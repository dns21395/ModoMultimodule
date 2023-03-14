package uk.nightlines.core.common

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel

// https://proandroiddev.com/dagger-2-and-jetpack-compose-integration-8a8d424ffdb4
@Suppress("UNCHECKED_CAST")
@Composable
inline fun <reified T : ViewModel> daggerViewModel(
    key: String? = null,
    crossinline viewModelInstanceCreator: () -> T
): T =
    viewModel(
        modelClass = T::class.java,
        key = key,
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return viewModelInstanceCreator() as T
            }
        }
    )

@Composable
inline fun <reified VM : ViewModel> viewModels(
    crossinline viewModelProducer: () -> VM
): Lazy<VM> {
    return viewModels(LocalViewModelStoreOwner.current!!, viewModelProducer)
}

inline fun <reified VM : ViewModel> viewModels(
    viewModelStoreOwner: ViewModelStoreOwner,
    crossinline viewModelProducer: () -> VM
): Lazy<VM> {
    return lazy(LazyThreadSafetyMode.NONE) {
        val factory = object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <VM : ViewModel> create(modelClass: Class<VM>) = viewModelProducer() as VM
        }
        val viewModelProvider = ViewModelProvider(viewModelStoreOwner, factory)
        viewModelProvider[VM::class.java]
    }
}