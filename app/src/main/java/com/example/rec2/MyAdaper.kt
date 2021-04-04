package com.example.rec2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

class MyAdaper() : Adapter<MyHolder>() {

    var models = arrayListOf<User>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onMenuClick : (view : View, position : Int) -> Unit = {view, position ->  }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val a = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyHolder(a)
    }

    override fun getItemCount(): Int {
        return models.size
    }


    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.populateModel(models[position], onMenuClick, position)
    }
}