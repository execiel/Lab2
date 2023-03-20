package com.example.lab2
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.clearFragmentResult
import androidx.navigation.Navigation
import com.example.lab2.databinding.FragmentLoginBinding
import com.example.lab2.databinding.FragmentNewsletterBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class NewsletterFragment : Fragment() {

    private lateinit var binding: FragmentNewsletterBinding;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val newsletterSubscribers = ArrayList<String>();

        // init arraylist
        for(i in 1 .. 5)
            newsletterSubscribers.add("enmail$i@mail.se");

        // Create binding and view
        binding = FragmentNewsletterBinding.inflate(layoutInflater, container, false);
        val view = binding.root;


        // Onclicklistener class for snackbar
        class SnackbarListener: View.OnClickListener {
            override fun onClick(v: View) {
                newsletterSubscribers.removeLast()
            }
        }

        // Handle send event
        binding.sendButton.setOnClickListener() {
            val email = binding.emailEditText.text.toString()
            newsletterSubscribers.add(email)

            println("It got clicked")

            // Create snackbar for undo action
            val confirmSnackbar = Snackbar.make(view, "Är du säker att du vill skriva upp dig?", Snackbar.LENGTH_LONG)
            confirmSnackbar.setAction(R.string.undo_string, SnackbarListener());
            confirmSnackbar.show()
        }

        // Handle back event
        binding.backButton.setOnClickListener() {
            Navigation.findNavController(view).navigate(R.id.action_newsletterFragment_to_homeFragment)
        }

        return view;
    }

}