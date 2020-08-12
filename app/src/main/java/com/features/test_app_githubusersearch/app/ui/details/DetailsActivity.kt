package com.features.test_app_githubusersearch.app.ui.details

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.features.test_app_githubusersearch.R
import com.features.test_app_githubusersearch.api.models.RepoModel
import com.features.test_app_githubusersearch.api.models.UserDataModel
import com.features.test_app_githubusersearch.app.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class DetailsActivity : BaseActivity() {

    companion object {
        val EXTRA_USER_DETAIL = "user_detail"

        fun newInstance(
            context: Context,
            userDataModel: UserDataModel) : Intent {
            return Intent(context, DetailsActivity::class.java)
                .apply {
                    putExtra(EXTRA_USER_DETAIL, userDataModel)
                }
        }
    }

    @Inject
    lateinit var factory: DetailsViewModelFactory
    private lateinit var viewModel: DetailsViewModel
    private lateinit var adapter: DetailsRepositoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        appComponent.inject(this)
        this.viewModel = ViewModelProvider(viewModelStore, this.factory).get(DetailsViewModel::class.java)

        this.adapter = DetailsRepositoriesAdapter(object : DetailsRepositoriesAdapter.RepoClickListener {
            override fun onRepoClick(repoUrl: String) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(repoUrl)))
            }
        })
        rv_details_repos.adapter = adapter
        rv_details_repos.layoutManager = LinearLayoutManager(this)

        this.viewModel.repos.observe(this@DetailsActivity, Observer<ArrayList<RepoModel>> {
            adapter.replaceAll(it)
        })

        initView()
    }

    fun initView() {
        (intent.extras?.getSerializable(EXTRA_USER_DETAIL) as? UserDataModel)?.let {

            Glide.with(this)
                .load(it.avatar_url)
                .centerCrop()
                .into(iv_details_avatar)



            tv_details_name.text = it.name


            if(it.email.isNullOrBlank()) {
                tv_details_email.text = "-"
            } else {
                tv_details_email.text = it.email
            }

            if(it.location.isNullOrBlank()) {
                tv_details_location.text = "-"
            } else {
                tv_details_location.text = it.location
            }

            tv_details_followers.text = it.followers.toString()
            tv_details_following.text = it.following.toString()

            if(it.bio != null) {
                tv_details_bio.visibility = View.VISIBLE
                tv_details_bio.text = it.bio
            }

            viewModel.loadRepos(it.login)

        }
    }
}