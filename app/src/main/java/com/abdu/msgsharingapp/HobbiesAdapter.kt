package com.abdu.msgsharingapp

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.list_item.view.*

class HobbiesAdapter(val context: Context, val hobbies: List<Hobby>) : RecyclerView.Adapter<HobbiesAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hobbies.count()
    }

    override fun onBindViewHolder(viewHodler: MyViewHolder, pos: Int) {
        viewHodler.setData(hobbies[pos], pos)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var currentHobby: Hobby? = null
        var currentPos: Int = 0

        init {
            itemView.setOnClickListener {
                Toast.makeText(context, (currentHobby?.title ?: "title not available") + " clicked!", Toast.LENGTH_SHORT).show()
            }

            itemView.imgShare.setOnClickListener {
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT, currentHobby?.title ?: "title not available")
                intent.type = "text/plain"

                context.startActivity(Intent.createChooser(intent, "Choose App: "))
            }
        }

        fun setData(hobby: Hobby?, pos: Int) {
            itemView.txvTitle.text = hobby!!.title
            currentHobby = hobby
            currentPos = pos
        }
    }
}