package com.features.test_app_githubusersearch.app.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.features.test_app_githubusersearch.R
import com.features.test_app_githubusersearch.api.models.UserDataModel
import kotlinx.android.synthetic.main.item_user.view.*

class MainUsersAdapter(private val listener: UserClickListener): RecyclerView.Adapter<MainUsersAdapter.UsersViewHolder>() {

    private lateinit var context: Context
    private val dataArr = ArrayList<UserDataModel>()

    fun replaceAll(arr: ArrayList<UserDataModel>){
        dataArr.clear()
        if(arr.isNotEmpty()) {
            dataArr.addAll(arr)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        this.context = parent.context
        return UsersViewHolder(LayoutInflater.from(context).inflate(R.layout.item_user, parent, false))
    }

    override fun getItemCount(): Int { return dataArr.size }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val item = dataArr[position]

        holder.name.text = item.name
        holder.repos.text = String.format(context.getString(R.string.repos), item.public_repos)

        Glide.with(context)
            .load(item.avatar_url)
            .centerCrop()
            .into(holder.avatar)

        holder.itemView.setOnClickListener {
            listener.onUserClick(item)
        }
    }

    class UsersViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val avatar: ImageView = v.iv_user_avatar
        val name: TextView = v.tv_user_name
        val repos: TextView = v.tv_user_repos
    }

    interface UserClickListener {
        fun onUserClick(user: UserDataModel)
    }
}