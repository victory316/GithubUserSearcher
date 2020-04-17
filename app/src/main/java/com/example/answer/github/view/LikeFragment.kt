package com.example.answer.github.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.answer.R
import com.example.answer.databinding.FragmentLikeBinding
import com.example.answer.github.viewmodel.GithubViewModel
import com.example.answer.github.ui.GithubListAdapter
import com.example.answer.github.util.InjectorUtils

class LikeFragment : Fragment() {
    private lateinit var binding: FragmentLikeBinding
    private lateinit var view: GithubActivity
    private lateinit var adapter: GithubListAdapter

    private val githubViewModel: GithubViewModel by viewModels {
        InjectorUtils.proviedGithubViewModel(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val roomDetailLayoutManager = LinearLayoutManager(view)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_like, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
        }
        binding.searchRecyclerView.adapter = GithubListAdapter().apply {
            setViewModel(githubViewModel)
        }

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