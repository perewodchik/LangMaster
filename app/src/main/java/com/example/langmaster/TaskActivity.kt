package com.example.langmaster

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_task.view.*
import org.w3c.dom.Text

class TaskActivity : AppCompatActivity() {

    private var currentTask = 0
    private var livesCounter = 3
    var wordToGuess = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        val lives = findViewById<TextView>(R.id.livesCounter)
        val selectWord = findViewById<TextView>(R.id.selectWord)
        val imageText = arrayListOf(
            Pair(findViewById<ImageView>(R.id.imageChoice1), findViewById<TextView>(R.id.textDescription1)),
            Pair(findViewById<ImageView>(R.id.imageChoice2), findViewById<TextView>(R.id.textDescription3)),
            Pair(findViewById<ImageView>(R.id.imageChoice3), findViewById<TextView>(R.id.textDescription2)),
            Pair(findViewById<ImageView>(R.id.imageChoice4), findViewById<TextView>(R.id.textDescription4)))

        val extras = intent.extras
        var exerciseName = "default"
        if (extras != null) {
            exerciseName = extras.getString("Exercise").toString()
        }

        lives.text = getString(R.string.lives_d).format(livesCounter)

        //Creating task
        val tasks: ArrayList<Task> = when {
            exerciseName == "Fruits" -> tasksFruits
            exerciseName == "Furniture" -> tasksFurniture
            exerciseName == "Miscellaneous" -> tasksThird
            exerciseName == "Flowers" -> tasksFlowers
            else -> tasksFruits
        }
        updateActivity(imageText, tasks[currentTask].words, selectWord)

