package domain.usecases.properties

import adapters.repositories.properties.PropertiesRepository
import domain.entities.Property

import scala.concurrent.Future

protected class PropertiesUseCasesImpl(private val propertiesRepository: PropertiesRepository)
    extends PropertiesUseCases {
  override def saveNewProperty(property: Property): Future[Unit] = {
    propertiesRepository.saveNewProperty(property)
  }
}
