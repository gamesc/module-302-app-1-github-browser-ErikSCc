package com.example.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.model.Repository

class RepositoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)

        val listOfRepos=intent?.getParcelableArrayListExtra<Repository>(KEY_REPOSITORY_DATA)
        listOfRepos?.let{

            val numberOfRepositories=getString(R.string.number_of_repos,it.size)

           findViewById<TextView>(R.id.textViewNumberOfRepos)?.text=numberOfRepositories
        }
    }
    companion object{
        const val KEY_REPOSITORY_DATA="keyRepositoryData"
    }
}