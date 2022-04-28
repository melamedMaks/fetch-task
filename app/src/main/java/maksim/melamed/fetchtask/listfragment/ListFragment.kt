package maksim.melamed.fetchtask.listfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import maksim.melamed.fetchtask.databinding.FragmentListBinding
import maksim.melamed.fetchtask.models.SortedData
import maksim.melamed.fetchtask.utils.filterAndSortListOfItems

/**
the second screen that displays list of items received from api
allows to refresh the data by swiping dawn
 */
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var listViewModel: ListViewModel
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var groupAdapter: GroupAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //initiates viewModel classic mvvm pattern without vm factory
        listViewModel = ViewModelProvider(this)[ListViewModel::class.java]
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //creates adapter
        groupAdapter = GroupAdapter()
        //initiates variables
        initVars()
        //initiates listeners (only onRefreshListener listener)
        initListeners()
        //initiates observers of liveData from viewModel
        initObservers()
        //calls method in viewModel to get data that starts a chained events
        //that the result of these returned to one of the observers
        //(fragment -> viewModel -> repository -> service -> repository -> viewModel -> observer)
        listViewModel.getData()
    }

    private fun initObservers() {
        listViewModel.apply {
            //observes result of received data (list of Data objects)
            data.observe(viewLifecycleOwner) { list ->
                //disables two progress bars (one that enabled only while first load, second on swipe down)
                disableProgressBars()
                //creating a new variable with sorted list of items according to the task requirements
                val sortedData = filterAndSortListOfItems(list) //method to sort the received list
                //renders ui within sorted list while using recycler view

                groupAdapter.updateGroupAdapter(sortedData)
            }
            //observes coroutine exception handler
            //receives string of localised message
            //displays message with toast
            errorHandler.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                disableProgressBars()
            }
        }
    }

    /**
    initiates recycler view and adapter
    that receives sorted list of items and displays to user
     */
    private fun initAdapter() {
        binding.recyclerViewGroup.adapter = groupAdapter
        binding.recyclerViewGroup.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.VERTICAL, false
        )
    }

    /**
     * function to disable / enable swipe progress bars by passing true / false
     */
    private fun disableProgressBars() {
        binding.progressindicator.isVisible = false //indicator from material design
        swipeRefreshLayout.isRefreshing = false
    }

    /**
     initiates swipeRefreshLayout listener
     that calls function from viewModel to get list of items
     */

    private fun initListeners() {
        swipeRefreshLayout.setOnRefreshListener {
            listViewModel.getData()
        }
    }

    /**
     * initiates variables (useful if more than one)
     */
    private fun initVars() {
        initAdapter()
        swipeRefreshLayout = binding.swipe
        swipeRefreshLayout.isEnabled = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}