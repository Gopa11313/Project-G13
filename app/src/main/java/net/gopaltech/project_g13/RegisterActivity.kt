package net.gopaltech.project_g13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import net.gopaltech.project_g13.utils.SharedPref

class RegisterActivity : AppCompatActivity() {
    private lateinit var registerBtn: AppCompatButton
    private lateinit var name: AppCompatEditText
    private lateinit var sharedPref: SharedPref
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        init()
        onclick()
    }

    fun init() {
        registerBtn = findViewById(R.id.registerBtn)
        name = findViewById(R.id.name)
        sharedPref = SharedPref(this)
    }

    fun onclick() {
        registerBtn.setOnClickListener() {
            if (!name.text.toString().isNullOrEmpty()) {
                sharedPref.setName(name.text.toString())
                sharedPref.setIsFirstTime(false)
                startActivity(Intent(this, LessonListActivity::class.java))
            } else {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            }

        }
    }
}