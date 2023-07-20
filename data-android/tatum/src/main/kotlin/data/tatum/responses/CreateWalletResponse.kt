package data.tatum.responses

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class CreateWalletResponse(
    val xpub: String,
    val mnemonic: String,
) {

    override fun toString(): String {
        return "x pub: $xpub\nmnemonic: $mnemonic"
    }
}