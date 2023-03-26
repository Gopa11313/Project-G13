package net.gopaltech.project_g13.model

import java.io.Serializable

class Lesson(
    val id:String,
    val name: String,
    val length: String,
    val description: String,
    val videoLink: String,
    val Notes: String?,
    var isCompete: Boolean
) : Serializable {
}