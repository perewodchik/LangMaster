package com.example.langmaster

class Task(val taskType: TaskType,
           var words: ArrayList<Word>){
}

enum class TaskType(val type: Int) {
    IMAGES(0),
    BUTTONS(1)
}