package domain.usecases.buyers

import adapters.interfaces.emails.EmailingService
import adapters.repositories.buyers.BuyersRepository
import domain.entities.{Buyer, Property}
import domain.usecases.properties.PropertiesUseCases
import zio.prelude.NonEmptySet

import scala.concurrent.Future
import concurrent.ExecutionContext.Implicits.global

protected class BuyersUseCasesImpl(
    private val buyersRepository: BuyersRepository,
    private val emailingService: EmailingService,
    private val propertiesUseCases: PropertiesUseCases
) extends BuyersUseCases {
  override def savePropertyAndNotifyBuyers(property: Property): Future[Unit] = {
    for {
      _ <- propertiesUseCases.saveNewProperty(property)
      _ <- notifyBuyersForProperty(property)
    } yield ()
  }

  override def notifyBuyersForProperty(property: Property): Future[Unit] = {
    for {
      buyersOpt <- buyersRepository.getBuyersForProperty(property)
      _ <- buyersOpt match
        case Some(buyers) => emailingService.notifyBuyers(buyers, property)
        case None => Future.successful(())
    } yield ()
  }
}
