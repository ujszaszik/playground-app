package hu.bitnet.smallapp.entity.error

class EntityIsNullException(
    id: Int,
    message: String? = "Entity $id is null",
    cause: Throwable? = null
) : RuntimeException(message, cause)