package adapters.repositories

trait DatabaseDto[A] {
  def toEntity: A
}
