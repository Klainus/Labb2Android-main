package com.example.labb_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.labb_2.ui.theme.Labb2Theme
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment

import androidx.compose.ui.text.style.TextAlign
import com.example.labb_2.ui.theme.Citron
import com.example.labb_2.ui.theme.EngViolet
import com.example.labb_2.ui.theme.Mint


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Labb2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}
@Preview
@Composable
fun MyApp() {


    val navController = rememberNavController()

    Surface (
        modifier = Modifier.fillMaxSize(),
        color = Mint
    ) {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {Home(navController)}
            composable("login") {Login(navController, null, null)}
            composable("about") {About(navController)}
            composable("end/{username}") {navBackStackEntry ->
                val username = navBackStackEntry.arguments?.getString("username") ?: ""
                End(navController, username)
            }

            composable (
                "loginPage/{username}/{password}",
                arguments = listOf (
                    navArgument("username") {type = NavType.StringType},
                    navArgument("password") {type = NavType.StringType}
                )
            ) {navBackStackEntry ->
                val username = navBackStackEntry.arguments?.getString("username") ?: ""
                val password = navBackStackEntry.arguments?.getString("password") ?: ""
                Login(navController, username, password)

            }
        }
    }
}

@Composable
fun Home(navController: NavController) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Box(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {

            Text(
                text = "Hi there!",
                fontSize = 30.sp,
                color = EngViolet,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(50.dp)

            )
        }

        Box(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()

        ) {

            Text(text = "Welcome, to the new hot topic of World of Warcraft.... " +
                    "THE newest Brutosaur mount, as an in-game microtransaction. " +
                    "Read further for it's price and features!",
                fontSize = 20.sp,
                color = EngViolet,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(8.dp)
                )
    }

        Button(onClick = {navController.navigate("about")},
            colors = ButtonDefaults.buttonColors(Citron),
            modifier = Modifier
                .padding(60.dp)) {
            Text(text = "Read more")
        }

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.brutosaur_mount),
            contentDescription = "brutosaur",
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(bottom = 10.dp)
        )
    }
}


@Composable
fun Login(navController: NavController, username: String?, password: String?) {
    var userNameValue by remember {mutableStateOf(username ?: "")}
    var passwordValue by remember {mutableStateOf(password ?: "")}


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Login",
            fontSize = 24.sp,
            modifier = Modifier
                .padding(16.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.wow_logo),
            contentDescription = "wowlogo",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(top = 30.dp)
        )

        TextField(
            value = userNameValue,
            onValueChange = { userNameValue = it },
            label = { Text("Username is Brutosaur") }
        )
        TextField(
            value = passwordValue,
            onValueChange = { passwordValue = it },
            label = { Text("Password = 78") }
        )


        Button(onClick = {
            if (userNameValue in LoginUsernames && passwordValue == "78") {
                navController.navigate("end/$userNameValue")
            }
        },
            modifier = Modifier.padding(32.dp),
            colors = ButtonDefaults.buttonColors(Citron)

        ) {
            Text(text="Login")
        }
    }
}


@Composable
fun About(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Mint) {

    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "About this Mount",
            fontSize = 24.sp,
            color = EngViolet,
            modifier = Modifier
                //.align(Alignment.CenterHorizontally)
                .padding(50.dp)
        )

        Text(text = "The main selling point for this mount is that it has an Auction house" +
                " AND a Mailbox at your disposal, wherever you can mount up!" +
                " This package comes with a hefty price tag of 78€",

            fontSize = 18.sp,
            color = EngViolet,
            textAlign = TextAlign.Center,
            modifier = Modifier
                //.align(Alignment.CenterHorizontally)
                .padding(36.dp)
        )


        Button(onClick = {navController.navigate("login")},
            colors = ButtonDefaults.buttonColors(Citron)) {
            Text(text = "To Login")

        }

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.brutosaur_read_more),
            contentDescription = "brutosaur showcase",
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
        )
    }
}

@Composable
fun End(navController: NavController, username: String) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "The $username !",
            fontSize = 30.sp,
            color = EngViolet,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(20.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.brutosaur_lineup),
            contentDescription = "brutosaur final picture",
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
        )

        Text(text = "This is the first time I have experienced a paid way to get a small edge" +
                " in World of Warcraft, it's exciting to see how many people see it being worth" +
                " for an in-game convenience item! After all, the game needs incomes to continue" +
                " thriving and develop!",
            fontSize = 14.sp,
            color = EngViolet,
            textAlign = TextAlign.Center,
            modifier = Modifier
                //.align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )

        Button(onClick = { navController.navigate("home") },
            colors = ButtonDefaults.buttonColors(Citron),
        ) {
            Text(text = "Back to start")
        }

        Spacer(modifier = Modifier.weight(1f))


    }
}

val LoginUsernames = arrayOf(
    "Brutosaur", "brutosaur"
)