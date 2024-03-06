package com.example.places.BTNSNEWMACHINE.LEGS.dialogs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.places.BTNSNEWMACHINE.LEGS.DB.DBHalperLegs
import com.example.places.BTNSNEWMACHINE.LEGS.activity.Legs
import com.example.places.R

class EditLegsMachine : AppCompatActivity() {

    private lateinit var db: DBHalperLegs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_legs_machine)

        var name = findViewById<EditText>(R.id.editTextRedact)
        val del = findViewById<ConstraintLayout>(R.id.upload)

        db = DBHalperLegs(this)

        name.setText(intent.getStringExtra("name"))

        del.setOnClickListener {
            val names = name.text.toString()
            val deletedata = db.deleteuserdata(names)

            if (deletedata==true){
                Toast.makeText(this, "Delete contact", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@EditLegsMachine, Legs::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        finishAffinity()
    }

}