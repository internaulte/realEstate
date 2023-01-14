package domain.entities.utils.mocks

import core.Mock
import domain.entities.{Buyer, PropertyFilters}
import zio.prelude.NonEmptySet

import java.util.UUID

final class BuyerMock extends Mock[Buyer] {
  private var id: UUID = UUID.randomUUID()
  private var buyerEmailAddress: String = "email@email.com"
  private var filters: Option[NonEmptySet[PropertyFilters]] = None

  def setId(id: UUID): BuyerMock = {
    this.id = id
    this
  }

  def setBuyerEmailAddress(buyerEmailAddress: String): BuyerMock = {
    this.buyerEmailAddress = buyerEmailAddress
    this
  }

  def setFilters(filters: Option[NonEmptySet[PropertyFilters]]): BuyerMock = {
    this.filters = filters
    this
  }

  override def build(): Buyer = {
    Buyer(id = this.id, buyerEmailAddress = this.buyerEmailAddress, filters = this.filters)
  }
}
