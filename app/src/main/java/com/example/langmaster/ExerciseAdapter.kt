package com.example.langmaster

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.exercise_card.view.*

class ExerciseAdapter(val data: ArrayList<Exercise>) :
    RecyclerView.Adapter<ExerciseAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageIcon = itemView.findViewById<ImageView>(R.id.cardLogo)
        val title = itemView.findViewById<TextView>(R.id.cardTitle)
        val description = itemView.findViewById<TextView>(R.id.cardDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.exercise_card, parent, false)

        view.setOnClickListener {
            val intent: Intent = Intent(parent.context, TaskActivity::class.java)
            intent.putExtra("Exercise", view.cardTitle.text)
            parent.context.startActivity(intent)
        }

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val title = holder.title
        val description = holder.description
        val imageIcon = holder.imageIcon

        title.text = data.get(position).exerciseName
        imageIcon.setImageResource(data.get(position).imageId)
        description.text = data.get(position).description
    }

    override fun getItemCount(): Int {
        return data.size
    }
}