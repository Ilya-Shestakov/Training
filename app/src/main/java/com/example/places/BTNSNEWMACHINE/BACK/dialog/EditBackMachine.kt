package com.example.places.BTNSNEWMACHINE.BACK.dialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.places.BTNSNEWMACHINE.BACK.DB.DBHalperBack
import com.example.places.BTNSNEWMACHINE.BACK.activity.Back
import com.example.places.BTNSNEWMACHINE.HANDS.activity.Hands
import com.example.places.R

class EditBackMachine : AppCompatActivity() {

    private lateinit var db: DBHalperBack

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_back_machine)

        var name = findViewById<EditText>(R.id.editTextRedactBack)
        val del = findViewById<ConstraintLayout>(R.id.uploadBack)

        db = DBHalperBack(this)



        name.setText(intent.getStringExtra("name"))

        del.setOnClickListener {
            val names = name.text.toString()
            val deletedata = db.deleteuserdata(names)

            if (deletedata==true){
                Toast.makeText(this, "Delete contact", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@EditBackMachine, Back::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show()
            }
        }
    }

}