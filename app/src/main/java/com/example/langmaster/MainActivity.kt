package com.example.langmaster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        val continueButton = findViewById<Button>(R.id.buttonContinue);
        continueButton.setOnClickListener {
            val intent: Intent = Intent(this, LanguageExercises::class.java)
            intent.putExtra("chosenLanguage", chosenLanguage)
            startActivity(intent)
        }


    }
    var chosenLanguage = "czech";

}