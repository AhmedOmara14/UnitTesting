package maze.run.unittesting.ui.fragment

import android.icu.lang.UCharacter.IndicPositionalCategory.LEFT
import android.icu.lang.UCharacter.IndicPositionalCategory.RIGHT
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_shopping_item.*
import kotlinx.android.synthetic.main.fragment_image_pick.*
import kotlinx.android.synthetic.main.fragment_shopping.*
import kotlinx.android.synthetic.main.fragment_shopping.view.*
import maze.run.unittesting.R
import maze.run.unittesting.other.Status
import maze.run.unittesting.ui.ViewModels.viewmodel
import maze.run.unittesting.ui.adapter.adapterforImage
import maze.run.unittesting.ui.adapter.adapterforShoppingItem
import javax.inject.Inject

@AndroidEntryPoint

class ShoppingFragment
@Inject
constructor(
    val adapterforShoppingItem: adapterforShoppingItem,
    var ShoppingViewModel: viewmodel? = null
   
) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_shopping, container, false)
        ShoppingViewModel = ShoppingViewModel ?: ViewModelProvider(requireActivity()).get(viewmodel::class.java)
        subscribeToObservers()
        view.rvShoppingItems.adapter = adapterforShoppingItem
        view.rvShoppingItems.layoutManager = LinearLayoutManager(requireContext())
        ItemTouchHelper(itemTouchCallback).attachToRecyclerView(view.rvShoppingItems)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.fabAddShoppingItem.setOnClickListener {
            findNavController().navigate(R.id.action_shoppingFragment_to_addShoppingItemFragment)
        }
    }

    private val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(
        0, LEFT or RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ) = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val pos = viewHolder.layoutPosition
            val item = adapterforShoppingItem.shoppingItems[pos]
            ShoppingViewModel?.deleteShoppingItemfromDB(item)
            Snackbar.make(requireView(), "Successfully deleted item", Snackbar.LENGTH_LONG).apply {
                setAction("Undo") {
                    ShoppingViewModel?.insertShoppingItemintoDB(item)
                }
                show()
            }
        }
    }
    private fun subscribeToObservers() {
        ShoppingViewModel?.shoppingItem?.observe(viewLifecycleOwner, Observer {
            adapterforShoppingItem.shoppingItems = it
        })
        ShoppingViewModel?.totalprice?.observe(viewLifecycleOwner, Observer {
            val price = it ?: 0f
            val priceText = "Total Price: $price€"
            tvShoppingItemPrice.text = priceText
        })
    }

   
}