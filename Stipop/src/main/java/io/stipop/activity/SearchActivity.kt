package io.stipop.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AbsListView
import androidx.recyclerview.widget.LinearLayoutManager
import io.stipop.*
import io.stipop.adapter.KeywordAdapter
import io.stipop.adapter.StickerAdapter
import io.stipop.databinding.ActivitySearchStickBinding
import io.stipop.extend.RecyclerDecoration
import io.stipop.model.SPSticker
import org.json.JSONObject
import java.io.IOException

class SearchActivity: Activity() {

    lateinit var context: Context

    private lateinit var binding: ActivitySearchStickBinding
    private lateinit var keywordAdapter: KeywordAdapter
    private lateinit var stickerAdapter: StickerAdapter

    var keywords = ArrayList<JSONObject>()
    private var stickerData = ArrayList<SPSticker>()

    private var lastItemVisibleFlag = false

    var page = 1
    var totalPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchStickBinding.inflate(layoutInflater)

        this.context = this

        val drawable = binding.cont.background as GradientDrawable
        drawable.setColor(Color.parseColor(Config.themeBackgroundColor))

        val drawable2 = binding.bar.background as GradientDrawable
        drawable2.setColor(Color.parseColor(Config.themeGroupedContentBackgroundColor)) // solid  color
        drawable2.cornerRadius = Utils.dpToPx(Config.searchbarRadius.toFloat())

        binding.searchIV.setImageResource(Config.getSearchbarResourceId(context))
        binding.eraseIV.setImageResource(Config.getEraseResourceId(context))

        binding.titleTV.setTextColor(Config.getSearchTitleTextColor(context))
        binding.keywordET.setTextColor(Config.getSearchTitleTextColor(context))


        binding.searchIV.setIconDefaultsColor()
        binding.eraseIV.setIconDefaultsColor()


        val gd = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(Color.parseColor(Config.themeBackgroundColor), Color.TRANSPARENT)
        )

        binding.shadowV.background = gd

        binding.clearTextLL.setOnClickListener {
            binding.keywordET.setText("")
        }

        binding.keywordET.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val keyword = Utils.getString(binding.keywordET)

                page = 1
                search(keyword)
            }
        })

        keywordAdapter = KeywordAdapter(keywords)
        keywordAdapter.setOnItemClickListener(object : KeywordAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                if (position > keywords.size) {
                    return
                }

                val item = keywords[position]
                val keyword = Utils.getString(item, "keyword")
                page = 1
                search(keyword)
            }
        })

        stickerAdapter = StickerAdapter(context, R.layout.item_sticker, stickerData)

        val mLayoutManager = LinearLayoutManager(this)
        mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        binding.keywordRV.layoutManager = mLayoutManager
        binding.keywordRV.addItemDecoration(RecyclerDecoration(10))
        binding.keywordRV.adapter = keywordAdapter

        binding.stickerGV.numColumns = Config.searchNumOfColumns
        binding.stickerGV.adapter = stickerAdapter
        binding.stickerGV.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScrollStateChanged(absListView: AbsListView?, scrollState: Int) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && lastItemVisibleFlag && totalPage > page) {
                    page += 1

                    search(Utils.getString(binding.keywordET))
                }
            }

            override fun onScroll(view: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
                lastItemVisibleFlag = (totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount)
            }

        })

        binding.stickerGV.setOnItemClickListener { adapterView, view, i, l ->
            val sticker = stickerData[i]

            Stipop.send(sticker.stickerId, sticker.keyword) { result ->
                if (result) {
                    Stipop.instance!!.delegate.onStickerSelected(sticker)

                    finish()
                }
            }
        }

        if (Config.searchTagsHidden) {
            binding.tagLL.visibility = View.GONE
        } else {
            binding.tagLL.visibility = View.VISIBLE

            getKeyword()
        }

        search("")
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getKeyword() {
        keywords.clear()

        APIClient.get(this, APIClient.APIPath.SEARCH_KEYWORD.rawValue, null) { response: JSONObject?, e: IOException? ->
            // println(response)

            if (null != response) {

                if (!response.isNull("body")) {
                    val body = response.getJSONObject(java.lang.String.valueOf("body"))

                    val keywordList = body.getJSONArray("keywordList")

                    for (i in 0 until keywordList.length()) {
                        keywords.add(keywordList.get(i) as JSONObject)
                    }

                }

            }

            keywordAdapter.notifyDataSetChanged()

        }

    }

    private fun search(keyword: String) {

        val params = JSONObject()
        params.put("userId", Stipop.userId)
        params.put("lang", Stipop.lang)
        params.put("countryCode", Stipop.countryCode)
        params.put("limit", 36)
        params.put("pageNumber", page)
        params.put("q", keyword)

        APIClient.get(this, APIClient.APIPath.SEARCH.rawValue, params) { response: JSONObject?, e: IOException? ->
            // println(response)

            if (null != response) {

                if (!response.isNull("body")) {
                    val body = response.getJSONObject(java.lang.String.valueOf("body"))

                    if (!body.isNull("pageMap")) {
                        val pageMap = body.getJSONObject("pageMap")
                        totalPage = Utils.getInt(pageMap, "pageCount")
                    }

                    if (!body.isNull("stickerList")) {
                        val stickerList = body.getJSONArray("stickerList")

                        if (stickerList.length() < 1) {
                            return@get
                        }

                        if (page == 1) {
                            stickerData.clear()
                        }

                        for (i in 0 until stickerList.length()) {
                            stickerData.add(SPSticker(stickerList.get(i) as JSONObject))
                        }

                    }

                }

            }

            stickerAdapter.notifyDataSetChanged()

        }

    }


}