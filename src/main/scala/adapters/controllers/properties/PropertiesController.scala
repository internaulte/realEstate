package adapters.controllers.properties

import adapters.controllers.properties.dtos.PropertyDto
import domain.usecases.buyers.BuyersUseCases
import domain.usecases.properties.PropertiesUseCases

import scala.concurrent.Future

trait PropertiesController {
  def saveNewProperty(propertyDto: PropertyDto): Future[Unit]
}

object PropertiesController {
  lazy val instance: PropertiesController = new PropertiesControllerImpl(
    propertiesUseCases = PropertiesUseCases.instance,
    buyersUseCases = BuyersUseCases.instance
  )
}
