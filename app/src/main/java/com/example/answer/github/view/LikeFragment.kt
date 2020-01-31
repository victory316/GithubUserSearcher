package com.example.answer.github.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.answer.R
import com.example.answer.databinding.FragmentLikeBinding
import com.example.answer.github.viewmodel.GithubViewModel
import com.example.answer.github.view.adapter.GithubListAdapter

class LikeFragment : Fragment() {
    private var githubViewModel: GithubViewModel? = null
    private lateinit var binding: FragmentLikeBinding
    private lateinit var view: GithubActivity
    private lateinit var adapter: GithubListAdapter

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        githubViewModel =
            ViewModelProviders.of(requireActivity()).get(GithubViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val roomDetailLayoutManager = LinearLayoutManager(view)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_like, container, false)
        binding.searchRecyclerView.adapter = adapter
        binding.searchRecyclerView.layoutManager = roomDetailLayoutManager
        binding.searchRecyclerView.setHasFixedSize(true)

        return binding.root
    }


    fun setContext(view: GithubActivity){
        this.view = view
    }

    fun setAdapter(adapter : GithubListAdapter) {
        this.adapter = adapter
    }


    companion object {
        fun newInstance(): LikeFragment {
            return LikeFragment()
        }
    }
}