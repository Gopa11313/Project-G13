package net.gopaltech.project_g13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import net.gopaltech.project_g13.utils.SharedPref

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler().postDelayed({
            val sharedPref = SharedPref(this)
            if (sharedPref.getIsFirstTime()) {
                startActivity(Intent(this, RegisterActivity::class.java))
            } else {
                startActivity(Intent(this, WelcomeActivity::class.java))
            }

        }, 2500)
    }
}