        for (i: Int in 0..3) {
            imageText[i].first.setOnClickListener {
                if(it.contentDescription == wordToGuess){
                    lives.text = getString(R.string.lives_d).format(livesCounter)
                    currentTask++
                    if(currentTask != tasks.size)
                        updateActivity(imageText, tasks[currentTask].words, selectWord)
                    else{
                        livesCounter = 3
                    }
                }
                else {
                    livesCounter--
                    lives.text = getString(R.string.lives_d).format(livesCounter)
                }
                if(livesCounter == 0) {
                    val intent: Intent = Intent(this, Result::class.java)
                    intent.putExtra("Result", "failed")
                    intent.putExtra("Exercise", exerciseName)
                    startActivity(intent)
                    finish()
                }
                if(currentTask == tasks.size) {
                    val intent: Intent = Intent(this, Result::class.java)
                    intent.putExtra("Result", "completed")
                    intent.putExtra("Exercise", exerciseName)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun updateActivity(imageText: ArrayList<Pair<ImageView, TextView>>,
        words: ArrayList<Word>, selectWord: TextView) {
        words.shuffle()
        wordToGuess = words[0].translatedWord
        words.shuffle()
        selectWord.text = getString(R.string.select_the_word_s).format(wordToGuess)
        for (i: Int in 0..3) {
            imageText[i].first.setImageResource(words[i].imageId)
            imageText[i].first.contentDescription = words[i].translatedWord
            imageText[i].second.text = words[i].originWord
        }
    }


    private val tasksFruits = arrayListOf(
        Task(TaskType.IMAGES, arrayListOf(
            Word("ostružina", "blackberry", R.drawable.blackberry),
            Word("krvavý pomeranč", "red orange", R.drawable.red_orange),
            Word("fík", "figs", R.drawable.figs),
            Word("angrešt", "gooseberry", R.drawable.gooseberry)
        )),
        Task(TaskType.IMAGES, arrayListOf(
            Word("mandarinka", "tangerine", R.drawable.tangerine),
            Word("broskev", "peach", R.drawable.peach),
            Word("švestka", "plum", R.drawable.plum),
            Word("kdoule", "quince", R.drawable.quince)
        )),
        Task(TaskType.IMAGES, arrayListOf(
            Word("mandarinka", "tangerine", R.drawable.tangerine),
            Word("vodní meloun", "watermelon", R.drawable.watermelon),
            Word("rybíz", "currant", R.drawable.currant),
            Word("jahoda", "strawberry", R.drawable.strawberry)
        )),
        Task(TaskType.IMAGES, arrayListOf(
            Word("broskev", "peach", R.drawable.peach),
            Word("ostružina", "blackberry", R.drawable.blackberry),
            Word("švestka", "plum", R.drawable.plum),
            Word("třešeň", "cherry", R.drawable.cherry)
        )),
        Task(TaskType.IMAGES, arrayListOf(
            Word("limetka", "lime", R.drawable.lime),
            Word("hrozen vína", "grapes", R.drawable.grapes),
            Word("meruňka", "apricot", R.drawable.apricot),
            Word("kdoule", "quince", R.drawable.quince)
        )),
        Task(TaskType.IMAGES, arrayListOf(
            Word("vodní meloun", "watermelon", R.drawable.watermelon),
            Word("broskev", "peach", R.drawable.peach),
            Word("fík", "figs", R.drawable.figs),
            Word("jahoda", "strawberry", R.drawable.strawberry)
        )),
        Task(TaskType.IMAGES, arrayListOf(
            Word("krvavý pomeranč", "red orange", R.drawable.red_orange),
            Word("rybíz", "peach", R.drawable.peach),
            Word("fík", "currant", R.drawable.currant),
            Word("angrešt", "gooseberry", R.drawable.gooseberry)
        )),
        Task(TaskType.IMAGES, arrayListOf(
            Word("limetka", "lime", R.drawable.lime),
            Word("rybíz", "peach", R.drawable.peach),
            Word("ostružina", "blackberry", R.drawable.blackberry),
            Word("angrešt", "gooseberry", R.drawable.gooseberry)
        )))


    private val tasksFurniture = arrayListOf(
        Task(TaskType.IMAGES, arrayListOf(
            Word("lůžkoviny", "bedding", R.drawable.bedding),
            Word("kolébka", "cradle", R.drawable.cradle),
            Word("záclona", "curtain", R.drawable.curtain),
            Word("odkládací stolek", "side table", R.drawable.side_table)
        )),
        Task(TaskType.IMAGES, arrayListOf(
            Word("odpadkový koš", "trash can", R.drawable.trash_can),
            Word("koberec", "carpet", R.drawable.carpet),
            Word("psací stůl", "desk", R.drawable.desk),
            Word("sedačka", "sofa", R.drawable.sofa)
        )),
        Task(TaskType.IMAGES, arrayListOf(
            Word("odpadkový koš", "trash can", R.drawable.trash_can),
            Word("trezor", "safe", R.drawable.safe),
            Word("knihovna", "library", R.drawable.library),
            Word("kolébka", "cradle", R.drawable.cradle)
        )),
        Task(TaskType.IMAGES, arrayListOf(
            Word("lůžkoviny", "bedding", R.drawable.bedding),
            Word("odpadkový koš", "trash can", R.drawable.trash_can),
            Word("kolébka", "cradle", R.drawable.cradle),
            Word("psací stůl", "desk", R.drawable.desk)
        )),
        Task(TaskType.IMAGES, arrayListOf(
            Word("záclona", "curtain", R.drawable.curtain),
            Word("koberec", "carpet", R.drawable.carpet),
            Word("trezor", "safe", R.drawable.safe),
            Word("odkládací stolek", "side table", R.drawable.side_table)
        )),
        Task(TaskType.IMAGES, arrayListOf(
            Word("psací stůl", "desk", R.drawable.desk),
            Word("psací stůl", "desk", R.drawable.desk),
            Word("sedačka", "sofa", R.drawable.sofa),
            Word("knihovna", "library", R.drawable.library)
        )))

    private val tasksThird = arrayListOf(
        Task(TaskType.IMAGES, arrayListOf(
            Word("řetěz", "chain", R.drawable.chain),
            Word("zahradní hadice", "garden hose", R.drawable.garden_hose),
            Word("hák", "hook", R.drawable.hook),
            Word("hřebík", "nail", R.drawable.nail)
        )),
        Task(TaskType.IMAGES, arrayListOf(
            Word("hasicí vůz", "fire truck", R.drawable.fire_truck),
            Word("motocyklistická přilba", "motorcycle helmet", R.drawable.motorcycle_helmet),
            Word("silnice", "road", R.drawable.road),
            Word("rozcestník", "signpost", R.drawable.signpost)
        )),
        Task(TaskType.IMAGES, arrayListOf(
            Word("člun", "boat", R.drawable.boat),
            Word("ruské kolo", "Ferris wheel", R.drawable.ferris_wheel),
            Word("šach", "game of chess", R.drawable.game_of_chess),
            Word("výprava", "expedition", R.drawable.expedition)
        )),
        Task(TaskType.IMAGES, arrayListOf(
            Word("námořnictvo", "navy", R.drawable.navy),
            Word("střelec", "shooter", R.drawable.shooter),
            Word("boj", "fighting", R.drawable.fighting),
            Word("útok", "attack", R.drawable.attack)
        )),
        Task(TaskType.IMAGES, arrayListOf(
            Word("řetěz", "chain", R.drawable.chain),
            Word("hasicí vůz", "fire truck", R.drawable.fire_truck),
            Word("člun", "boat", R.drawable.boat),
            Word("námořnictvo", "navy", R.drawable.navy)
        )),
        Task(TaskType.IMAGES, arrayListOf(
            Word("zahradní hadice", "garden hose", R.drawable.garden_hose),
            Word("motocyklistická přilba", "motorcycle helmet", R.drawable.motorcycle_helmet),
            Word("ruské kolo", "Ferris wheel", R.drawable.ferris_wheel),
            Word("střelec", "shooter", R.drawable.shooter)
        )),
        Task(TaskType.IMAGES, arrayListOf(
            Word("hák", "hook", R.drawable.hook),
            Word("silnice", "road", R.drawable.road),
            Word("šach", "game of chess", R.drawable.game_of_chess),
            Word("boj", "fighting", R.drawable.fighting)
        )),
        Task(TaskType.IMAGES, arrayListOf(
            Word("hřebík", "nail", R.drawable.nail),
            Word("rozcestník", "signpost", R.drawable.signpost),
            Word("výprava", "expedition", R.drawable.expedition),
            Word("útok", "attack", R.drawable.attack)
        )))

    private val tasksFlowers = arrayListOf(
        Task(TaskType.IMAGES, arrayListOf(
            Word("pampeliška", "dandelion", R.drawable.evangelion),
            Word("maceška", "pansy", R.drawable.pansy),
            Word("broskvoň", "peach-tree", R.drawable.peach_tree),
            Word("leknín", "waterlily", R.drawable.waterlily)
        )),
        Task(TaskType.IMAGES, arrayListOf(
            Word("slunečnice", "sunflower", R.drawable.sunflower),
            Word("chrpa", "cornflower", R.drawable.cornflower),
            Word("sedmikráska", "daisy", R.drawable.daisy),
            Word("bambus", "bamboo", R.drawable.bamboo)
        )),
        Task(TaskType.IMAGES, arrayListOf(
            Word("houba", "mushroom", R.drawable.mushroom),
            Word("sněženka", "snowdrop", R.drawable.snowdrop),
            Word("růže", "rose", R.drawable.rose),
            Word("olivovník", "olive", R.drawable.olive)
        )),
        Task(TaskType.IMAGES, arrayListOf(
            Word("pampeliška", "dandelion", R.drawable.evangelion),
            Word("slunečnice", "sunflower", R.drawable.sunflower),
            Word("houba", "mushroom", R.drawable.mushroom),
            Word("maceška", "pansy", R.drawable.pansy)
        )),
        Task(TaskType.IMAGES, arrayListOf(
            Word("chrpa", "cornflower", R.drawable.cornflower),
            Word("sněženka", "snowdrop", R.drawable.snowdrop),
            Word("broskvoň", "peach-tree", R.drawable.peach_tree),
            Word("sedmikráska", "daisy", R.drawable.daisy)
        )),
        Task(TaskType.IMAGES, arrayListOf(
            Word("růže", "rose", R.drawable.rose),
            Word("leknín", "waterlily", R.drawable.waterlily),
            Word("bambus", "bamboo", R.drawable.bamboo),
            Word("olivovník", "olive", R.drawable.olive)
        )))
}