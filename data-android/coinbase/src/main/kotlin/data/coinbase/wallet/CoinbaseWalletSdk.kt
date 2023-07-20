package data.coinbase.wallet

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.coinbase.android.nativesdk.CoinbaseWalletSDK
import com.coinbase.android.nativesdk.message.request.Account
import com.coinbase.android.nativesdk.message.request.Web3JsonRPC
import com.coinbase.android.nativesdk.message.response.ActionResult

class CoinbaseWalletSdk(
    applicationContext: Context,
    appLinkDomain: String,
) {

    private lateinit var launcher: ActivityResultLauncher<Intent>
    private val sdk = CoinbaseWalletSDK(
        appContext = applicationContext,
        domain = Uri.parse(appLinkDomain),
        openIntent = { intent -> launcher.launch(intent) }
    )

    fun init(lifecycleOwner: LifecycleOwner) {
        when (lifecycleOwner) {
            is AppCompatActivity -> lifecycleOwner.init()
            is Fragment -> lifecycleOwner.init()
        }
    }

    fun establishConnection() {
        val requestAccount = Web3JsonRPC.RequestAccounts()
            .action()
        val handShakeActions = listOf(requestAccount)

        sdk.initiateHandshake(
            initialActions = handShakeActions
        ) { result: Result<List<ActionResult>>, account: Account? ->
            result.onSuccess { actionResults: List<ActionResult> ->
                println("[LOG] HandShake success:\n$actionResults")
            }
            result.onFailure { err ->
                println("[LOG] HandShake failure:\n$err")
            }
        }
    }

    private fun Fragment.init() {
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val uri = it.data?.data ?: return@registerForActivityResult
            sdk.handleResponse(uri)
        }
    }

    private fun AppCompatActivity.init() {
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val uri = it.data?.data ?: return@registerForActivityResult
            sdk.handleResponse(uri)
        }
    }
}