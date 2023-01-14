package adapters.interfaces.emails

import adapters.interfaces.emails.config.EmailConfig
import adapters.interfaces.emails.dtos.PreparedEmail
import domain.entities.{Buyer, Property}
import zio.prelude.NonEmptySet

import scala.concurrent.Future

protected class EmailingServiceImpl(private val emailConfig: EmailConfig) extends EmailingService {
  override def notifyBuyers(
      buyersToNotify: NonEmptySet[Buyer],
      propertyToBeNotified: Property
  ): Future[Unit] = {
    val preparedEmails = buyersToNotify.map { buyerToNotify =>
        val textToSend = "toto"
        PreparedEmail(emailAddress = buyerToNotify.buyerEmailAddress, textToSend = textToSend)
    }

    emailConfig.mailer(preparedEmails)
  }
}
