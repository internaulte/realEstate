import adapters.controllers.properties.PropertiesController
import adapters.controllers.properties.dtos.PropertyDto
import domain.entities.utils.geographicCalculations.Point
import domain.entities.utils.types.{Latitude, Longitude, PropertyType}
import zio.prelude.newtypes.Natural

import concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
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

  val controller = PropertiesController.instance

  // should print email1@email1.com and email2@email2.com

  val otherNewProperty = PropertyDto(
    propertyType = PropertyType.House,
    position = Point(latitude = Latitude(5.2), longitude = Longitude(4.9)),
    priceInMillis = Natural(300000000),
    surfaceInMeterSquare = Natural(90),
    numberOfRooms = Natural(4),
    hasGarage = true,
    hasGarden = false,
    description = "a test house"
  )

  // should print email1@email1.com and email3@email3.com

  val thirdNewProperty = PropertyDto(
    propertyType = PropertyType.House,
    position = Point(latitude = Latitude(5.2), longitude = Longitude(4.9)),
    priceInMillis = Natural(300000000),
    surfaceInMeterSquare = Natural(90),
    numberOfRooms = Natural(4),
    hasGarage = true,
    hasGarden = true,
    description = "a test house"
  )

  // should print email1@email1.com only

  val finalResult: Future[Unit] = {
    for {
      _ <- controller.saveNewProperty(newProperty).map(_ => println("end first property")).recover {
        case error: Throwable =>
          println(error.getMessage)
          println(error.getStackTrace.mkString("Array(", ", ", ")"))
      }
      _ <- controller.saveNewProperty(otherNewProperty).map(_ => println("end other property")).recover {
        case error: Throwable =>
          println(error.getMessage)
          println(error.getStackTrace.mkString("Array(", ", ", ")"))
      }
      _ <- controller.saveNewProperty(thirdNewProperty).map(_ => println("end other property")).recover {
        case error: Throwable =>
          println(error.getMessage)
          println(error.getStackTrace.mkString("Array(", ", ", ")"))
      }
    } yield ()
  }

  Await.result(finalResult, Duration.Inf)
}
