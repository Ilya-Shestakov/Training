package com.example.places.BTNSNEWMACHINE.HANDS.delete

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.places.BTNSNEWMACHINE.HANDS.DB.DBHalperHands
import com.example.places.BTNSNEWMACHINE.HANDS.activity.Hands
import com.example.places.R

class EditHandsMachine : AppCompatActivity() {

    private lateinit var db: DBHalperHands

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_hands_machine)

        var name = findViewById<EditText>(R.id.editTextRedactHand)
        val del = findViewById<ConstraintLayout>(R.id.uploadHand)

        db = DBHalperHands(this)



        name.setText(intent.getStringExtra("name"))

        del.setOnClickListener {
            val names = name.text.toString()
            val deletedata = db.deleteuserdata(names)

            if (deletedata==true){
                Toast.makeText(this, "Delete contact", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@EditHandsMachine, Hands::class.java)
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