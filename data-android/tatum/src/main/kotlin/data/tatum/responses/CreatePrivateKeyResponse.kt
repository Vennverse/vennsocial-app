package data.tatum.responses

import kotlinx.serialization.Serializable

@Serializable
data class CreatePrivateKeyResponse(
    val key: String
) {
    override fun toString(): String {
        return "private key: $key"
    }
}
