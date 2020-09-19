package maze.run.unittesting.ui.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import maze.run.unittesting.R

class adapterforShoppingItem {

    inner class viewholder(itemView: View) :RecyclerView.ViewHolder(itemView){
        var imageviewifutem:ImageView=itemView.findViewById(R.id.ivShoppingImage1)


    }
}