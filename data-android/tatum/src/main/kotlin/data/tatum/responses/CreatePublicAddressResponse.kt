package data.tatum.responses

import kotlinx.serialization.Serializable

@Serializable
data class CreatePublicAddressResponse(
    val address: String
){
    override fun toString(): String {
        return "public address: $address"
    }
}