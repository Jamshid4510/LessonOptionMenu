package com.example.rec2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val myAdaper = MyAdaper()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = myAdaper
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))

        recyclerView.setOnClickListener {
            Toast.makeText(this, "Nima gap", Toast.LENGTH_SHORT).show()
        }

        setData()

        val lac = LayoutAnimationController(AnimationUtils.loadAnimation(this, R.anim.anim_slide))
        lac.delay = 0.2f
        lac.order = LayoutAnimationController.ORDER_NORMAL
        recyclerView.layoutAnimation = lac


        myAdaper.onMenuClick = {view, position ->

            var menu = PopupMenu(this, view)
            var menuInflator = menu.menuInflater.inflate(R.menu.item_options, menu.menu)

            menu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.btnAdd -> {
                        myAdaper.models.add(position+1, User("This is ${myAdaper.models.size + 1}", "This is ${myAdaper.models.size + 1} description"))
                        myAdaper.notifyItemInserted(position + 1)
                        myAdaper.notifyItemRangeChanged(0, myAdaper.itemCount)
                    }
                    R.id.btnDelete -> {

                        val dialog = AlertDialog.Builder(this)
                            .setTitle("Tanla")
                            .setMessage("Aniq o'chirmoqchimisan")
                            .setPositiveButton("Ha"){dialogInterface, i ->
                                Toast.makeText(this, "Item ${myAdaper.models[position]} deleted", Toast.LENGTH_SHORT).show()
                                myAdaper.models.removeAt(position)
                                myAdaper.notifyItemRemoved(position)
                                myAdaper.notifyItemRangeChanged(0, myAdaper.itemCount)
                            }
                            .setNegativeButton("Yo'q"){dialogInterface, i ->
                                dialogInterface.dismiss()
                            }
                            .setNeutralButton("Bilmayman"){dialogInterface, i ->
                                dialogInterface.cancel()
                            }
                            dialog.show()

                    }
                }

                return@setOnMenuItemClickListener true
            }



            menu.show()
        }

    }

    private fun setData(){
        var models = arrayListOf<User>()

        for (i in 1..10){
            var model = User("This is $i title", "This is $i description")
            models.add(model)
        }

        myAdaper.models = models
    }

}