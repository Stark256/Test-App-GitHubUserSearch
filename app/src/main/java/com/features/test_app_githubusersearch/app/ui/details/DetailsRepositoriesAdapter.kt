package com.features.test_app_githubusersearch.app.ui.details

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.features.test_app_githubusersearch.R
import com.features.test_app_githubusersearch.api.models.RepoModel
import com.features.test_app_githubusersearch.api.models.UserDataModel
import kotlinx.android.synthetic.main.item_repo.view.*

class DetailsRepositoriesAdapter(private val listener: RepoClickListener): RecyclerView.Adapter<DetailsRepositoriesAdapter.RepoViewHolder>() {

    private lateinit var context: Context
    private val dataArr = ArrayList<RepoModel>()

    fun replaceAll(arr: ArrayList<RepoModel>){
        dataArr.clear()
        if(arr.isNotEmpty()) {
            dataArr.addAll(arr)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        this.context = parent.context
        return RepoViewHolder(LayoutInflater.from(context).inflate(R.layout.item_repo, parent, false))
    }

    override fun getItemCount(): Int { return dataArr.size }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val item = dataArr[position]

        holder.repoName.text = item.name
        holder.repoForks.text = String.format(context.getString(R.string.forks), item.forks_count.toString())
        holder.repoStars.text = String.format(context.getString(R.string.stars), item.stargazers_count.toString())

        holder.itemView.setOnClickListener {
            listener.onRepoClick(item.url)
        }
    }

    class RepoViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val repoName: TextView = v.tv_repo_name
        val repoForks: TextView = v.tv_repo_forks
        val repoStars: TextView = v.tv_repo_stars
    }

    interface RepoClickListener {
        fun onRepoClick(repoUrl: String)
    }
}