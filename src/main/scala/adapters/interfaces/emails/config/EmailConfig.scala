package adapters.interfaces.emails.config

import adapters.interfaces.emails.dtos.PreparedEmail
import zio.prelude.NonEmptySet

import scala.concurrent.Future

protected[emails] trait EmailConfig {
  val mailer: NonEmptySet[PreparedEmail] => Future[Unit] // TODO: return list of email sending failure and success ?
}

protected[emails] object EmailConfig {
  lazy val instance: EmailConfig = EmailConfigImpl
}