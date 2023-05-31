package com.bharath.simplemusicplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.bharath.simplemusicplayer.presentation.ui.SongListScreen
import com.bharath.simplemusicplayer.ui.theme.SimpleMusicPlayerTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            SimpleMusicPlayerTheme {
                // A surface container using the 'background' color from the theme
               Permission()






            }
        }
    }
}
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Permission(){
    val permission=if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.TIRAMISU){
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    }else{
        android.Manifest.permission.READ_MEDIA_AUDIO
    }
    val permissionState= rememberPermissionState(permission = permission)
    val lifecycleOwner= LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner ){
        val observer=LifecycleEventObserver{_,event->
            if (event==Lifecycle.Event.ON_RESUME){
                permissionState.launchPermissionRequest()
            }


        }
        lifecycleOwner.lifecycle.addObserver(observer = observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }

    }
    if (permissionState.status.isGranted){
        SongListScreen()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimpleMusicPlayerTheme {
        Greeting("Android")
    }
}