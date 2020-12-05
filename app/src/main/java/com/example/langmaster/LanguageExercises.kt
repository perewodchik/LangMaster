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
        //val imageView = findViewById<ImageView>(R.id.)

        val extras = intent.extras
        if (extras != null) {
            val value = extras.getString("chosenLanguage")
            textLanguage.text = value
        }

        val layoutManager: LinearLayoutManager = LinearLayoutManager(this)
        val recyclerView: RecyclerView = findViewById<RecyclerView>(R.id.exercicesRecycler)
        val data: ArrayList<Exercise> = ArrayList();

        data.add(Exercise("Fruits", R.drawable.exercisefruits))
        data.add(Exercise())
        data.add(Exercise())
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()

        val adapter = ExerciseAdapter(data)
        recyclerView.adapter = adapter
    }

}