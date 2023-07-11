package io.stipop.activity

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import io.stipop.Config
import io.stipop.R
import io.stipop.databinding.ActivityStoreBinding
import io.stipop.fragment.AllStickerFragment
import io.stipop.fragment.MyStickerFragment

class StoreActivity: FragmentActivity() {

    lateinit var context: Context
    private lateinit var binding: ActivityStoreBinding

    private lateinit var fragmentTransaction: FragmentTransaction

    var tab = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoreBinding.inflate(layoutInflater)
        this.context = this

        tab = intent.getIntExtra("tab", 1)

        val drawable = binding.containerLL.background as GradientDrawable
        drawable.setColor(Color.parseColor(Config.themeBackgroundColor)) // solid  color

        binding.navigationBarLL.setBackgroundColor(Color.parseColor(Config.themeGroupedContentBackgroundColor))

        binding.underLineV.setBackgroundColor(Config.getUnderLineColor(context))


        binding.allV.setBackgroundColor(Config.getStoreNavigationTextColor(context, true))
        binding.myV.setBackgroundColor(Config.getStoreNavigationTextColor(context, true))

        val fm: FragmentManager = supportFragmentManager

        if (tab == 2) {
            fragmentTransaction = fm.beginTransaction()
            fragmentTransaction.add(R.id.fragmentFL, MyStickerFragment())
            fragmentTransaction.commit()
        } else {
            fragmentTransaction = fm.beginTransaction()
            fragmentTransaction.add(R.id.fragmentFL, AllStickerFragment())
            fragmentTransaction.commit()
        }

        binding.allTabLL.setOnClickListener {
            changeTabs(1)

            fragmentTransaction = fm.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentFL, AllStickerFragment())
            fragmentTransaction.commit()
        }

        binding.myTabLL.setOnClickListener {
            changeTabs(2)

            fragmentTransaction = fm.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentFL, MyStickerFragment())
            fragmentTransaction.commit()
        }

        changeTabs(tab)

    }

    private fun changeTabs(type: Int) {
        binding.allTV.setTextColor(Config.getStoreNavigationTextColor(context, false))
        binding.myTV.setTextColor(Config.getStoreNavigationTextColor(context, false))

        binding.allV.visibility = View.INVISIBLE
        binding.myV.visibility = View.INVISIBLE

        if (type == 1) {
            binding.allTV.setTextColor(Config.getStoreNavigationTextColor(context, true))
            binding.allV.visibility = View.VISIBLE
        } else {
            binding.myTV.setTextColor(Config.getStoreNavigationTextColor(context, true))
            binding.myV.visibility = View.VISIBLE
        }
    }

}