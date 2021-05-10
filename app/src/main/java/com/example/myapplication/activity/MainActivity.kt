package com.example.myapplication.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.example.myapplication.R

class MainActivity : AppCompatActivity(),TextView.OnEditorActionListener {

    private var editTextUsername:EditText?=null
    private var progressBar:ProgressBar?=null
    private var imm: InputMethodManager?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextUsername=findViewById(R.id.editTextusername)
        progressBar=findViewById(R.id.progressBar)
        editTextUsername?.setOnEditorActionListener(this)
        imm=getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    }

    override fun onEditorAction(p0: TextView?, actionId: Int, event: KeyEvent?): Boolean {
     return if (p0==editTextUsername){
         val username:String?=editTextUsername?.text?.trim().toString()
         if(username.isNullOrEmpty() || username.isNullOrBlank()){
             editTextUsername?.error=getString(R.string.username_cannot_be_empty)
         } else{
             imm?.hideSoftInputFromWindow(editTextUsername?.windowToken,0)
             progressBar?.visibility= View.VISIBLE
             getRepositoriesForUsername(username)
         }

         true } else{
             false}
     }
    private fun getRepositoriesForUsername(username:String)
    {}
    }

