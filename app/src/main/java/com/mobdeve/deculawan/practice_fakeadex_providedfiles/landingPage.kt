package com.mobdeve.deculawan.practice_fakeadex_providedfiles


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.RecyclerView

class landingPage : ComponentActivity(){
    //load data
    private val restaurants: ArrayList<RestaurantModel> = DataGenerator.loadData()

    //Our RecyclerView
    private lateinit var recyclerView: RecyclerView
    private lateinit var login_button: Button
    private lateinit var register_button: Button
    private lateinit var uname: EditText
    private lateinit var pword: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_login)
        login_button = findViewById(R.id.login_button)
        register_button = findViewById(R.id.reg_button)

        ///For Login
        uname = findViewById(R.id.login_email)
        pword = findViewById(R.id.login_password)

        //Login click behavior
        login_button.setOnClickListener {
            val enteredUname = uname.text.toString()
            val enteredPword = pword.text.toString()

            // Check if the entered username and password match your conditions
            if (enteredUname == "TEST" && enteredPword == "TEST") {
                // If the conditions are met, start the MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                // Handle invalid login here (e.g., display an error message)
                Toast.makeText(applicationContext, "Wrong Username/Password", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        //
        register_button.setOnClickListener {
            val intent = Intent(this, RegisterPage::class.java)
            startActivity(intent)
        }
    }
}