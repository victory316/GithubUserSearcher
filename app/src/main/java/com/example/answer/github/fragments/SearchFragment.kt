package com.example.answer.github.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.answer.R
import com.example.answer.databinding.FragmentSearchBinding
import com.example.answer.github.GithubActivity
import com.example.answer.github.GithubViewModel
import com.example.answer.github.recyclerview.GithubSearchAdapter


class SearchFragment : Fragment() {
    private var githubViewModel: GithubViewModel? = null
    private lateinit var binding: FragmentSearchBinding
    private lateinit var view: GithubActivity
    private lateinit var adapter: GithubSearchAdapter

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // init ViewModel
        githubViewModel =
            ViewModelProviders.of(requireActivity()).get(GithubViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View { // Inflate the layout for this fragment

        val roomDetailLayoutManager = LinearLayoutManager(view)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        binding.searchRecyclerView.adapter = adapter
        binding.searchRecyclerView.layoutManager = roomDetailLayoutManager
        binding.searchRecyclerView.setHasFixedSize(true)

        return binding.root
    }

    override fun onViewCreated(@NonNull view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = githubViewModel
    }

    fun clearText() {
        binding.searchEditText.text.clear()
    }

    fun setContext(view: GithubActivity){
        this.view = view
    }

    fun setAdapter(adapter : GithubSearchAdapter) {
        this.adapter = adapter
    }

    fun getString() : String {
        return binding.searchEditText.text.toString()
    }

    companion object {
        /**
         * Create a new instance of this fragment
         * @return A new instance of fragment FirstFragment.
         */
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }
}