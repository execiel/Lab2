package com.example.lab2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.lab2.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Grab viewmodel
        val usernameViewModel: MainActivity.UsernameViewModel by activityViewModels();

        // Create binding and view
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false);
        val view = binding.root;

        // Grab elements
        val usernameEditText = binding.usernameEditText;
        val loginButton = binding.loginButton;

        // Call navigation function
        loginButton.setOnClickListener() {
            navigateToHome(usernameEditText.text.toString(), usernameViewModel, view);
        }

        // Return view
        return view;
    }

    private fun navigateToHome(username: String, viewModel: MainActivity.UsernameViewModel, view: View) {
        if(username == "") return;
        viewModel.data.value = username;

        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment);
    }

}