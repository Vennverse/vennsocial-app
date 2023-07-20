package data.tatum

sealed interface WalletType {
    fun apiWalletType(): String
}

object Bitcoin : WalletType {
    override fun apiWalletType() = "bitcoin"
}

object Ethereum : WalletType {
    override fun apiWalletType() = "ethereum"
}
