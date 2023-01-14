package adapters.interfaces.emails.dtos

protected[emails] final case class PreparedEmail(
    emailAddress: String, // TODO: use stronger type here,
    textToSend: String
)
