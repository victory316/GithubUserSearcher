package com.example.answer.github.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.answer.R
import com.example.answer.databinding.FragmentSearchBinding
import com.example.answer.github.viewmodel.GithubViewModel
import com.example.answer.github.ui.PagingAdapter
import com.example.answer.github.util.InjectorUtils
import timber.log.Timber


class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var pagingAdapter: PagingAdapter

    private val githubViewModel: GithubViewModel by viewModels {
        InjectorUtils.proviedGithubViewModel(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val roomDetailLayoutManager = LinearLayoutManager(activity)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)


        pagingAdapter = PagingAdapter(requireContext()).apply {
            setViewModel(githubViewModel)
        }

        binding.apply {
            Log.d("test", "viewModel : ${githubViewModel.hashCode()}")

            viewModel = githubViewModel
            lifecycleOwner = viewLifecycleOwner
            searchRecyclerView.adapter = pagingAdapter
            searchRecyclerView.layoutManager = roomDetailLayoutManager
            searchRecyclerView.setHasFixedSize(true)
        }

        githubViewModel.doShimmerAnimation.addOnPropertyChangedCallback( object: Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if (githubViewModel.doShimmerAnimation.get()) {
                    binding.shimmerViewContainer.startShimmer()
                    binding.shimmerViewContainer.visibility = View.VISIBLE
                } else {
                    binding.shimmerViewContainer.stopShimmer()
                    binding.shimmerViewContainer.visibility = View.INVISIBLE
                }
            }
        })

        subscribeUi(pagingAdapter)

        return binding.root
    }

    private fun subscribeUi(adapter: PagingAdapter) {
        githubViewModel.getPersonsLiveData().observe(viewLifecycleOwner, Observer { name ->

            Timber.tag("paging").d("observing : $name")

            if (name != null) {
                adapter.submitList(name)
            }
        })
    }


    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }
}