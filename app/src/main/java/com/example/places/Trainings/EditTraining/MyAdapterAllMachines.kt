package com.example.places.Trainings.EditTraining

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.places.R
import com.example.places.Trainings.Trainings.DB.DatalistTrainings


class MyAdapterAllMachines(var userList: ArrayList<DatalistItemTrainer>): RecyclerView.Adapter<MyAdapterAllMachines.MyViewHolder>() {

//    Listener

//    private lateinit var mListener: onItemClickListener
//    interface onItemClickListener{
//        fun onItemClick(position: Int)
//    }
//    fun OnItemClickListener(listener: onItemClickListener){
//        mListener = listener
//    }

//    class MyViewHolder(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView)
//    {
//        val tname: TextView = itemView.findViewById(R.id.textView)
//
//        init{
//            itemView.setOnClickListener {
//                listener.onItemClick(adapterPosition)
//            }
//        }
//
//    }
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val tname: TextView = itemView.findViewById(R.id.textView)
        val tname2: TextView = itemView.findViewById(R.id.textView2)
    }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_trainer, parent, false)
            return MyViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return userList.size
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val currentItem = userList[position]
            holder.tname.text = currentItem.name
            holder.tname2.text = currentItem.name2
        }

}