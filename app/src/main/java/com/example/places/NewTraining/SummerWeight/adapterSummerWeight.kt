package com.example.places.NewTraining.SummerWeight

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.places.R


class adapterSummerWeight(var userList: ArrayList<DatalistSummerWeight>): RecyclerView.Adapter<adapterSummerWeight.MyViewHolder>() {




    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val tScoreSummerWeight: TextView = itemView.findViewById(R.id.textItogWeight)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_attitude, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.tScoreSummerWeight.text = currentItem.score
    }

}