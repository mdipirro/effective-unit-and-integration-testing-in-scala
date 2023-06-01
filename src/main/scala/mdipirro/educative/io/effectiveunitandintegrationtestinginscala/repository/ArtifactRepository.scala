package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.repository

import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.PutObjectRequest

import java.nio.file.Path
import scala.util.Try

trait ArtifactRepository:
  def upload(location: String, artifactPath: Path): Boolean

trait AwsClientProvider:
  protected lazy val s3: S3Client

trait S3ArtifactRepository extends ArtifactRepository:
  this: AwsClientProvider =>

  override def upload(location: String, artifactPath: Path): Boolean =
    Try {
      s3.putObject(
        PutObjectRequest.builder()
          .bucket(location)
          .key(artifactPath.getFileName.toString)
          .build(),
        artifactPath
      )
    }.toOption.isDefined
