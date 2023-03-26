package net.gopaltech.project_g13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import net.gopaltech.project_g13.utils.SharedPref

class WelcomeActivity : AppCompatActivity() {
    private lateinit var registerBtn: AppCompatButton
    private lateinit var name: AppCompatTextView
    private lateinit var sharedPref: SharedPref
    private lateinit var completedLesson: AppCompatTextView
    private lateinit var unCompletedLesson: AppCompatTextView
    private lateinit var reset: AppCompatTextView
    private lateinit var topMessage: AppCompatTextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        init()
        onclicks()
    }

    fun init() {
        name = findViewById(R.id.name)
        registerBtn = findViewById(R.id.continueBtn)
        completedLesson = findViewById(R.id.completedLesson)
        unCompletedLesson = findViewById(R.id.unCompletedLesson)
        reset = findViewById(R.id.reset)
        topMessage = findViewById(R.id.topMessage)
        sharedPref = SharedPref(this)
        val completedLessonCount =sharedPref.getCompletedCount().toDouble()
        val totalLesson = sharedPref.getTotalLessonCount().toDouble()
        val completePercentage:Double =completedLessonCount.div(totalLesson)*100
        topMessage.setText("You have completed $completePercentage% of your lesson")
        completedLesson.setText(sharedPref.getCompletedCount().toString())
        unCompletedLesson.setText((sharedPref.getTotalLessonCount() - sharedPref.getCompletedCount()).toString())
        name.setText("Welcome, ${sharedPref.getName()}")

    }

    fun onclicks() {
        registerBtn.setOnClickListener() {
            startActivity(Intent(this, LessonListActivity::class.java))
        }
        reset.setOnClickListener() {
            popupDialogBox()
        }
    }

    fun popupDialogBox() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Clear All Data")
        builder.setMessage("Are You Sure You Want To Clear All Data From Your Device!!")

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            Toast.makeText(this, "You Have Successfully Cleared All Data", Toast.LENGTH_SHORT)
                .show()
            sharedPref.clear()
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            dialog.dismiss()
        }

        builder.show()
    }
}