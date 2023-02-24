package uk.nightlines.feature.weather.ui

//import androidx.compose.runtime.*
//import com.github.terrakok.modo.Screen
//import com.github.terrakok.modo.stack.StackNavModel
//import com.github.terrakok.modo.stack.StackScreen
//import kotlinx.parcelize.Parcelize
//import uk.nightlines.modomultimodule.di.DaggerAppComponent
//import uk.nightlines.modomultimodule.di.LocalNavigationProvider
//
//@Parcelize
//class SampleStack2(
//    private val stackNavModel: StackNavModel,
//) : StackScreen(stackNavModel) {
//
//    constructor(rootScreen: Screen) : this(StackNavModel(rootScreen))
//
//    @Composable
//    override fun Content() {
//
//        val navigationProvider = DaggerAppComponent.builder().build()

//        val screen = remember {
//            mutableStateOf<NavigationCommand>(NavigationForwardCommand)
//        }
//
//        if (screen.value is NavigationForwardCommand) {
//            this.replace(SampleScreen3())
//        }
//
//        if (screen.value is NavigationForwardRootCommand) {
//            this.replace(SampleScreen4())
//        }
//
//        Log.d("GTA5", "[SampleStack2] screen changed : ${screen.value}")

//        CompositionLocalProvider(
//            LocalNavigationProvider provides navigationProvider
//        ) {
//            TopScreenContent()
//        }


//
//        LaunchedEffect("key_2") {
//            navigationProvider.getNavigation().commandsFlow.collect { command ->
//                Log.d("GTA5", "[SampleStack2] command : $command")
//
//                screen.value = command
//            }
//        }
//    }
//}