package uk.nightlines.feature.weather.root.ui

//import android.util.Log
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.rememberCoroutineScope
//import com.github.terrakok.modo.Screen
//import com.github.terrakok.modo.ScreenKey
//import com.github.terrakok.modo.generateScreenKey
//import kotlinx.coroutines.launch
//import kotlinx.parcelize.Parcelize
//import uk.nightlines.modomultimodule.di.LocalNavigationProvider
//import uk.nightlines.modomultimodule.navigation.OpenWeatherScreenCommand
//import uk.nightlines.modomultimodule.navigation.NavigationOpenStack2

//@Parcelize
//class SampleScreen3(
//    override val screenKey: ScreenKey = generateScreenKey()
//) : Screen {
//
//    @Composable
//    override fun Content() {
//        SampleContent3()
//    }
//}
//
//@Parcelize
//class SampleScreen4(
//    override val screenKey: ScreenKey = generateScreenKey()
//) : Screen {
//
//    @Composable
//    override fun Content() {
//        SampleContent4()
//    }
//}
//
//@Composable
//fun SampleContent3() {
//    Log.d("GTA5", "recomposition Sample Content 3")
//    val navigation = LocalNavigationProvider.current
//
//    val coroutineScope = rememberCoroutineScope()
//
//    Text(text = "Hello, World!")
//
//    Button(onClick = {
//        coroutineScope.launch {
//            navigation.getNavigation().commandsFlow.emit(OpenWeatherScreenCommand)
//        }
//    }) {
//        Text("open screen 3")
//    }
//}
//
//@Composable
//fun SampleContent4() {
//    val navigation = LocalNavigationProvider.current
//
//    val coroutineScope = rememberCoroutineScope()
//
//    Text(text = "Hello, Screen 4")
//
//    Button(onClick = {
//        coroutineScope.launch {
//            navigation.getNavigation().commandsFlow.emit(NavigationOpenStack2)
//        }
//    }) {
//        Text("open screen 4")
//    }
//}