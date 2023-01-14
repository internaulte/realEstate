package adapters.repositories.properties.dtos

import domain.entities.Property
import domain.entities.utils.geographicCalculations.Point
import zio.prelude.newtypes.Natural
import adapters.repositories.DatabaseDto
import domain.entities.utils.types.PropertyType

import java.util.UUID

protected[properties] final case class DatabasePropertyDto (
    id: UUID,
    propertyType: PropertyType,
    position: Point,
    priceInMillis: Natural,
    surfaceInMeterSquare: Natural,
    numberOfRooms: Natural,
    hasGarage: Boolean,
    hasGarden: Boolean,
    description: String
) extends DatabaseDto[Property] {
  override def toEntity: Property = {
    Property(
      id = this.id,
      propertyType = this.propertyType,
      position = this.position,
      priceInMillis = this.priceInMillis,
      surfaceInMeterSquare = this.surfaceInMeterSquare,
      numberOfRooms = this.numberOfRooms,
      hasGarage = this.hasGarage,
      hasGarden = this.hasGarden,
      description = this.description
    )
  }
}

protected[properties] object DatabasePropertyDto {
  def apply(property: Property): DatabasePropertyDto = {
    DatabasePropertyDto(
      id = property.id,
      propertyType = property.propertyType,
      position = property.position,
      priceInMillis = property.priceInMillis,
      surfaceInMeterSquare = property.surfaceInMeterSquare,
      numberOfRooms = property.numberOfRooms,
      hasGarage = property.hasGarage,
      hasGarden = property.hasGarden,
      description = property.description
    )
  }
}