package net.gopaltech.project_g13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.OnClickAction
import android.view.View
import android.view.View.OnClickListener
import android.webkit.WebView
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.net.toUri
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import net.gopaltech.project_g13.model.Lesson
import net.gopaltech.project_g13.utils.SharedPref
import java.lang.reflect.Type

class LessonDetailsActivity : AppCompatActivity() {
    lateinit var title: AppCompatTextView
    lateinit var length: AppCompatTextView
    lateinit var description: AppCompatTextView
    lateinit var notes: AppCompatEditText
    lateinit var saveNotesButton: AppCompatButton
    lateinit var makeComplete: AppCompatButton
    lateinit var sharedPref: SharedPref
    lateinit var data: Lesson
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_details)
        val intent = intent
        init()
        if (intent != null) {
            data = intent.getSerializableExtra("data") as Lesson
            title.setText(data.name)
            length.setText("Length ${data.length}")
            description.setText(data.description)
        }
        onClick()

    }

    fun init() {
        title = findViewById(R.id.title)
        length = findViewById(R.id.length)
        description = findViewById(R.id.description)
        notes = findViewById(R.id.notes)
        saveNotesButton = findViewById(R.id.saveNotesButton)
        makeComplete = findViewById(R.id.makeComplete)
        sharedPref = SharedPref(this)
    }

    fun onClick() {
        saveNotesButton.setOnClickListener() {
            val notesArray = sharedPref.getNotes()
            var newData = ArrayList<String>()
            val gson = GsonBuilder().create()

            if (!notesArray.isNullOrEmpty()) {
                val type: Type = object : TypeToken<ArrayList<Lesson?>?>() {}.type
                newData = gson.fromJson<ArrayList<String>>(
                    notesArray,
                    object : TypeToken<ArrayList<String>>() {}.type
                )
            }
            if (!notes.text.toString().isNullOrEmpty()) {
                Toast.makeText(this, "You have successfully made a note", Toast.LENGTH_SHORT).show()
                newData.add(notes.text.toString())
                sharedPref.setNotes(gson.toJson(newData))
                notes.setText("")

            } else {
                Toast.makeText(this, "Please write some note", Toast.LENGTH_SHORT).show()
            }
        }
        makeComplete.setOnClickListener() {
            val datahub = Datahub.getInstance()
            for (i in 0..datahub.lessonData.size) {
                if (data.id == datahub.lessonData.get(i).id) {
                    datahub.lessonData.get(i).isCompete = true
                    break
                }
            }
            Toast.makeText(
                this,
                "You have successfully marked this lesson as complete",
                Toast.LENGTH_SHORT
            ).show()
            startActivity(Intent(this, LessonListActivity::class.java))
        }
    }

}