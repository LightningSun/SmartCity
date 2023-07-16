package com.example.smartcity.view.home.adapter

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.youth.banner.adapter.BannerAdapter

class ImageBannerAdapter(dataList: MutableList<String> = mutableListOf()) :
    BannerAdapter<String, ImageBannerAdapter.Holder>(
        dataList
    ) {
    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): Holder {
        val imageView = ImageView(parent!!.context)
        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return Holder(imageView)
    }

    override fun onBindView(holder: Holder, data: String?, position: Int, size: Int) {
        Glide.with(holder.imageView)
            .load(data)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.e("Glide", "Load failed", e)
                    return false // Let Glide handle the error
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.d("Glide", "Load success")
                    return false // Let Glide handle the success
                }

            })
            .into(holder.imageView)

    }


    class Holder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)
}
