package com.mexicandeveloper.jpmcexercise.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mexicandeveloper.jpmcexercise.databinding.RowSchoolBinding
import com.mexicandeveloper.jpmcexercise.models.School

class RVMainAdapter(var theSchoolList: List<School>, var listener: ListenerInteraction) :
    RecyclerView.Adapter<RVMainAdapter.SchoolViewHolder>() {

    private lateinit var binding: RowSchoolBinding

    @SuppressLint("NotifyDataSetChanged")
    fun setList(newSchoolList: List<School>) {
        theSchoolList = newSchoolList
        notifyDataSetChanged()
    }

    inner class SchoolViewHolder(binding: RowSchoolBinding) : ViewHolder(binding.root) {

        fun bind(school: School, onSelect: (School) -> Unit) {
            binding.school = school

            binding.root.setOnClickListener {
                onSelect(school)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {

        binding = RowSchoolBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SchoolViewHolder(binding)
    }

    override fun getItemCount(): Int = theSchoolList.size


    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        holder.bind(theSchoolList[position]) {
            listener.onItemClick(position)
        }
    }

    interface ListenerInteraction {
        fun onItemClick(position: Int)
    }
}