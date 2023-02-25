package uk.nightlines.modomultimodule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.github.terrakok.modo.Modo
import com.github.terrakok.modo.stack.StackScreen
import uk.nightlines.feature.weather.main_impl.ui.WeatherStack

class MainActivity : ComponentActivity() {

    private var rootScreen: StackScreen? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rootScreen = Modo.init(savedInstanceState, rootScreen) {
            WeatherStack()
        }

        setContent {
            Surface(color = MaterialTheme.colorScheme.background) {
                rootScreen?.Content()
            }
        }
    }
}