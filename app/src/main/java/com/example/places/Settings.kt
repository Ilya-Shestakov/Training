package com.example.places

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.places.BTNSNEWMACHINE.BACK.activity.Back
import com.example.places.BTNSNEWMACHINE.BOSOM.activity.Bosom
import com.example.places.BTNSNEWMACHINE.HANDS.activity.Hands
import com.example.places.BTNSNEWMACHINE.LEGS.activity.Legs
import com.example.places.HalperBtn.AllTrainers
import com.example.places.Main.MainActivity

class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val btnAll: ConstraintLayout = findViewById(R.id.btnAllTrainers)
        val btnLegs: ConstraintLayout = findViewById(R.id.btnLegs)
        val btnHands: ConstraintLayout = findViewById(R.id.btnHands)
        val btnBack: ConstraintLayout = findViewById(R.id.btnBack)
        val btnBosom: ConstraintLayout = findViewById(R.id.btnBosom)

        btnAll.setOnClickListener{
            MethodDispAllTrainers()
        }

        btnLegs.setOnClickListener {
            MethodDispLegs()
        }
        btnHands.setOnClickListener {
            MethodDispHands()
        }
        btnBack.setOnClickListener {
            MethodDispBack()
        }
        btnBosom.setOnClickListener {
            MethodDispBosom()
        }
    }

    private fun MethodDispLegs() {
        val intent = Intent(this, Legs::class.java)
        startActivity(intent)
        finish()
    }

    private fun MethodDispBosom() {
        val intent = Intent(this, Bosom::class.java)
        startActivity(intent)
        finish()
    }

    private fun MethodDispBack() {
        val intent = Intent(this, Back::class.java)
        startActivity(intent)
        finish()
    }

    private fun MethodDispHands() {
        val intent = Intent(this, Hands::class.java)
        startActivity(intent)
        finish()
    }

    private fun MethodDispAllTrainers() {
        val intent = Intent(this, AllTrainers::class.java)
        startActivity(intent)
        finish()
    }

    fun btnToBack(view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}