package com.example.places.NewTraining.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.places.NewTraining.DB.DatalistNewAttitude
import com.example.places.R

class MyAdapterNewTraining(var userList: ArrayList<DatalistNewAttitude>): RecyclerView.Adapter<MyAdapterNewTraining.MyViewHolder>() {


    private lateinit var mListener: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun OnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }
    class MyViewHolder(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView)
    {
        val tNameAttitude: TextView = itemView.findViewById(R.id.textViewNameAttitude)
        val tWeightAttitude: TextView = itemView.findViewById(R.id.textViewWeightAttitude)
        val textViewTypeAttitude: TextView = itemView.findViewById(R.id.textViewTypeAttitude)
        init{
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_attitude, parent, false)
        return MyViewHolder(itemView, mListener)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.tNameAttitude.text = currentItem.name
        holder.tWeightAttitude.text = currentItem.weight
        holder.textViewTypeAttitude.text = currentItem.type
    }

}