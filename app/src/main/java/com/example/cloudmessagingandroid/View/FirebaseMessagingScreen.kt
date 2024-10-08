package com.example.cloudmessagingandroid.View

import android.Manifest
import android.os.Build
import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.cloudmessagingandroid.View.Component.FirebaseMessagingNotificationPermissionDialog
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun FirebaseMessagingScreen() {

    val showNotificationDialog = remember { mutableStateOf(false) }

    // Android 13 Api 33 - runtime notification permission has been added
    val notificationPermissionState = rememberPermissionState(
        permission = Manifest.permission.POST_NOTIFICATIONS
    )
    if (showNotificationDialog.value) FirebaseMessagingNotificationPermissionDialog(
        showNotificationDialog = showNotificationDialog,
        notificationPermissionState = notificationPermissionState
    )

    LaunchedEffect(key1=Unit){
        if (notificationPermissionState.status.isGranted ||
            Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU
        ) {
            Firebase.messaging.subscribeToTopic("Tutorial")
        } else showNotificationDialog.value = true
    }





    // Main Screen
    
    Text(text = "Howdy")

    var url = "http://3.136.251.55/dashboard"
    url = "https://www.mctrax.net/loginmain.php"

    // Adding a WebView inside AndroidView with layout as full screen
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }, update = {
        it.settings.javaScriptEnabled = true
        it.settings.domStorageEnabled = true
        it.loadUrl(url)
    })













//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.Black),
//        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text("Hello, main screen", color = Color.White)
//    }








}