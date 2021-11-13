package com.example.mobilework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit: Retrofit =  Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val gitNetwork: NetworkingGitHub = retrofit.create(NetworkingGitHub::class.java)
        val login = "ElerGard"
        val repos = "Mobile_Networking"

        gitNetwork.getUser(login).enqueue(object: Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {

            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                val user: User? = response.body()

                val login: TextView = findViewById<View>(R.id.textView) as TextView
                login.text = "GitHub login: " + user?.login

                val imageView: ImageView = findViewById<View>(R.id.imageView) as ImageView

                Picasso.get()
                    .load(user?.avatar_url)
                    .placeholder(R.drawable.avatar_1)
                    .error(android.R.drawable.alert_dark_frame)
                    .into(imageView)
            }
        })

        gitNetwork.getRepos(login, repos).enqueue(object: Callback<Repos> {
            override fun onFailure(call: Call<Repos>, t: Throwable) {

            }

            override fun onResponse(call: Call<Repos>, response: Response<Repos>) {
                val rep: Repos? = response.body()

                val reposName: TextView = findViewById<View>(R.id.textView2) as TextView
                reposName.text = "Repository name: " + rep?.name_rep

                val reposInfo: TextView = findViewById<View>(R.id.textView3) as TextView
                reposInfo.text = "Last update repository: " + rep?.last_update_rep?.substring(0,10)


            }
        })

    }


}