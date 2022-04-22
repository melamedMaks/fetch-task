package maksim.melamed.fetchtask.presenterfragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import maksim.melamed.fetchtask.R
import maksim.melamed.fetchtask.utils.animateArrow
import maksim.melamed.fetchtask.databinding.FragmentPresenterBinding

/*
the first screen that presented to a user after default splash screen
describes the meaning of the app and calls to action (load data) within
animated view (arrow dawn) that points to the button (load) below
 */
class PresenterFragment : Fragment() {

    private var _binding: FragmentPresenterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPresenterBinding.inflate(inflater, container, false)
        return binding.root
    }

    //ObjectAnimator requires minimum build version 24
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //initiates listeners, in this case on load button click listener
        initListeners()
        //starts animation of arrow dawn view
        animateArrow(view = binding.imageViewArrowDownPresenter)
    }

    //navigates to ListFragment that loads data from api and displays it to user
    private fun initListeners() {
        binding.buttonLoadPresenter.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}