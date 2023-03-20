package com.example.lab2

import android.app.ActionBar.LayoutParams
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginTop
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.lab2.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Viewmodels
        val commentViewModel: MainActivity.CommentViewModel by activityViewModels();

        // Create binding and view
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false);
        val view = binding.root;

        // Add comments from viewmodel
        val commentLayout = binding.commentLayout;
        commentViewModel.data.observe(viewLifecycleOwner) { value ->
            for(comment in value) {
                addComment(commentLayout, comment.user, comment.content)
            }
        }

        // Navigate to comment fragment
        binding.navCommentButton.setOnClickListener() {
           Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_commentFragment)
        }

        binding.newsletterButton.setOnClickListener() {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_newsletterFragment)
        }

        // Return view
        return view;
    }

    // Add a singular comment
    fun addComment(commentLayout: LinearLayout, user: String, comment: String) {
        // Create comment textview and margins
        val commentTextView = TextView(this.context)
        val commentParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        commentParams.setMargins(0, 60, 0, 0)

        commentTextView.text = comment
        commentTextView.textSize = 15f
        commentTextView.setLayoutParams(commentParams)

        // Create user textview
        val userTextView = TextView(this.context)
        userTextView.text = "av: $user"
        userTextView.textSize = 12f

        // Add textviews
        commentLayout.addView(commentTextView)
        commentLayout.addView(userTextView)
    }
}