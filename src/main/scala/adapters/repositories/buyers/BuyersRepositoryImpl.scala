package adapters.repositories.buyers

import adapters.repositories.buyers.dtos.DatabaseBuyerDto
import domain.entities.utils.geographicCalculations.{Area, Point}
import domain.entities.utils.types.{DistanceInMeters, Latitude, Longitude, PropertyType}
import domain.entities.*
import zio.prelude.NonEmptySet
import zio.prelude.newtypes.Natural

import java.util.UUID
import scala.collection.mutable
import scala.concurrent.Future

protected class BuyersRepositoryImpl extends BuyersRepository { // TODO: database config is missing
  override def getBuyersForProperty(property: Property): Future[Option[NonEmptySet[Buyer]]] = {
    val filteredBuyers = databaseBuyers
      .filter { buyer =>
        buyer.acceptProperty(property)
      }
      .map(_.toEntity)
    // TODO: optimize: use collect to avoid to pass all filters if one is false and to do the toEntity with no second list parsing

    Future.successful(NonEmptySet.fromIterableOption(filteredBuyers.toSet))
  }

  private val databaseBuyers: mutable.Set[DatabaseBuyerDto] = mutable.Set(
    DatabaseBuyerDto(id = UUID.randomUUID(), buyerEmailAddress = "email1@email1.com", filters = None),
    DatabaseBuyerDto(
      id = UUID.randomUUID(),
      buyerEmailAddress = "email2@email2.com",
      filters = Some(
        NonEmptySet(
          PriceInMillisFilter(maxValue = Some(Natural(350000000))),
          SurfaceInMeterSquareFilter(minValue = Natural(80)),
          NumberOfRoomsFilter(minValue = Natural(3), maxValue = None),
          PropertyTypesFilter(NonEmptySet(PropertyType.Apartment))
        )
      )
    ),
    DatabaseBuyerDto(
      id = UUID.randomUUID(),
      buyerEmailAddress = "email3@email3.com",
      filters = Some(
        NonEmptySet(
          GeographicFilter(area =
            Area(
              center = Point(latitude = Latitude(5), longitude = Longitude(5)),
              radius = DistanceInMeters(Natural.make(40000).toOption.get)
            )
          ),
          PropertyTypesFilter(NonEmptySet(PropertyType.House)),
          HasGarageFilter(isValid = true),
          HasGardenFilter(isValid = false)
        )
      )
    )
  )
}
