package com.example.places

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.drawerlayout.widget.DrawerLayout
import com.example.places.BTNSNEWMACHINE.BACK.activity.Back
import com.example.places.BTNSNEWMACHINE.BOSOM.activity.Bosom
import com.example.places.BTNSNEWMACHINE.HANDS.activity.Hands
import com.example.places.BTNSNEWMACHINE.LEGS.activity.Legs
import com.example.places.nav_menu.bio
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var toogle: ActionBarDrawerToggle
    private lateinit var dialog: Dialog
    lateinit var valueAge: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val navVoid: NavigationView = findViewById(R.id.navView)
        val btnLegs: ConstraintLayout = findViewById(R.id.btnLegs)
        val btnHands: ConstraintLayout = findViewById(R.id.btnHands)
        val btnBack: ConstraintLayout = findViewById(R.id.btnBack)
        val btnBosom: ConstraintLayout = findViewById(R.id.btnBosom)

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

        toogle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navVoid.setNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.nav_bio -> MethodOpenBio()
                R.id.nav_menu -> MethodDispMenu()
                R.id.nav_legs -> MethodDispLegs()
                R.id.nav_hands -> MethodDispHands()
                R.id.nav_back -> MethodDispBack()
                R.id.nav_bosom -> MethodDispBosom()
                R.id.nav_exit -> finishAffinity()
            }
            true

        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toogle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    private fun MethodDispLegs() {
        val intent = Intent(this, Legs::class.java)
        startActivity(intent)
        finish()
    }

    private fun MethodDispMenu() {
        val intent = Intent(this, MainActivity::class.java)
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

    private fun MethodOpenBio(){
        val intent = Intent(this, bio::class.java)
        startActivity(intent)
        finish()
    }


}