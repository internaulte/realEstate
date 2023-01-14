package adapters.interfaces.emails

import adapters.interfaces.emails.config.EmailConfig
import domain.entities.{Buyer, Property}
import zio.prelude.NonEmptySet

import scala.concurrent.Future

trait EmailingService {
  def notifyBuyers(buyersToNotify: NonEmptySet[Buyer], propertyToBeNotified: Property): Future[Unit]
}

object EmailingService {
  lazy val instance: EmailingService = new EmailingServiceImpl(emailConfig = EmailConfig.instance)
}