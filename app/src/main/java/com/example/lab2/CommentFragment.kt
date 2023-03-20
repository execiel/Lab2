package com.example.lab2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.lab2.databinding.FragmentCommentBinding

class CommentFragment : Fragment() {

    private lateinit var binding: FragmentCommentBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Grab viewmodels
        val commentViewModel: MainActivity.CommentViewModel by activityViewModels();
        val userNameViewModel: MainActivity.UsernameViewModel by activityViewModels();

        // Create binding and view
        binding = FragmentCommentBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        // Grab elements
        val commentButton = binding.commentButton;
        val commentEditText = binding.commentEditText;

        // Grab username
        var username = "John doe";

        userNameViewModel.data.observe(viewLifecycleOwner) { value ->
            username = value;
        }

        // Handle on click event
        commentButton.setOnClickListener()  {
            navigateHome(commentEditText.text.toString(), username, view, commentViewModel);
        }

        // Return view
        return view

    }

    private fun navigateHome(comment: String, username: String, view: View, viewModel: MainActivity.CommentViewModel) {
        if(comment == "") return;

        // Push comment in to viewmodel
        var tempList = ArrayList<Comment>();

        viewModel.data.observe(viewLifecycleOwner) { value ->
            if(value != null) tempList = value
        }

        tempList.add(Comment(username, comment))

        viewModel.data.value = tempList;

        // Navigate back
        Navigation.findNavController(view).navigate(R.id.action_commentFragment_to_homeFragment);
    }
}