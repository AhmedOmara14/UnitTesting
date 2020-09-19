package maze.run.unittesting.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import maze.run.unittesting.ui.adapter.adapterforImage
import maze.run.unittesting.ui.fragment.ImagePickFragment
import javax.inject.Inject


class ShoppingFragmentFactory
@Inject
constructor(
    val adapterforImage: adapterforImage
)
:FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
           ImagePickFragment::class.java.name ->ImagePickFragment(adapterforImage)
            else -> super.instantiate(classLoader, className)
        }

    }
}