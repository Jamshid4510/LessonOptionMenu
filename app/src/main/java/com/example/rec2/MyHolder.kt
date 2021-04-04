package com.example.rec2

import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item.view.*

class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvTitle = itemView.tvTitle
    val tvDescription = itemView.tvDescription

    fun populateModel(user : User, onMenuClick : (view : View, position : Int) -> Unit, position: Int){
        tvTitle.text = user.title
        tvDescription.text = user.description

        itemView.btnOptions.setOnClickListener {
            onMenuClick.invoke(it, position)
        }

    }
}