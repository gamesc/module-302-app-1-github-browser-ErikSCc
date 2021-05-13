package com.example.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.model.Repository

class RepositoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)

        val listOfRepos=intent?.getParcelableArrayListExtra<Repository>("keyRepositoryData")
        listOfRepos.let{

        }
    }
    companion object{
        const val KEY_REPOSITORY_DATA="keyRepositoryData"
    }
}