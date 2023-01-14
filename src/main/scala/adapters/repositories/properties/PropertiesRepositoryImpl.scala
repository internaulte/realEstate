package adapters.repositories.properties

import adapters.repositories.properties.dtos.DatabasePropertyDto
import domain.entities.Property

import scala.collection.mutable
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

protected class PropertiesRepositoryImpl extends PropertiesRepository { // TODO: database config is missing
  override def saveNewProperty(property: Property): Future[Unit] = {
    Future(databaseProperties += DatabasePropertyDto(property))
  }

  private val databaseProperties: mutable.Set[DatabasePropertyDto] = mutable.Set()
}
