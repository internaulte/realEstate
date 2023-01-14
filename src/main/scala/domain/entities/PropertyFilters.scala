package domain.entities

import domain.entities.utils.geographicCalculations.Area
import zio.prelude.NonEmptySet
import zio.prelude.newtypes.Natural

import java.time.{LocalDate, Month, YearMonth}

sealed trait PropertyFilters {
  def isPropertyOkForFilter(property: Property): Boolean
}

sealed trait NumericPropertyFilters extends PropertyFilters {
  val minValue: Natural
  val maxValue: Option[Natural]

  protected def isMaxValueOkForFilter(propertyValue: Natural): Boolean = maxValue match {
    case Some(maxValue) => maxValue >= propertyValue
    case None => true
  }
}

sealed trait BooleanPropertyFilters extends PropertyFilters {
  val isValid: Boolean
}

sealed trait EnumPropertyFilter[A] extends PropertyFilters {
  val values: NonEmptySet[A]
}

final case class PriceInMillisFilter(
    override val minValue: Natural = Natural.zero,
    override val maxValue: Option[Natural] = None
) extends NumericPropertyFilters {
  override def isPropertyOkForFilter(property: Property): Boolean = {
    property.priceInMillis >= minValue && isMaxValueOkForFilter(property.priceInMillis)
  }
}

final case class SurfaceInMeterSquareFilter(
    override val minValue: Natural = Natural.zero,
    override val maxValue: Option[Natural] = None
) extends NumericPropertyFilters {
  override def isPropertyOkForFilter(property: Property): Boolean = {
    property.surfaceInMeterSquare >= minValue && isMaxValueOkForFilter(property.surfaceInMeterSquare)
  }
}

final case class NumberOfRoomsFilter(
    override val minValue: Natural = Natural.zero,
    override val maxValue: Option[Natural] = None
) extends NumericPropertyFilters {
  override def isPropertyOkForFilter(property: Property): Boolean = {
    property.numberOfRooms >= minValue && isMaxValueOkForFilter(property.numberOfRooms)
  }
}

final case class GeographicFilter(area: Area) extends PropertyFilters {
  override def isPropertyOkForFilter(property: Property): Boolean = {
    area.isPointInArea(property.position)
  }
}

final case class HasGarageFilter(override val isValid: Boolean) extends BooleanPropertyFilters {
  override def isPropertyOkForFilter(property: Property): Boolean = {
    property.hasGarage == isValid
  }
}

final case class HasGardenFilter(override val isValid: Boolean) extends BooleanPropertyFilters {
  override def isPropertyOkForFilter(property: Property): Boolean = {
    property.hasGarden == isValid
  }
}

final case class PropertyTypesFilter[PropertyType](override val values: NonEmptySet[PropertyType])
    extends EnumPropertyFilter[PropertyType] {
  override def isPropertyOkForFilter(property: Property): Boolean = {
    values.contains(property.propertyType)
  }
}
