package com.fearaujo.data

import com.fearaujo.model.Response
import com.google.gson.Gson
import java.io.InputStream
import java.util.*

class JsonDeserializer(private val gson: Gson) {

    fun deserializeFile(fileName: String): Response {
        val json = getJson(fileName)
        return gson.fromJson(json, Response::class.java)
    }

    private fun getJson(path: String?): String {
        return path?.let {
            val jsonFile = this.javaClass.classLoader!!.getResourceAsStream(path)
            convertStreamToString(jsonFile)
        } ?: run {
            ""
        }

    }

    private fun convertStreamToString(inputStream: InputStream): String {
        val s = Scanner(inputStream).useDelimiter("\\A")
        return if (s.hasNext()) s.next() else ""
    }

}