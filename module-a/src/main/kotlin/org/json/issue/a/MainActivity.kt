package org.json.issue.a

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import org.json.JSONObject
import org.json.issue.a.BuildConfig.BUILD_TYPE

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val input = "{\"username\": null}"
        val json = JSONObject(input)
        val username = try {
            json.getString("username")
        } catch (e: Exception) {
            """${e.message}
                |${e.stackTrace.first()}
            """.trimMargin()
        }

        val classLoader = JSONObject::class.java.classLoader!!.toString()
        val source = if (classLoader.contains("BootClassLoader")) {
            "Android Platform"
        } else {
            "External Library"
        }
        val variant = BUILD_TYPE

        setContent {
            MaterialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = if (source == "Android Platform") MaterialTheme.colors.background else MaterialTheme.colors.error
                ) {
                    Column {
                        AppName("org.json Issue 283422335")
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Input(input)
                            UserName(username)
                            Source(source)
                            ClassLoader(classLoader)
                            Variant(variant)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AppName(name: String, modifier: Modifier = Modifier) {
    Text(text = name, fontSize = 8.em, modifier = modifier)
}

@Composable
fun Input(input: String, modifier: Modifier = Modifier) {
    Row {
        Label("Input: ", modifier = modifier)
        Text(text = input, modifier = modifier)
    }
}

@Composable
fun UserName(username: String, modifier: Modifier = Modifier) {
    Row {
        Label("input.username: ", modifier = modifier)
        Text(username, modifier = modifier)
    }
}

@Composable
fun Source(source: String, modifier: Modifier = Modifier) {
    Row {
        Label("org.json source: ", modifier = modifier)
        Text(source, modifier = modifier)
    }
}

@Composable
fun ClassLoader(classLoader: String, modifier: Modifier = Modifier) {
    Row {
        Label("Classloader: ", modifier = modifier)
        Text(classLoader, modifier = modifier)
    }
}

@Composable
fun Variant(variant: String, modifier: Modifier = Modifier) {
    Row {
        Label("Variant: ", modifier = modifier)
        Text(variant, modifier = modifier)
    }
}

@Composable
fun Label(text: String, modifier: Modifier = Modifier) {
    Text(text, fontWeight = FontWeight.Bold, modifier = modifier)
}
