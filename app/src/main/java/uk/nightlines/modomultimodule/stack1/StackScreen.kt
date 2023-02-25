//package uk.nightlines.modomultimodule.stack1
//
//import android.util.Log
//import androidx.compose.foundation.layout.Column
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.rememberCoroutineScope
//import com.github.terrakok.modo.*
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.parcelize.Parcelize
//import uk.nightlines.modomultimodule.di.LocalNavigationProvider
//
//@Parcelize
//class SampleScreen(
//    override val screenKey: ScreenKey = generateScreenKey()
//) : Screen {
//
//    @Composable
//    override fun Content() {
//        SampleContent()
//    }
//}
//
//@Parcelize
//class SampleScreen2(
//    override val screenKey: ScreenKey = generateScreenKey()
//) : Screen {
//
//    @Composable
//    override fun Content() {
//        SampleContent2()
//    }
//}
//
//@Composable
//fun SampleContent() {
////    val parent = LocalContainerScreen.current
//    val navigation = LocalNavigationProvider.current
//
//    val coroutineScope = rememberCoroutineScope()
//
//    Column {
//        Text(text = "Weather Screen")
//
//        Button(onClick = {
//            coroutineScope.launch(Dispatchers.Main) {
//                navigation.getNavigation().navigate(uk.nightlines.core.navigation.OpenWeatherScreenCommand)
//            }
//        }) {
//            Text("navigation command")
//        }
//        Button(onClick = {
////            (parent as StackScreen).replace(uk.nightlines.feature.weather.ui.SampleScreen3())
//        }) {
//            Text("modo way")
//        }
//    }
//
//}
//
//@Composable
//fun SampleContent2() {
//    Log.d("GTA5", "Sample Content 2")
//
//    val navigation = LocalNavigationProvider.current
//
//    val coroutineScope = rememberCoroutineScope()
//
//    Text(text = "Hello, Screen 2")
//
//    Button(onClick = {
//        coroutineScope.launch {
//            navigation.getNavigation().commandsFlow.emit(uk.nightlines.core.navigation.NavigationOpenStack2)
//        }
//    }) {
//        Text("open stack")
//    }
//}