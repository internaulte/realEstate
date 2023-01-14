package adapters.repositories.buyers

import domain.entities.{Buyer, Property}
import zio.prelude.NonEmptySet

import scala.concurrent.Future

trait BuyersRepository {
  def getBuyersForProperty(property: Property): Future[Option[NonEmptySet[Buyer]]]
}

object BuyersRepository {
  lazy val instance: BuyersRepository = new BuyersRepositoryImpl
}