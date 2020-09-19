package maze.run.unittesting.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add_shopping_item.*
import kotlinx.android.synthetic.main.fragment_add_shopping_item.view.*
import kotlinx.android.synthetic.main.fragment_add_shopping_item.view.ivShoppingImage1
import kotlinx.android.synthetic.main.item_shopping.view.*
import maze.run.unittesting.R
import maze.run.unittesting.other.Status
import maze.run.unittesting.ui.ViewModels.viewmodel
import maze.run.unittesting.ui.adapter.adapterforImage
import java.util.*
import javax.inject.Inject


class AddShoppingItemFragment
@Inject
constructor(
    val glide: RequestManager
) : Fragment() {
    lateinit var viewmodel1: viewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_shopping_item, container, false)
        viewmodel1 = ViewModelProvider(requireActivity()).get(viewmodel::class.java)

        subscribeToobserver()
        view.btnAddShoppingItem.setOnClickListener {
            viewmodel1.insertShoppingItemtoDB(
                view.etShoppingItemName.text.toString(),
                view.etShoppingItemAmount.text.toString(),
                view.etShoppingItemPrice.text.toString()
                )
        }
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewmodel1.setCurImageUrl("")
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.ivShoppingImage1.setOnClickListener {
            findNavController().navigate(R.id.action_addShoppingItemFragment_to_imagePickFragment)
        }


    }

    private fun subscribeToobserver() {
        viewmodel1.curImageUrl.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            glide.load(it).into(ivShoppingImage1)
        })

        viewmodel1.insertShoppingItemintoDB.observe(requireActivity(), androidx.lifecycle.Observer {
            it.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        Snackbar.make(
                            requireView().rootView,
                            "Adding Shopping item",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        findNavController().popBackStack()
                    }

                    Status.ERROR -> {
                        Snackbar.make(
                            requireView().rootView,
                            "Error in Adding Shopping item",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
    }


}