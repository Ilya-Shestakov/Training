package com.example.places.Trainings.Trainings.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.places.R
import com.example.places.Trainings.Trainings.DB.DatalistTrainings
import org.w3c.dom.Text


class MyAdapterTainings(var userList: ArrayList<DatalistTrainings>): RecyclerView.Adapter<MyAdapterTainings.MyViewHolder>() {

    private lateinit var mListener: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun OnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    class MyViewHolder(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView)
    {
        val tname: TextView = itemView.findViewById(R.id.textViewName)
        val tdate: TextView = itemView.findViewById(R.id.textViewDate)
        val tweight: TextView = itemView.findViewById(R.id.textViewWeight)
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
        holder.tname.text = currentItem.name
        holder.tdate.text = currentItem.date
        holder.tweight.text = currentItem.weight
    }

}