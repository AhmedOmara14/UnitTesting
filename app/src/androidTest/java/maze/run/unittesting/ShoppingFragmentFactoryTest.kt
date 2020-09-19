package maze.run.unittesting

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import maze.run.unittesting.repository.FakeShoppingRepositoryAndroidTest
import maze.run.unittesting.ui.ViewModels.viewmodel
import maze.run.unittesting.ui.adapter.adapterforImage
import maze.run.unittesting.ui.adapter.adapterforShoppingItem
import maze.run.unittesting.ui.fragment.AddShoppingItemFragment
import maze.run.unittesting.ui.fragment.ImagePickFragment
import maze.run.unittesting.ui.fragment.ShoppingFragment
import javax.inject.Inject


class ShoppingFragmentFactoryTest
@Inject
constructor(
    val adapterforImage: adapterforImage,
    val adapterforShoppingItem: adapterforShoppingItem,
    val glide: RequestManager
)
:FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            ImagePickFragment::class.java.name ->ImagePickFragment(adapterforImage)
            AddShoppingItemFragment::class.java.name ->AddShoppingItemFragment(glide)
            ShoppingFragment::class.java.name ->ShoppingFragment(adapterforShoppingItem,
                viewmodel(FakeShoppingRepositoryAndroidTest()))

            else -> super.instantiate(classLoader, className)
        }

    }
}