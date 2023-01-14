package adapters.controllers.dtos

trait EntityDto[A] {
  def toEntity: A
}
