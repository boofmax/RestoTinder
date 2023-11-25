package com.mobdeve.s11.restotinder


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mobdeve.s11.restotinder.R

class RegisterPage : ComponentActivity() {
    companion object {
        private const val TAG = "RegisterPage"
    }

    private lateinit var name : EditText
    private lateinit var email : EditText
    private lateinit var pw : EditText
    private lateinit var confirm_pw : EditText
    private lateinit var reg_button : Button

    private lateinit var myFirestoreRecyclerAdapter: MyAdapter

    //DB REFERECEC
    private lateinit var dbRef: FirebaseFirestore

    //Our RecyclerView
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_register)
        dbRef = Firebase.firestore

        name = findViewById(R.id.reg_name)
        email = findViewById(R.id.reg_email)
        pw = findViewById(R.id.reg_password)
        confirm_pw = findViewById(R.id.reg_confirm_password)
        reg_button = findViewById(R.id.register)


        val query = dbRef.collection(MyFirestoreReferences.USERS_COLLECTION)

        reg_button.setOnClickListener {
            val enteredUname = name.text.toString()
            val enteredEmail = email.text.toString()
            val pw = pw.text.toString()
            val confirm_pw = confirm_pw.text.toString()

            // Check if the entered username and password match your conditions
            if (enteredUname.isEmpty() || enteredEmail.isEmpty() || pw.isEmpty() || confirm_pw.isEmpty()) {
                // Handle the case where any of the variables are empty or pw and confirm_pw don't match
                // You can put your error handling code here
                Toast.makeText(applicationContext, "Please fill up all necessary fields", Toast.LENGTH_SHORT).show()

            }
            else if(pw != confirm_pw){
                Toast.makeText(applicationContext, "Passwords do not match", Toast.LENGTH_SHORT).show()
            }
            else {
                // All variables are non-empty, and pw and confirm_pw match
                // You can put your success logic here
                val data: MutableMap<String, Any?> = HashMap()
                data[MyFirestoreReferences.USER_FIELD] = enteredUname
                data[MyFirestoreReferences.PASSWORD_FIELD] = pw

                dbRef.collection(MyFirestoreReferences.USERS_COLLECTION).add(data)
                    .addOnSuccessListener { documentReference ->
                        Log.d(TAG, "User was added written with ID: "+ documentReference.id)
                    }
                    .addOnFailureListener{ e -> Log.w(TAG, "Error adding user",e)}

                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("uname", enteredUname)
                startActivity(intent)
                Toast.makeText(applicationContext, "Welcome, $enteredUname!", Toast.LENGTH_SHORT).show()
            }

        }
    }

}