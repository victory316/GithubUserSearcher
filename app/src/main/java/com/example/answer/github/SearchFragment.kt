package com.example.answer.github

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.answer.R
import com.google.android.material.textfield.TextInputEditText


class SearchFragment : Fragment() {
    private var githubViewModel: GithubViewModel? = null
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
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(@NonNull view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val name: TextView = view.findViewById(R.id.user_name)
        // Add Text Watcher on name input text
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