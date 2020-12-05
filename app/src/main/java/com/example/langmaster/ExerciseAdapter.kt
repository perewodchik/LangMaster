package com.example.langmaster

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExerciseAdapter(val data: ArrayList<Exercise>) :
    RecyclerView.Adapter<ExerciseAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageIcon = itemView.findViewById<ImageView>(R.id.cardLogo)
        val title = itemView.findViewById<TextView>(R.id.cardTitle)
        val description = itemView.findViewById<TextView>(R.id.cardDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.exercise_card, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val title = holder.title
        val description = holder.description
        val imageIcon = holder.imageIcon

        title.text = data.get(position).exerciseName
        imageIcon.setImageResource(data.get(position).imageId)
        description.text = "description"
    }

    override fun getItemCount(): Int {
        return data.size
    }
}