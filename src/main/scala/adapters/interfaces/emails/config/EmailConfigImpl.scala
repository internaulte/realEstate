package adapters.interfaces.emails.config

import adapters.interfaces.emails.dtos.PreparedEmail
import zio.prelude.NonEmptySet

import scala.concurrent.Future

protected object EmailConfigImpl extends EmailConfig {
  override val mailer: NonEmptySet[PreparedEmail] => Future[Unit] = {
    mailsToSend => Future.successful{
      println("starts to send mails")
      println(mailsToSend)
      println("mails send")
    }
  }
}
