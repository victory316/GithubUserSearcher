package com.example.answer.github.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.answer.R
import com.example.answer.databinding.FragmentSearchBinding
import com.example.answer.github.viewmodel.GithubViewModel
import com.example.answer.github.view.adapter.GithubListAdapter
import com.example.answer.github.view.adapter.PagingAdapter


class SearchFragment : Fragment() {
    private var githubViewModel: GithubViewModel? = null
    private lateinit var binding: FragmentSearchBinding
    private lateinit var view: GithubActivity
//    private lateinit var adapter: GithubListAdapter
    private lateinit var pagingAdapter: PagingAdapter

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

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        binding.searchRecyclerView.adapter = pagingAdapter
        binding.searchRecyclerView.layoutManager = roomDetailLayoutManager
        binding.searchRecyclerView.setHasFixedSize(true)

        githubViewModel!!.doShimmerAnimation.addOnPropertyChangedCallback( object: Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if (githubViewModel!!.doShimmerAnimation.get()) {
                    binding.shimmerViewContainer.startShimmer()
                    binding.shimmerViewContainer.visibility = View.VISIBLE
                } else {
                    binding.shimmerViewContainer.stopShimmer()
                    binding.shimmerViewContainer.visibility = View.INVISIBLE
                }
            }
        })

        return binding.root
    }

    override fun onViewCreated(@NonNull view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this // 해제될 수 있음. viewlifecycleowner
        binding.viewModel = githubViewModel
    }

    fun clearText() {
        binding.searchEditText.text.clear()
    }

    fun setContext(view: GithubActivity){
        this.view = view
    }

//    fun setAdapter(adapter : GithubListAdapter) {
//        this.adapter = adapter
//    }

    fun setPagingAdapter(adapter : PagingAdapter) {
        this.pagingAdapter = adapter
    }

    fun getString() : String {
        return binding.searchEditText.text.toString()
    }

    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }
}