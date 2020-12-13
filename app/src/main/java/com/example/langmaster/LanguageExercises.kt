package com.example.langmaster

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LanguageExercises : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language_exercises)

        val textLanguage = findViewById<TextView>(R.id.textLanguage)


        val layoutManager: LinearLayoutManager = LinearLayoutManager(this)
        val recyclerView: RecyclerView = findViewById<RecyclerView>(R.id.exercicesRecycler)
        val data: ArrayList<Exercise> = ArrayList();

        // Creating tasks



        data.add(Exercise("Fruits", R.drawable.exercisefruits, "Fruits, berries and vegetables"))
        data.add(Exercise("Furniture", R.drawable.furniture, "Beds, desks and chairs") )
        data.add(Exercise("Miscellaneous", R.drawable.nail, "Literally everything"))
        data.add(Exercise("Flowers", R.drawable.rose, "Beautiful flowers"))
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()

        val adapter = ExerciseAdapter(data)
        recyclerView.adapter = adapter
    }

}

