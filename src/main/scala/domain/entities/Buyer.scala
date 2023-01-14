package domain.entities

import zio.prelude.NonEmptySet
import zio.prelude.newtypes.Natural

import java.util.UUID

final case class Buyer(
    id: UUID,
    buyerEmailAddress: String,
    filters: Option[NonEmptySet[PropertyFilters]]
) //TODO: use stronger type for email
