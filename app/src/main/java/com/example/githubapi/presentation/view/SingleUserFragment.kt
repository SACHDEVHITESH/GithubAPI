package com.example.githubapi.presentation.view

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubapi.R
import com.example.githubapi.databinding.SingleUserFragmentBinding
import com.example.githubapi.presentation.viewmodel.ClosedPrViewModel

class SingleUserFragment : Fragment() {

    private val closedPRViewModel by activityViewModels<ClosedPrViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: SingleUserFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.single_user_fragment, container, false)
        closedPRViewModel.getUserInfoByUsername().observe(this) { githubUserModel ->
            context?.let {
                Glide.with(it)
                    .load(githubUserModel?.avatarUrl)
                    .apply(RequestOptions.circleCropTransform())
                    .into(binding.imgAvatar)
            }
            binding.name.text = githubUserModel?.login
            if (TextUtils.isEmpty(githubUserModel?.login)) {
                binding.name.visibility = View.GONE
                binding.name1.visibility = View.GONE
            }

            binding.email.text = githubUserModel?.email
            if (TextUtils.isEmpty(githubUserModel?.email)) {
                binding.email.visibility = View.GONE
                binding.email1.visibility = View.GONE
            }

            binding.bio.text = githubUserModel?.bio
            if (TextUtils.isEmpty(githubUserModel?.bio)) {
                binding.bio.visibility = View.GONE
                binding.bio1.visibility = View.GONE
            }

            binding.blog.text = githubUserModel?.blog
            if (TextUtils.isEmpty(githubUserModel?.blog)) {
                binding.blog.visibility = View.GONE
                binding.blog1.visibility = View.GONE
            }

            binding.company.text = githubUserModel?.company
            if (TextUtils.isEmpty(githubUserModel?.company)) {
                binding.company.visibility = View.GONE
                binding.company1.visibility = View.GONE
            }

            binding.followers.text = githubUserModel?.followers.toString()
            if (TextUtils.isEmpty(githubUserModel?.followers.toString())) {
                binding.followers.visibility = View.GONE
                binding.followers1.visibility = View.GONE
            }

            binding.following.text = githubUserModel?.following.toString()
            if (TextUtils.isEmpty(githubUserModel?.following.toString())) {
                binding.following.visibility = View.GONE
                binding.following1.visibility = View.GONE
            }

            binding.publicRepos.text = githubUserModel?.publicRepos.toString()
            if (TextUtils.isEmpty(githubUserModel?.publicRepos.toString())) {
                binding.publicRepos.visibility = View.GONE
                binding.publicRepos1.visibility = View.GONE
            }

            binding.publicGists.text = githubUserModel?.publicGists.toString()
            if (TextUtils.isEmpty(githubUserModel?.publicGists.toString())) {
                binding.publicGists.visibility = View.GONE
                binding.publicGists1.visibility = View.GONE
            }

            binding.createdAt.text = githubUserModel?.getCreatedDateAsString()
            if (TextUtils.isEmpty(githubUserModel?.getCreatedDateAsString())) {
                binding.createdAt.visibility = View.GONE
                binding.createdAt1.visibility = View.GONE
            }

            binding.updatedAt.text = githubUserModel?.getUpdatedDateAsString()
            if (TextUtils.isEmpty(githubUserModel?.getUpdatedDateAsString())) {
                binding.updatedAt.visibility = View.GONE
                binding.updatedAt1.visibility = View.GONE
            }
    }

        return binding.root
    }
}
