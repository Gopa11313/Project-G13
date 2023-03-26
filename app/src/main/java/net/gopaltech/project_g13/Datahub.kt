package net.gopaltech.project_g13

import net.gopaltech.project_g13.model.Lesson

class Datahub {
    companion object {
        private lateinit var instance: Datahub
        fun getInstance(): Datahub {
            synchronized(this) {
                if (!::instance.isInitialized) {
                    instance = Datahub()
                }
            }
            return instance
        }
    }

    var lessonData = ArrayList<Lesson>()
}