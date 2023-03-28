package net.gopaltech.project_g13

import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import net.gopaltech.project_g13.model.Lesson

class LessonAdapter(val context: Context, val data: ArrayList<Lesson>, val onclick: onclickList) :
    BaseAdapter() {
    private lateinit var itemCount: AppCompatTextView
    private lateinit var title: AppCompatTextView
    private lateinit var length: AppCompatTextView
    private lateinit var item: LinearLayout
    private lateinit var completeLesson: AppCompatImageView
    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(p0: Int): Any {
        return p0
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(parent: Int, p1: View?, grpupView: ViewGroup?): View? {
        val itemData = data.get(parent)
        var convertView = p1
        convertView = LayoutInflater.from(context).inflate(R.layout.lesson_item, grpupView, false)
        itemCount = convertView.findViewById(R.id.itemCount)
        title = convertView.findViewById(R.id.title)
        length = convertView.findViewById(R.id.length)
        completeLesson = convertView.findViewById(R.id.completeLesson)
        item = convertView.findViewById(R.id.item)

        itemCount.setText((parent + 1).toString())
        title.setText(itemData.name)
        length.setText("Length: ${itemData.length}")
        if (!itemData.isCompete) completeLesson.visibility = View.GONE

        item.setOnClickListener() {
            if (parent == 0) {
                onclick.click(itemData, parent, true)
            } else {
                onclick.click(itemData, parent, data.get(parent - 1).isCompete)
            }

        }
        return convertView
    }

    interface onclickList {
        fun click(lesson: Lesson, position: Int, checkSequent: Boolean)

    }
}