package com.example.places.Trainings.Trainings.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.places.NewTraining.Adapter.MyAdapterNewTraining
import com.example.places.NewTraining.DB.DatalistNewAttitude
import com.example.places.R
import com.example.places.Trainings.Trainings.DB.DatalistTraining

class MyAdapterTrainings(var userList: ArrayList<DatalistTraining>): RecyclerView.Adapter<MyAdapterTrainings.MyViewHolder>() {

    private lateinit var mListener: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun OnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }
    class MyViewHolder(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView)
    {
        val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        val textViewWeight: TextView = itemView.findViewById(R.id.textViewWeight)
        val textViewDate: TextView = itemView.findViewById(R.id.textViewDate)
        init{
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_trainings, parent, false)
        return MyViewHolder(itemView, mListener)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.textViewName.text = currentItem.name
        holder.textViewWeight.text = currentItem.weight
        holder.textViewDate.text = currentItem.date
    }

}