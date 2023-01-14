package adapters.repositories.buyers.dtos

import adapters.repositories.DatabaseDto
import domain.entities.{Buyer, Property, PropertyFilters}
import zio.prelude.NonEmptySet
import zio.prelude.newtypes.Natural

import java.util.UUID

protected[buyers] final case class DatabaseBuyerDto(
    id: UUID,
    buyerEmailAddress: String, // TODO: use stronger type for email
    filters: Option[NonEmptySet[PropertyFilters]]
) extends DatabaseDto[Buyer] {
  override def toEntity: Buyer = {
    Buyer(id = this.id, buyerEmailAddress = this.buyerEmailAddress, filters = this.filters)
  }

  def acceptProperty(property: Property): Boolean = {
    filters match {
      case Some(buyerFilters) =>
        buyerFilters.foldLeft(true) { case (isPropertyOkForFilters, buyerFilter) =>
          buyerFilter.isPropertyOkForFilter(property) && isPropertyOkForFilters
        }
      case None => true
    }
  }
}

protected[buyers] object DatabaseBuyerDto {
  def apply(buyer: Buyer): DatabaseBuyerDto = {
    DatabaseBuyerDto(id = buyer.id, buyerEmailAddress = buyer.buyerEmailAddress, filters = buyer.filters)
  }
}
