package domain.entities.utils.types

import zio.prelude.Subtype
import zio.prelude.newtypes.Natural


object DistanceInMeters extends Subtype[Natural] {
  def getAbsoluteValue(number: Int): DistanceInMeters = {
    val checkedValue: Natural = if (number > 0) {
      Natural.make(number).toOption.get
    } else {
      Natural.make(-number).toOption.get
    }

    wrap(checkedValue)
  }
  
  def fromNatural(natural: Natural): DistanceInMeters = {
    DistanceInMeters(natural)
  }
}
type DistanceInMeters = DistanceInMeters.Type

object Latitude extends Subtype[Double]
type Latitude = Latitude.Type

object LatitudeInRadiants extends Subtype[Double] {
  def fromLatitude(latitude: Latitude): LatitudeInRadiants = {
    wrap(Latitude.unwrap(latitude).toRadians)
  }
}
type LatitudeInRadiants = LatitudeInRadiants.Type

object Longitude extends Subtype[Double]
type Longitude = Longitude.Type

object LongitudeInRadiants extends Subtype[Double] {
  def fromLongitude(longitude: Longitude): LongitudeInRadiants = {
    wrap(Longitude.unwrap(longitude).toRadians)
  }
}
type LongitudeInRadiants = LongitudeInRadiants.Type