package com.example.realtime_assigment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.realtime_assigment.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    var count : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding : ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Write a message to the database
        val database = Firebase.database
        val myRef = database.getReference()

        binding.buttonSave.setOnClickListener {
            binding.buttonSave.setOnClickListener{
                var name = binding.editTextName.text.toString()
                var number = binding.editTextNumber.text.toString()
                var address = binding.editTextAddress.text.toString()

                val person = hashMapOf(
                    "name" to name,
                    "number" to number,
                    "address" to address
                )

                myRef.child("person").child("$count").setValue(person)
                count++
                Toast.makeText(applicationContext,"Success",Toast.LENGTH_SHORT).show()
        }

         binding.getData.setOnClickListener {
             myRef.addValueEventListener(object: ValueEventListener {

                 override fun onDataChange(snapshot: DataSnapshot) {

                     val value = snapshot.getValue()
                     binding.getData.text = value.toString()
                     Toast.makeText(applicationContext,"Success",Toast.LENGTH_SHORT).show()

                 }

                 override fun onCancelled(error: DatabaseError) {
                     Toast.makeText(applicationContext,"Faild",Toast.LENGTH_SHORT).show()

                 }

             })
         }

        }


    }
}