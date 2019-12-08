package com.example.answer.github

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.answer.R
import com.google.android.material.textfield.TextInputEditText

class LikeFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_like, container, false)
    }

    override fun onViewCreated(@NonNull view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        /**
         * Create a new instance of this fragment
         * @return A new instance of fragment FirstFragment.
         */
        fun newInstance(): LikeFragment {
            return LikeFragment()
        }
    }
}