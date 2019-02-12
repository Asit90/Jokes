@file:Suppress("DEPRECATION")

package com.example.jokes

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.VolleyLog
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

@Suppress("DEPRECATION")
class Category : AppCompatActivity() {

    internal lateinit var category: String
    internal lateinit var image: ImageView
    internal lateinit var textView: TextView
    internal var progressDialog: ProgressDialog? = null
    internal lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        category = intent.getStringExtra("category")
        Log.d("category","aaa"+category)
        download_joke(category)

        button = findViewById(R.id.next_joke)
        image = findViewById(R.id.icon_url)
        textView = findViewById(R.id.value)

        button.setOnClickListener { download_joke(category) }

    }

    private fun download_joke(category: String?) {

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, "http://api.icndb.com/jokes/random/10?limitTo=$category", null,
            Response.Listener { response ->
                Log.d("response", "aaaaa" + response!!)
                if (response == null) {
                    Toast.makeText(this@Category, "No Internet Try again!", Toast.LENGTH_SHORT).show()
                    return@Listener
                }
                try {
                    val strResp = response.toString()
                    val jsonObj: JSONObject = JSONObject(strResp)
                    val jsonArray: JSONArray = jsonObj.getJSONArray("value")
                    var str_joke: String = ""
                    for (i in 0 until jsonArray.length()) {
                        var jsonInner: JSONObject = jsonArray.getJSONObject(i)
                        str_joke = str_joke + "\n" + jsonInner.get("joke")
                    }
                    textView!!.text = str_joke

//                    textView.text = strResp
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { error ->
                VolleyLog.d("error", "Error: " + error.message)
                Log.d("error","aaa"+error.message);
            })
        val q = Volley.newRequestQueue(applicationContext)
        q.add(jsonObjectRequest)

    }
}