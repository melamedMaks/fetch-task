package maksim.melamed.fetchtask.listfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import maksim.melamed.fetchtask.databinding.GroupItemBinding
import maksim.melamed.fetchtask.models.Data
import maksim.melamed.fetchtask.models.SortedData

/**
 * GroupAdapter that accepts list of DataSorted objects and renders it in recyclerView
while providing list of detailed item data to nested ItemAdapter
 */

class GroupAdapter() :
    RecyclerView.Adapter<GroupAdapter.VH>() {

    private var dataList = listOf<SortedData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(
            GroupItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: VH, position: Int) {
        renderListId(holder, position)
        renderItemList(holder, position)
    }

    /**
     * displays list id within its current position in the list by index
     */
    private fun renderListId(
        holder: VH,
        position: Int
    ) {
        holder.binding.itemListIdValue.text = dataList[position].listId.toString()
    }

    /**
     * passes the list of data to ItemAdapter
    within its current position in the list (list of SortedData) that grouped by list id
     */
    private fun renderItemList(
        holder: VH,
        position: Int
    ) {
        holder.binding.recyclerViewItem.adapter = ItemAdapter(list = dataList[position].itemList)
        holder.binding.recyclerViewItem.layoutManager = LinearLayoutManager(
            holder.binding.itemListId.context, LinearLayoutManager.HORIZONTAL, false
        )
    }

    override fun getItemCount(): Int = dataList.size

    inner class VH(val binding: GroupItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun updateGroupAdapter(dataList: List<SortedData>){
        this.dataList = dataList
        this.notifyDataSetChanged()
    }
}