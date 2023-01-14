package domain.entities.utils.geographicCalculations.mocks

import domain.entities.utils.geographicCalculations.Point
import domain.entities.utils.types.{Latitude, Longitude}
import core.Mock

final class PointMock extends Mock[Point] {
  private var latitude: Latitude = Latitude(5.0)
  private var longitude: Longitude = Longitude(5.0)

  def setLatitude(latitude: Latitude): PointMock = {
    this.latitude = latitude
    this
  }

  def setLongitude(longitude: Longitude): PointMock = {
    this.longitude = longitude
    this
  }

  override def build(): Point = {
    Point(
      latitude = this.latitude,
      longitude = this.longitude
    )
  }
}
