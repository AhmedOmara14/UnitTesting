package maze.run.unittesting.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_image_pick.*
import maze.run.unittesting.R
import maze.run.unittesting.ui.ViewModels.viewmodel
import maze.run.unittesting.ui.adapter.adapterforImage
import javax.inject.Inject

class ImagePickFragment
@Inject
constructor(
    val adapterforImage1: adapterforImage
) : Fragment() {
    lateinit var viewmodel1: viewmodel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_image_pick, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel1 = ViewModelProvider(requireActivity()).get(viewmodel::class.java)
        setupRecyclerView()
        adapterforImage1.setonItemClickListener {
            findNavController().popBackStack()
            viewmodel1.setCurImageUrl(it)
        }
    }

    fun setupRecyclerView() {
        rvImages.apply {
            adapter = adapterforImage1
            layoutManager = GridLayoutManager(requireContext(), 4)
        }
    }

}