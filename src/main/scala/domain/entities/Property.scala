package domain.entities

import domain.entities.utils.geographicCalculations.Point
import domain.entities.utils.types.PropertyType
import zio.prelude.newtypes.Natural

import java.time.LocalDate
import java.util.UUID

final case class Property( // TODO: add mocks in test
    id: UUID,
    propertyType: PropertyType,
    position: Point,
    priceInMillis: Natural,
    surfaceInMeterSquare: Natural,
    numberOfRooms: Natural,
    hasGarage: Boolean,
    hasGarden: Boolean,
    description: String
)
