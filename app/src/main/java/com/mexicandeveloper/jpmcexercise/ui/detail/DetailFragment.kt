package com.mexicandeveloper.jpmcexercise.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import com.mexicandeveloper.jpmcexercise.R
import com.mexicandeveloper.jpmcexercise.databinding.FragmentDetailBinding
import com.mexicandeveloper.jpmcexercise.ui.main.MainViewModel
import com.mexicandeveloper.jpmcexercise.utils.Status


class DetailFragment : Fragment() {

    companion object {

        fun newInstance(fm: FragmentManager) {
            val theFragment = DetailFragment()
            fm.commit {
                replace(R.id.container, theFragment)
                addToBackStack(null)
                setReorderingAllowed(true)
            }

        }
    }

    private val viewModel: MainViewModel by activityViewModels()

    lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.school = viewModel.getOneSchool()

        viewModel.getSATScore()
        viewModel.score.observe(viewLifecycleOwner) {
            it.data?.let { scores ->
                if (scores.any()) binding.score = scores[0]
            }
            when (it.status) {
                Status.LOADING -> binding.pbDetail.visibility = View.VISIBLE
                Status.ERROR -> binding.pbDetail.visibility = View.GONE
                Status.SUCCESS -> binding.pbDetail.visibility = View.GONE
            }
        }

        binding.btnWebSite.setOnClickListener {
            viewModel.getOneSchool()?.website?.let { url ->
                var formatterdURl = url
                if (!url.startsWith("https://") && !url.startsWith("http://")) {
                    formatterdURl = "http://" + url;
                }
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(formatterdURl)
                startActivity(intent)
            }

        }
        binding.tvCall.setOnClickListener {
            viewModel.getOneSchool()?.phoneNumber?.let { phone ->

                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:" + phone)
                startActivity(intent)
            }

        }
        binding.tvLocation.setOnClickListener {
            viewModel.getOneSchool()?.latitude?.let { lat ->
                viewModel.getOneSchool()?.longitude?.let { long ->
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("geo:$lat,$long")
                    startActivity(intent)
                }
            }

        }

    }
}