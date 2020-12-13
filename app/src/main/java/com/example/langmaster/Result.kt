package com.example.langmaster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.exercise_card.view.*

class Result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val image = findViewById<ImageView>(R.id.resultImage)
        val text = findViewById<TextView>(R.id.resultText)
        val tryButton = findViewById<Button>(R.id.tryAgainButton)
        val continueButton = findViewById<Button>(R.id.continueButton)

        image.setImageResource(R.drawable.jojo_victory)
        val extras = intent.extras
        var result = "You have completed the exercise!"
        var exerciseName = "default"
        if (extras != null) {
            val value = extras.getString("Result")
            if(value == "failed") {
                result = "You have failed the exercise. Try again?"
                image.setImageResource(R.drawable.try_again)
            }
            exerciseName = extras.getString("Exercise").toString()
        }
        tryButton.setOnClickListener {
            val intent: Intent = Intent(this, TaskActivity::class.java)
            intent.putExtra("Exercise", exerciseName)
            startActivity(intent)
            finish()
        }
        continueButton.setOnClickListener {
            finish()
        }
        text.text = result

    }
}