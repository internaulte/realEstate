package adapters.controllers.properties

import adapters.controllers.properties.dtos.PropertyDto
import domain.usecases.buyers.BuyersUseCases
import domain.usecases.properties.PropertiesUseCases

import scala.concurrent.Future

protected class PropertiesControllerImpl(
    private val propertiesUseCases: PropertiesUseCases, // for get properties and get properties by id...
    private val buyersUseCases: BuyersUseCases // use for post, put, patch properties
) extends PropertiesController {
  override def saveNewProperty(propertyDto: PropertyDto): Future[Unit] = {
    buyersUseCases.savePropertyAndNotifyBuyers(propertyDto.toEntity)
  }
}
