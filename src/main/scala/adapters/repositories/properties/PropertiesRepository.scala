package adapters.repositories.properties

import domain.entities.Property

import scala.concurrent.Future

trait PropertiesRepository {
  def saveNewProperty(property: Property): Future[Unit]
}

object PropertiesRepository {
  lazy val instance: PropertiesRepository = new PropertiesRepositoryImpl
}