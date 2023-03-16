package com.mexicandeveloper.jpmcexercise.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import java.util.*


@BindingAdapter("setImage")
fun ImageView.setImage(borough:String){

        val trimed = borough.trim { it <= ' ' }
        val resource= when(trimed) {
            "BROOKLYN" -> android.R.drawable.ic_menu_share
            "MANHATTAN" -> android.R.drawable.ic_dialog_map
            "BRONX" ->  android.R.drawable.ic_menu_delete
            "QUEENS" -> android.R.drawable.ic_menu_myplaces
            "STATEN IS" ->  android.R.drawable.presence_online
            else -> android.R.drawable.ic_menu_mylocation
        }
       this.setImageDrawable(this.context.getDrawable(resource))

}