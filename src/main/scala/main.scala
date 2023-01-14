import adapters.controllers.properties.PropertiesController
import adapters.controllers.properties.dtos.PropertyDto
import domain.entities.utils.geographicCalculations.Point
import domain.entities.utils.types.{Latitude, Longitude, PropertyType}
import zio.prelude.newtypes.Natural

import concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration.Duration

@main
def main(): Unit = {
  // new property arrives from any way (Web API, messaging listened topic...)
  val newProperty = PropertyDto(
    propertyType = PropertyType.Apartment,
    position = Point(latitude = Latitude(6.5), longitude = Longitude(7.4)),
    priceInMillis = Natural(300000000),
    surfaceInMeterSquare = Natural(90),
    numberOfRooms = Natural(4),
    hasGarage = false,
    hasGarden = true,
    description = "a test house"
  )

  val result = PropertiesController.instance.saveNewProperty(newProperty).map(_ => println("end")).recover {
    case error: Throwable =>
      println(error.getMessage)
      println(error.getStackTrace.mkString("Array(", ", ", ")"))
  }

  // should print email1@email1.com and email2@email2.com

  Await.result(result, Duration.Inf)
}
