package domain.entities.utils.geographicCalculations

import core.UnitTestSpec
import domain.entities.utils.geographicCalculations.mocks.PointMock
import domain.entities.utils.types.{DistanceInMeters, Latitude, Longitude}
import zio.prelude.newtypes.Natural

final class PointTest extends UnitTestSpec {
  describe("isAreaContainsOther") {
    it("should return 0 if points are equals") {
      val point1 = new PointMock().setLatitude(Latitude(5d)).setLongitude(Longitude(5d)).build()
      val point2 = new PointMock().setLatitude(Latitude(5d)).setLongitude(Longitude(5d)).build()

      assert(point1.distanceInMetersTo(point2) == DistanceInMeters(Natural.zero))
    }
    it("should return distance between points, no negative value") {
      val point1 = new PointMock().setLatitude(Latitude(43.2969901)).setLongitude(Longitude(5.3789783)).build()
      val point2 = new PointMock().setLatitude(Latitude(43.2969901)).setLongitude(Longitude(5.4789783)).build()

      val expectedDistance = Natural(13748)

      assert(point1.distanceInMetersTo(point2) == DistanceInMeters(expectedDistance))
      assert(point2.distanceInMetersTo(point1) == DistanceInMeters(expectedDistance))
    }
  }
}
