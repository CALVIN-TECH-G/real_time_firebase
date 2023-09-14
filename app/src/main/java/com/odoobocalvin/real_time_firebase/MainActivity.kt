package com.odoobocalvin.real_time_firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = Firebase.database.reference
        // realtime database refrence
        //https://real-time-firebase-4c525-default-rtdb.firebaseio.com/
        //writting data to firebase
        val txtview = findViewById<TextView>(R.id.ttview)

//
//        database.child("Price").setValue("2000 $")
//
//        // writting custom objects
//        val user1 = user("jack","1234")
//        database.child("Users").setValue(user1)


        //READING CUSTOM OBJECTS TO THE FIREBASE
        val pe = object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               val u1 = snapshot.getValue<user>()
                txtview.text = "UserName${u1?.userName}"+"/n"+"Password:${u1?.password}"
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }


        // reading data from firebase
//
//        val postListener = object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val gold_price = snapshot.value
//                txtview.text = gold_price.toString()
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//        }
//      database.child("Price").addValueEventListener(postListener)
        database.child("user").addValueEventListener(pe)
  }
}