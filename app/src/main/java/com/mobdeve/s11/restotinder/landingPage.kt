package com.mobdeve.s11.restotinder


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class landingPage : ComponentActivity(){
    companion object {
        private const val TAG = "LoginActivity"
    }

    //load data
    //private val restaurants: ArrayList<RestaurantModel> = DataGenerator.loadData()

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

            val db = Firebase.firestore
            val usersRef = db.collection(MyFirestoreReferences.USERS_COLLECTION)

            //Query to check if user collection reference is correct

            val query = usersRef.whereEqualTo(
                MyFirestoreReferences.USER_FIELD,enteredUname)
                .whereEqualTo(MyFirestoreReferences.PASSWORD_FIELD,enteredPword)

            // Perform the query and add an OnCompleteListener to know when the query has finished.
            // Logic in the OnCompleteListener is performed on completion of the query.
            query.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // If there are no results, then there is no sign of the username in the DB.
                    if (task.result.isEmpty) {
                        Toast.makeText(applicationContext, "Invalid Username/Password. Please try again.", Toast.LENGTH_SHORT)
                            .show()
                    } else { // Otherwise, move ot the mainPage with the usernaem specified
                        moveToRestoPageActivity(enteredUname)
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.exception)
                }
            }

        }
        //
        register_button.setOnClickListener {
            val intent = Intent(this, RegisterPage::class.java)
            startActivity(intent)
        }
    }

    private fun moveToRestoPageActivity(username: String){
        val intent = Intent(this, MainActivity::class.java)
        //we can also pass the username later on. for aesthetic.

        startActivity(intent)


    }
}