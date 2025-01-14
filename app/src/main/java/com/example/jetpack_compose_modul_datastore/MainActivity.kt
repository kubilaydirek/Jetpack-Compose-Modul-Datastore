package com.example.jetpack_compose_modul_datastore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetpack_compose_modul_datastore.ui.theme.JetpackComposeModulDatastoreTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeModulDatastoreTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier,viewModel : MainViewModel = hiltViewModel()) {
    val name = remember { mutableStateOf("") }
    val data by viewModel.name.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(data, style = TextStyle(fontSize = 18.sp, color = Color.Red, fontWeight = FontWeight.Bold), modifier = modifier.padding(15.dp))
        TextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text("İsminizi Giriniz") },
            modifier = Modifier
                .fillMaxWidth()
        )
        Button(onClick = {
            viewModel.saveNameDatastore(name.value)
        }) { Text(stringResource(R.string.save_data)) }
        Button(onClick = {
            viewModel.getData()
        }) { Text(stringResource(R.string.get_data)) }

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComposeModulDatastoreTheme {
        Greeting()
    }
}