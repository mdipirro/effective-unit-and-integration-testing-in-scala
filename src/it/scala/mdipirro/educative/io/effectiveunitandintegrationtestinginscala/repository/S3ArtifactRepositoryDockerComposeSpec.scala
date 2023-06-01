package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.repository

import com.dimafeng.testcontainers.scalatest.TestContainerForAll
import com.dimafeng.testcontainers.{ DockerComposeContainer, ExposedService }
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.IntegrationTestSuite
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy
import software.amazon.awssdk.auth.credentials.{ AwsBasicCredentials, StaticCredentialsProvider }
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.{
  CreateBucketRequest,
  HeadObjectRequest,
  NoSuchKeyException,
}

import java.io.File
import java.net.URI
import java.nio.file.Paths
import scala.util.{ Random, Try }

class S3ArtifactRepositoryDockerComposeSpec extends IntegrationTestSuite with TestContainerForAll:

  private val BucketName = Random.alphanumeric.take(10).mkString.toLowerCase

  private val LocalStackName = "localstack"

  override val containerDef: DockerComposeContainer.Def = DockerComposeContainer.Def(
    File("src/it/resources/docker-compose.yml"),
    exposedServices = Seq(
      ExposedService(
        LocalStackName,
        4566,
        new LogMessageWaitStrategy().withRegEx(".*Ready\\.\n"),
      )
    )
  )

  private def s3Client(port: Int) = S3Client
    .builder()
    .region(Region.US_WEST_2)
    .endpointOverride(URI(s"http://localhost:$port"))
    .credentialsProvider(
      StaticCredentialsProvider.create(
        AwsBasicCredentials.create("accessKeyId", "secretAccessKey")
      )
    )
    .forcePathStyle(true)
    .build()

  "S3ArtifactRepository" `should` "upload a file in the desired bucket" in {
    withContainers { cs =>
      val localstackPort = cs.getServicePort(LocalStackName, 4566)

      val repo = new S3ArtifactRepository with LocalstackAwsClientProvider:
        override val endpoint: String = s"http://localhost:$localstackPort"

      repo.upload(BucketName, Paths.get("build.sbt")) shouldBe true

      Try(
        s3Client(localstackPort).headObject(
          HeadObjectRequest.builder().bucket(BucketName).key("build.sbt").build()
        )
      ).fold(
        {
          case _: NoSuchKeyException => fail("File not found")
          case _ => fail()
        },
        _ => succeed,
      )
    }
  }

  override def afterContainersStart(cs: DockerComposeContainer): Unit =
    super.afterContainersStart(cs)
    s3Client(cs.getServicePort(LocalStackName, 4566))
      .createBucket(CreateBucketRequest.builder().bucket(BucketName).build)
