package com.example.places.BTNSNEWMACHINE.BOSOM.delete

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.places.BTNSNEWMACHINE.BOSOM.DB.DBHalperBosom
import com.example.places.BTNSNEWMACHINE.BOSOM.activity.Bosom
import com.example.places.R
import com.example.places.Trainings.Trainings.activity.Trainings

class EditBosomMachine : AppCompatActivity() {

    private lateinit var db: DBHalperBosom

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_bosom_machine)

        var name = findViewById<EditText>(R.id.editTextRedactBosom)
        val del = findViewById<ConstraintLayout>(R.id.uploadBosom)

        db = DBHalperBosom(this)

        name.setText(intent.getStringExtra("name"))

        del.setOnClickListener {
            val names = name.text.toString()
            val deletedata = db.deleteuserdata(names)

            if (deletedata==true){
                Toast.makeText(this, "Delete contact", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@EditBosomMachine, Bosom::class.java)
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
        val intent = Intent(this, Bosom::class.java)
        startActivity(intent)
        finish()
    }
}