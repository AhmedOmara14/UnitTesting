package maze.run.unittesting.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import maze.run.unittesting.R
import javax.inject.Inject

class adapterforImage
@Inject constructor(
    val glide: RequestManager
) : RecyclerView.Adapter<adapterforImage.viewholder>() {

    private val diffcallback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, diffcallback)

    private var onItemClickListener: ((String) -> Unit)? = null

    fun setonItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }

    var images: List<String>
        get() = differ.currentList
        set(value) = differ.submitList(value)


    inner class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageviewifutem: ImageView = itemView.findViewById(R.id.ivShoppingImage11)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        )
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        glide.load(images[position]).into(holder.imageviewifutem)
        holder.itemView.setOnClickListener {
            onItemClickListener.let { click ->
                if (click != null) {
                    click(images[position])
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }
}