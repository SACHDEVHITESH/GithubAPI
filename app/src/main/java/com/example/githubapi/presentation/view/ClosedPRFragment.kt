package com.example.githubapi.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubapi.R
import com.example.githubapi.data.remote.api.models.ClosedPRModel
import com.example.githubapi.data.remote.api.models.GithubUserModel
import com.example.githubapi.databinding.ClosedPrFragmentBinding
import com.example.githubapi.presentation.viewmodel.ClosedPrViewModel

class ClosedPRFragment : Fragment() {

    private val closedPRViewModel by activityViewModels<ClosedPrViewModel>()
    private lateinit var closedPRViewer: RecyclerView
    private lateinit var binding: ClosedPrFragmentBinding
    private lateinit var githubUser: GithubUserModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.closed_pr_fragment, container, false)
        binding.closedPRViewModel = closedPRViewModel
        closedPRViewer = binding.root.findViewById(R.id.closedPRRecyclerView)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        closedPRViewModel.getClosedPRInfo().observe(this) {
            showClosedPR(it)
        }

        closedPRViewModel.getUserInfoByUsername().observe(this) {
            if (it != null) {
                setProfileData(it)
            }
        }

        binding.viewProfile.setOnClickListener {
            findNavController().navigate(ClosedPRFragmentDirections.actionClosedPRFragmentToSingleUserFragment())
        }
    }

    private fun setProfileData(profileData: GithubUserModel) {
        context?.let {
            Glide.with(it)
                .load(profileData.avatarUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.imgAvatar)
        }
    }

    private fun showClosedPR(closedPRList: List<ClosedPRModel>?) {
        val closedPRListAdapter = ClosedPRListAdapter()
        closedPRViewer.layoutManager = LinearLayoutManager(context)
        closedPRViewer.adapter = closedPRListAdapter

        if (closedPRList != null) {
            closedPRListAdapter.setData(closedPRList)
            binding.progressBar.visibility = View.GONE
        }
    }

}
