package domain.entities.utils.geographicCalculations

import domain.entities.utils.types.DistanceInMeters

final case class Area(center: Point, radius: DistanceInMeters) {
  def isPointInArea(point: Point): Boolean = {
    if (this.center == point) {
      true
    } else {
      this.center.distanceInMetersTo(point) <= this.radius
    }
  }

  def isAreaContainsOther(otherArea: Area): Boolean = {
    val distanceOfCenters = this.center.distanceInMetersTo(otherArea.center)
    val maxOtherAreaDistanceToThisCenter = otherArea.radius + distanceOfCenters

    maxOtherAreaDistanceToThisCenter <= this.radius
  }
}