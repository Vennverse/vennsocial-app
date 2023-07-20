package data.tatum

import data.tatum.responses.CreateWalletResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

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
        return client.get("https://api.tatum.io/v3/${walletType.apiWalletType()}/wallet") {
            header("x-api-key", "2518f45b-2618-40e9-b965-eb719ccf2426")
            header("accept", "application/json")
        }.body()
    }
}