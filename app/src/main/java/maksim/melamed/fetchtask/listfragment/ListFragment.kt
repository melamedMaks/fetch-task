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
import maksim.melamed.fetchtask.models.Data
import maksim.melamed.fetchtask.models.SortedData

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var listViewModel: ListViewModel
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listViewModel = ViewModelProvider(this)[ListViewModel::class.java]
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initVars()
        initListeners()
        initObservers()
        listViewModel.getData()
    }

    private fun initObservers() {
        listViewModel.apply {
            data.observe(viewLifecycleOwner) { list ->
                disableProgressBars(isEnabled = false)
                val sortedData = filterAndSortListOfItems(list)
                renderUi(sortedData)
            }
            errorHandler.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                disableProgressBars(isEnabled = false)
            }
        }
    }

    private fun renderUi(sortedData: List<SortedData>) {
        binding.recyclerViewGroup.adapter = GroupAdapter(dataList = sortedData)
        binding.recyclerViewGroup.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.VERTICAL, false
        )
    }

    private fun disableProgressBars(isEnabled: Boolean) {
        binding.progressindicator.isVisible = isEnabled
        swipeRefreshLayout.isRefreshing = isEnabled
    }

    private fun initListeners() {
        swipeRefreshLayout.setOnRefreshListener {
            listViewModel.getData()
        }
    }

    private fun initVars() {
        swipeRefreshLayout = binding.swipe
        swipeRefreshLayout.isEnabled = true
    }

    private fun filterAndSortListOfItems(list: List<Data>): List<SortedData> {
        var data = list.filter { !it.name.isNullOrEmpty() }
        data = data.sortedWith(compareBy<Data> { it.listId }.then(compareBy { it.name }))
        val mapOfData = data.groupBy { it.listId }
        val listOfData = mapOfData.toList()
        val sortedData = mutableListOf<SortedData>()
        for ((i, pair) in listOfData.withIndex()) {
            sortedData.add(i, SortedData(pair.first, pair.second))
        }
        return sortedData
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}