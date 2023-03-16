package com.mexicandeveloper.jpmcexercise.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mexicandeveloper.jpmcexercise.databinding.FragmentMainBinding
import com.mexicandeveloper.jpmcexercise.ui.detail.DetailFragment
import com.mexicandeveloper.jpmcexercise.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(), RVMainAdapter.ListenerInteraction {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getSchools()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvMain.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.rvMain.adapter = RVMainAdapter(emptyList(), this)


        viewModel.res.observe(viewLifecycleOwner) {
            (binding.rvMain.adapter as RVMainAdapter).setList(it.data ?: emptyList())
            when (it.status) {
                Status.LOADING -> {
                    binding.pbMain.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.pbMain.visibility = View.GONE
                }
                Status.SUCCESS -> {
                    binding.pbMain.visibility = View.GONE
                }

            }
        }
    }

    override fun onItemClick(position: Int) {
        viewModel.position = position
        DetailFragment.newInstance(parentFragmentManager)
    }

}