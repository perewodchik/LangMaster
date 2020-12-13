package com.example.langmaster

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat.startActivity

class ExerciseOnClickListener(private val context: Context): View.OnClickListener {

    override fun onClick(view: View?) {
        val intent: Intent = Intent(context, TaskActivity::class.java)
        context.startActivity(intent)
    }
}