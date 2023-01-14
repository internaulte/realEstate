package domain.usecases.properties

import adapters.repositories.properties.PropertiesRepository
import domain.entities.Property

import scala.concurrent.Future

trait PropertiesUseCases {
  def saveNewProperty(property: Property): Future[Unit]
}

object PropertiesUseCases {
  lazy val instance: PropertiesUseCases = new PropertiesUseCasesImpl(
    propertiesRepository = PropertiesRepository.instance
  )
}
