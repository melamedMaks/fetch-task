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
import maksim.melamed.fetchtask.animateArrow
import maksim.melamed.fetchtask.databinding.FragmentPresenterBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
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

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        animateArrow(view = binding.imageViewArrowDownPresenter)
    }

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