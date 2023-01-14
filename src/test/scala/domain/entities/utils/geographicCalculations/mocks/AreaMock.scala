package domain.entities.utils.geographicCalculations.mocks

import core.Mock
import domain.entities.utils.geographicCalculations.{Area, Point}
import domain.entities.utils.types.DistanceInMeters
import zio.prelude.newtypes.Natural

final class AreaMock extends Mock[Area] {
  private var center: Point = new PointMock().build()
  private var radius: DistanceInMeters = DistanceInMeters.fromNatural(Natural(5))

  def setCenter(center: Point): AreaMock = {
    this.center = center
    this
  }

  def setRadius(radius: DistanceInMeters): AreaMock = {
    this.radius = radius
    this
  }

  override def build(): Area = {
    Area(
      center = this.center,
      radius = this.radius
    )
  }
}
