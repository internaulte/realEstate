package adapters.controllers.properties.dtos

import adapters.controllers.dtos.EntityDto
import domain.entities.Property
import domain.entities.utils.geographicCalculations.Point
import domain.entities.utils.types.PropertyType
import zio.prelude.newtypes.Natural

import java.util.UUID

final case class PropertyDto( // should be protected[properties] but must be visible by main class for now
    propertyType: PropertyType, // Should be PropertyTypeDto
    position: Point, // Should be PointDto
    priceInMillis: Natural,
    surfaceInMeterSquare: Natural,
    numberOfRooms: Natural,
    hasGarage: Boolean,
    hasGarden: Boolean,
    description: String
) extends EntityDto[Property] {
  override def toEntity: Property = {
    Property(
      id = UUID.randomUUID(),
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
