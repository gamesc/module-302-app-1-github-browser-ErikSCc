package com.example.myapplication.activity

import android.content.Context
import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.activity.RepositoryActivity.Companion.KEY_REPOSITORY_DATA
import com.example.myapplication.backend.RetrofitClient
import com.example.myapplication.model.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), TextView.OnEditorActionListener {

    private var editTextUsername: EditText? = null
    private var progressBar: ProgressBar? = null
    private var imm: InputMethodManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextUsername = findViewById(R.id.editTextusername)
        progressBar = findViewById(R.id.progressBar)
        editTextUsername?.setOnEditorActionListener(this)
        imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    }

    override fun onResume() {
        super.onResume()
        editTextUsername?.setText("")
    }

    override fun onEditorAction(p0: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        return if (p0 == editTextUsername) {
            val username: String? = editTextUsername?.text?.trim().toString()
            if (username.isNullOrEmpty() || username.isNullOrBlank()) {
                editTextUsername?.error = getString(R.string.username_cannot_be_empty)
            } else {
                imm?.hideSoftInputFromWindow(editTextUsername?.windowToken, 0)
                progressBar?.visibility = View.VISIBLE
                getRepositoriesForUsername(username)
            }

            true
        } else {
            false
        }
    }

    private fun getRepositoriesForUsername(username: String) {
        RetrofitClient
            .instance
            .getRepositoriesForUsers(username)
            .enqueue(object : Callback<List<Repository>> {
                override fun onResponse(
                    call: Call<List<Repository>>,
                    response: Response<List<Repository>>
                ) {
                    progressBar?.visibility = View.INVISIBLE


                    if (response.isSuccessful) {

                        val listOfRepos = response.body() as? ArrayList<Repository>

                        listOfRepos?.let {

                            val intent = Intent(this@MainActivity, RepositoryActivity::class.java)

                            intent.putParcelableArrayListExtra(
                                RepositoryActivity.KEY_REPOSITORY_DATA,
                                it
                            )
                            startActivity(intent)
                        }
                    } else {
                        val message = when (response.code()) {
                            500 -> R.string.internal_server_error
                            401 -> R.string.unauthorized
                            403 -> R.string.forbidden
                            404 -> R.string.user_not_found
                            else -> R.string.try_onother_user
                        }

                        Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
                        Log.e(TAG, getString(message))

                    }
                }

                override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                    Log.e(TAG, "Error getting repos:${t.localizedMessage}")
                    Toast.makeText(
                        this@MainActivity,
                        R.string.unable_to_get_repositories,
                        Toast.LENGTH_LONG
                    ).show()
                }
            })

    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}

