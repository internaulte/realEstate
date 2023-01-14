package domain.usecases.buyers

import adapters.interfaces.emails.EmailingService
import adapters.repositories.buyers.BuyersRepository
import domain.entities.Property
import domain.usecases.properties.PropertiesUseCases

import scala.concurrent.Future

trait BuyersUseCases {
  def savePropertyAndNotifyBuyers(property: Property): Future[Unit]

  def notifyBuyersForProperty(property: Property): Future[Unit]
}

object BuyersUseCases {
  lazy val instance: BuyersUseCases = new BuyersUseCasesImpl(
    buyersRepository = BuyersRepository.instance,
    emailingService = EmailingService.instance,
    propertiesUseCases = PropertiesUseCases.instance
  )
}
