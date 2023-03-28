package net.gopaltech.project_g13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.widget.AppCompatImageView
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import net.gopaltech.project_g13.model.Lesson
import net.gopaltech.project_g13.utils.SharedPref
import java.lang.reflect.Type

class MyNotesActivity : AppCompatActivity() {
    private lateinit var notesList: ListView
    private lateinit var noData: AppCompatImageView
    private lateinit var sharedPref: SharedPref
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_notes)
        init()
        setData()

    }

    fun init() {
        notesList = findViewById(R.id.notesList)
        noData = findViewById(R.id.noData)
        sharedPref = SharedPref(this)
    }

    fun setData() {
        var data = sharedPref.getNotes()
        var newData = ArrayList<String>()
        if (!data.isNullOrEmpty()) {
            noData.visibility = View.GONE
            notesList.visibility = View.VISIBLE
            val gson = GsonBuilder().create()
            val type: Type = object : TypeToken<ArrayList<Lesson?>?>() {}.type
            newData = gson.fromJson<ArrayList<String>>(
                data,
                object : TypeToken<ArrayList<String>>() {}.type
            )

            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, newData)
            notesList.adapter = arrayAdapter
        }
    }
}