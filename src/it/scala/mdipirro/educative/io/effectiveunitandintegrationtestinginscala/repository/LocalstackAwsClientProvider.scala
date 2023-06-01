package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.repository
import software.amazon.awssdk.auth.credentials.{AwsBasicCredentials, StaticCredentialsProvider}
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client

import java.net.URI
trait LocalstackAwsClientProvider extends AwsClientProvider:
  val endpoint: String

  override protected lazy val s3: S3Client = S3Client.builder()
    .region(Region.US_WEST_2)
    .endpointOverride(URI(endpoint))
    .credentialsProvider(
      StaticCredentialsProvider.create(
        AwsBasicCredentials.create("accessKeyId", "secretAccessKey")
      )
    )
    .forcePathStyle(true)
    .build()
