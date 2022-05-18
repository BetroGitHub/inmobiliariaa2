package com.grupoJuan.inmobiliariaJuan

import android.content.Context
import org.json.JSONException
import org.json.JSONObject


class Casa(
  val title: String,
  val description: String,
  val imageUrl: String,
  val price: String,
  val url:String
) {

  companion object {

    fun getCasasDelFichero(filename: String, context: Context): ArrayList<Casa> {
      val listaCasas = ArrayList<Casa>()

      try {
        // Load data
        val jsonString = loadJsonFromAsset("casas.json", context)
        val json = JSONObject(jsonString)
        val recipes = json.getJSONArray("casas")

        // Get Recipe objects from data
        (0 until recipes.length()).mapTo(listaCasas) {
          Casa(
            recipes.getJSONObject(it).getString("title"),
            recipes.getJSONObject(it).getString("description"),
            recipes.getJSONObject(it).getString("image"),
            recipes.getJSONObject(it).getString("price"),
            recipes.getJSONObject(it).getString("url")
          )
        }
      } catch (e: JSONException) {
        e.printStackTrace()
      }

      return listaCasas
    }

    private fun loadJsonFromAsset(filename: String, context: Context): String? {
      var json: String? = null

      try {
        val inputStream = context.assets.open(filename)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        json = String(buffer, Charsets.UTF_8)
      } catch (ex: java.io.IOException) {
        ex.printStackTrace()
        return null
      }

      return json
    }
  }
}