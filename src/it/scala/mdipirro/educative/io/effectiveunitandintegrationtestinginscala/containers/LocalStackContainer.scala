package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.containers

import com.dimafeng.testcontainers.GenericContainer
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy

import scala.language.adhocExtensions
class LocalStackContainer private (underlying: GenericContainer) extends GenericContainer(underlying):
  lazy val endpoint = s"http://localhost:${mappedPort(LocalStackContainer.LocalStackPort)}"
  val accessKeyId = "not_used"
  val secretAccessKey = "not_used"

object LocalStackContainer:
  private val LocalStackPort = 4566

  case object Def extends GenericContainer.Def[LocalStackContainer](
      new LocalStackContainer(
        GenericContainer(
          dockerImage = "localstack/localstack:2.0.2",
          exposedPorts = Seq(LocalStackPort),
          waitStrategy = new LogMessageWaitStrategy().withRegEx(".*Ready\\.\n")
        )
      )
    )
