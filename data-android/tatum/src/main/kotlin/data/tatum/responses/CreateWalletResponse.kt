package data.tatum.responses

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class CreateWalletResponse(
    @SerialName("xpub")
    val publicKey: String,
    val mnemonic: String,
)