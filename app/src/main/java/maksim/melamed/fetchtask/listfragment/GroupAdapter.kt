package maksim.melamed.fetchtask.listfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import maksim.melamed.fetchtask.databinding.GroupItemBinding
import maksim.melamed.fetchtask.models.SortedData

class GroupAdapter(var dataList: List<SortedData>) :
    RecyclerView.Adapter<GroupAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH = VH(
        GroupItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.itemListIdValue.text = dataList[position].listId.toString()
        holder.binding.recyclerViewItem.adapter = ItemAdapter(list = dataList[position].itemList)
        holder.binding.recyclerViewItem.layoutManager = LinearLayoutManager(
            holder.binding.itemListId.context, LinearLayoutManager.HORIZONTAL, false
        )
    }

    override fun getItemCount(): Int = dataList.size

    inner class VH(val binding: GroupItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}