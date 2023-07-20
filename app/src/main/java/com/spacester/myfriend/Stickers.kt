package com.spacester.myfriend

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import data.tatum.Bitcoin
import data.tatum.Ethereum
import data.tatum.Tatum
import data.tatum.WalletType
import io.stipop.Stipop
import io.stipop.StipopDelegate
import io.stipop.extend.StipopImageView
import io.stipop.model.SPPackage
import io.stipop.model.SPSticker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Stickers : AppCompatActivity(), StipopDelegate {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stickers)

        val tatum = Tatum()

        CoroutineScope(Dispatchers.Default).launch {
            val wallet = tatum.createWallet(Ethereum)
            println("[LOG] wallet: ${wallet}")
            val address = tatum.createPublicAddress(Ethereum, wallet.xpub, 1)
            println("[LOG] address: ${address}")
            val privateKey = tatum.createPrivateKey(Ethereum, wallet.mnemonic, 1)
            println("[LOG] private key: ${privateKey}")
        }

        val stipopIV = findViewById<StipopImageView>(R.id.stipopIV)

        Stipop.connect(this, stipopIV, "1234", "en", "US", this)

        Stipop.showSearch()

    }

    override fun onStickerSelected(sticker: SPSticker): Boolean {

        val i = Intent(this, SendStickerActivity::class.java)
        i.putExtra("type", intent.getStringExtra("type"))
        i.putExtra("id", intent.getStringExtra("id"))
        i.putExtra("uri",  sticker.stickerImg.toString())
        startActivity(i)
        finish()


        return true
    }

    override fun canDownload(spPackage: SPPackage): Boolean {

        return true
    }

}
