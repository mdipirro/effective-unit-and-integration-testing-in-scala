package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.repository

import com.dimafeng.testcontainers.scalatest.TestContainerForAll
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.IntegrationTestSuite
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.containers.LocalStackContainer
import software.amazon.awssdk.auth.credentials.{AwsBasicCredentials, StaticCredentialsProvider}
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.{CreateBucketRequest, HeadObjectRequest, NoSuchKeyException}

import java.net.URI
import java.nio.file.Paths
import scala.util.{Random, Try}

class S3ArtifactRepositorySpec extends IntegrationTestSuite with TestContainerForAll:

  private val BucketName = Random.alphanumeric.take(10).mkString.toLowerCase

  override val containerDef: LocalStackContainer.Def.type = LocalStackContainer.Def

  "S3ArtifactRepository" `should` "upload a file in the desired bucket" in {
    withContainers { cs =>
      val repo = new S3ArtifactRepository with LocalstackAwsClientProvider:
        override val endpoint: String = cs.endpoint

      repo.upload(BucketName, Paths.get("build.sbt")) shouldBe true

      Try(
        s3Client(cs).headObject(
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

  private def s3Client(cs: LocalStackContainer) = S3Client
    .builder()
    .region(Region.US_WEST_2)
    .endpointOverride(URI(cs.endpoint))
    .credentialsProvider(
      StaticCredentialsProvider.create(
        AwsBasicCredentials.create(cs.accessKeyId, cs.secretAccessKey)
      )
    )
    .forcePathStyle(true)
    .build()

  override def afterContainersStart(cs: LocalStackContainer): Unit =
    super.afterContainersStart(cs)
    s3Client(cs).createBucket(CreateBucketRequest.builder().bucket(BucketName).build)
