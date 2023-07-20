package data.tatum

import data.tatum.responses.CreatePrivateKeyResponse
import data.tatum.responses.CreatePublicAddressResponse
import data.tatum.responses.CreateWalletResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class Tatum {
    private val client = HttpClient(engine = Android.create()) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                explicitNulls = false
            })
        }
        expectSuccess = true
    }

    suspend fun createWallet(walletType: WalletType): CreateWalletResponse {
        println("[LOG] creating a ${walletType.apiWalletType()} wallet")
        return client.get("https://api.tatum.io/v3/${walletType.apiWalletType()}/wallet") {
            header("x-api-key", "2518f45b-2618-40e9-b965-eb719ccf2426")
            header("accept", "application/json")
        }
            .body()
    }

    suspend fun createPublicAddress(
        walletType: WalletType,
        xpub: String,
        index: Int
    ): CreatePublicAddressResponse {
        println("[LOG] creating a ${walletType.apiWalletType()} public address, number: $index")
        return client.get("https://api.tatum.io/v3/${walletType.apiWalletType()}/address/$xpub/$index") {
            header("x-api-key", "2518f45b-2618-40e9-b965-eb719ccf2426")
        }
            .body()
    }

    suspend fun createPrivateKey(
        walletType: WalletType,
        mnemonic: String,
        index: Int
    ): CreatePrivateKeyResponse {
        println("[LOG] creating a ${walletType.apiWalletType()} private key, number: $index")
        return client.post("https://api.tatum.io/v3/${walletType.apiWalletType()}/wallet/priv") {
            header("content-type", "application/json")
            header("x-api-key", "2518f45b-2618-40e9-b965-eb719ccf2426")
            setBody(
                buildJsonObject {
                    put("mnemonic", mnemonic)
                    put("index", index)
                },
            )
        }.body()
    }
}