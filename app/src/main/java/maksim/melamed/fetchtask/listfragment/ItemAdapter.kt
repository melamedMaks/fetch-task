package maksim.melamed.fetchtask.listfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import maksim.melamed.fetchtask.databinding.ItemBinding
import maksim.melamed.fetchtask.models.Data

class ItemAdapter(val list: List<Data>): RecyclerView.Adapter<ItemAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH = VH(
        ItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.itemNameValue.text = list[position].name
        holder.binding.itemIdValue.text = list[position].id.toString()
    }

    override fun getItemCount(): Int = list.size

    inner class VH(val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}