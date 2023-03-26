package net.gopaltech.project_g13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import net.gopaltech.project_g13.model.Lesson
import net.gopaltech.project_g13.utils.SharedPref
import kotlin.math.log

class LessonListActivity : AppCompatActivity(), LessonAdapter.onclickList {
    private lateinit var lessionList: ListView
    private lateinit var sharedPref: SharedPref
    private lateinit var adapter:LessonAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_list)
        sharedPref = SharedPref(this)
        setData()
        val data = Datahub.getInstance().lessonData
        adapter = LessonAdapter(this, data, this)
        lessionList = findViewById<ListView>(R.id.lessionList)

        lessionList.adapter = adapter
    }

    override fun click(lesson: Lesson) {
        val data = lesson
        println(data)
        val intent = Intent(this, LessonDetailsActivity::class.java)
        intent.putExtra("data", data)
        startActivity(intent)
    }

    fun setData() {
        val datahub = Datahub.getInstance()

        val lesson1 = Lesson(
            "1",
            "Introduction to Kotlin",
            "120 hours",
            "Kotlin is a cross-platform, statically typed, general-purpose high-level programming language with type inference. Kotlin is designed to interoperate fully with Java, and the JVM version of Kotlin's standard library depends on the Java Class Library, but type inference allows its syntax to be more concise",
            "https://www.youtube.com/watch?v=F9UC9DY-vIU",
            null,
            false
        )
        val lesson2 = Lesson(
            "2",
            "Android Application",
            "220 hours",
            "Android is a mobile operating system based on a modified version of the Linux kernel and other open-source software, designed primarily for touchscreen mobile devices such as smartphones and tablets. ",
            "https://www.youtube.com/watch?v=gnKD7pPj-DI",
            null,
            false
        )
        val lesson3 = Lesson(
            "3",
            "Data Structure and Algorithm",
            "120 hours",
            "A data structure is a named location that can be used to store and organize data. And, an algorithm is a collection of steps to solve a particular problem.",
            "https://www.youtube.com/watch?v=8hly31xKli0",
            null,
            false
        )

        val lesson4 = Lesson(
            "4",
            "Swift Fundamental",
            "110 horus",
            "Swift Fundamentals. by Simon Allardice. Swift is the modern, fast, and safe programming language that has rapidly become the first choice for building iOS and macOS apps.",
            "https://www.youtube.com/watch?v=Gqds8209TtA",
            null,
            false
        )
        val lesson5 = Lesson(
            "5",
            "Coding World",
            "100horus",
            "Common styles are imperative, functional, logical, and object-oriented languages. Programmers can choose from these coding language paradigms to best-serve their needs for a specific project.",
            " https://www.youtube.com/watch?v=N7ZmPYaXoic",
            null,
            false
        )
        val lessonData = datahub.lessonData
        lessonData.add(lesson1)
        lessonData.add(lesson2)
        lessonData.add(lesson3)
        lessonData.add(lesson4)
        lessonData.add(lesson5)
    }

    override fun onResume() {

        super.onResume()
    }

    fun setDataToSharedPref(data: ArrayList<Lesson>) {
        if (data != null) {
            var completedCount = 0
            sharedPref.setTotalLessonCount(data.size)

            for (i in data) {
                if (i.isCompete) {
                    completedCount += 1
                }
            }
            sharedPref.setCompletedCount(completedCount)
        }


    }

    override fun onStart() {
        Log.d(null, "Entered Stated")
        val data = Datahub.getInstance().lessonData
        setDataToSharedPref(data)
        adapter.notifyDataSetChanged()
        super.onStart()
    }
}