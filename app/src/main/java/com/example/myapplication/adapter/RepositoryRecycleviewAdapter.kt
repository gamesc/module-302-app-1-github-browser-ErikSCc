package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Repository
import kotlinx.android.synthetic.main.layout_recycler_view_repository.view.*

class RepositoryRecycleviewAdapter(private val listOfRepo: ArrayList<Repository>) :
    RecyclerView.Adapter<RepositoryRecycleviewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(listOfRepo[position])
    }

    override fun getItemCount(): Int {
        return listOfRepo.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var textViewRepoName = view.findViewById<TextView>(R.id.textViewRepoName)
        private var textViewPrivacy = view.findViewById<TextView>(R.id.textViewPrivacy)
        private var textViewLicense = view.findViewById<TextView>(R.id.textViewLicense)
        fun bind(repository: Repository) {
            textViewRepoName?.text = repository.name
            textViewLicense?.text = repository.license?.name
            val privacyType = when (repository.private) {
                true -> R.string.private_repo
                else -> R.string.public_repo
            }
            textViewPrivacy?.setText(privacyType)
        }

        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_recycler_view_repository, parent, false)
                return ViewHolder(view)

            }
        }
    }
}