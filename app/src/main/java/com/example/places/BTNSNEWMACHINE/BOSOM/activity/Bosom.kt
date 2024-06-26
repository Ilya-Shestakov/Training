package com.example.places.BTNSNEWMACHINE.BOSOM.activity

import android.app.Dialog
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.places.BTNSNEWMACHINE.BACK.activity.Back
import com.example.places.BTNSNEWMACHINE.BOSOM.DB.DBHalperBosom
import com.example.places.BTNSNEWMACHINE.BOSOM.DB.DatalistBosom
import com.example.places.BTNSNEWMACHINE.BOSOM.adapter.MyAdapterBosom
import com.example.places.BTNSNEWMACHINE.HANDS.activity.Hands
import com.example.places.BTNSNEWMACHINE.LEGS.activity.Legs
import com.example.places.Main.MainActivity
import com.example.places.R
import com.example.places.HalperBtn.Bio
import com.example.places.NewTraining.activity.NewTraining
import com.example.places.HalperBtn.Settings
import com.example.places.Trainings.Trainings.activity.Trainings
import com.google.android.material.navigation.NavigationView

class Bosom : AppCompatActivity() {

    lateinit var toogle: ActionBarDrawerToggle
    private lateinit var db: DBHalperBosom
    lateinit var dbh: DBHalperBosom
    private lateinit var newArrBosom: ArrayList<DatalistBosom>
    private lateinit var adapter: MyAdapterBosom
    lateinit var recyclerViewBosom: RecyclerView
    lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bosom)

        //Find
        val drawerLayoutBosom: DrawerLayout = findViewById(R.id.drawerLayoutBosom)
        val navVoidBosom: NavigationView = findViewById(R.id.navViewBosom)
        recyclerViewBosom = findViewById(R.id.recyclerViewBosom)
        db = DBHalperBosom(this)
        dbh = DBHalperBosom(this)

        recyclerViewBosom.layoutManager = LinearLayoutManager(this)
        recyclerViewBosom.setHasFixedSize(true)
        dispayuser()

        //Drawer
        toogle = ActionBarDrawerToggle(this, drawerLayoutBosom, R.string.open, R.string.close)
        drawerLayoutBosom.addDrawerListener(toogle)
        toogle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navVoidBosom.setNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.nav_training -> MethodDispTrainings()
                //R.id.nav_bio -> MethodOpenBio()
                R.id.nav_menu -> MethodDispMenu()
                R.id.nav_legs -> MethodDispLegs()
                R.id.nav_hands -> MethodDispHands()
                R.id.nav_back -> MethodDispBack()
                R.id.nav_bosom -> MethodDispBosom()
                R.id.nav_newTraining -> MethodDispNewTraining()
                R.id.nav_settings -> MethodDispSettings()
                R.id.nav_exit -> finishAffinity()
            }
            true
        }
    }

    private fun MethodDispSettings() {
        val intent = Intent(this, Settings::class.java)
        startActivity(intent)
        finish()
    }

    private fun MethodDispNewTraining() {
        val intent = Intent(this, NewTraining::class.java)
        startActivity(intent)
        finish()
    }

    private fun MethodDispTrainings() {
        val intent = Intent(this, Trainings::class.java)
        startActivity(intent)
        finish()
    }
    private fun MethodDispLegs() {
        val intent = Intent(this, Legs::class.java)
        startActivity(intent)
        finish()
    }

    private fun MethodDispHands() {
        val intent = Intent(this, Hands::class.java)
        startActivity(intent)
        finish()
    }

    private fun MethodDispBack() {
        val intent = Intent(this, Back::class.java)
        startActivity(intent)
        finish()
    }

    private fun MethodDispBosom() {
        val intent = Intent(this, Bosom::class.java)
        startActivity(intent)
        finish()
    }

    fun btnToBosom(view: View){
        val intent = Intent(this, Settings::class.java)
        startActivity(intent)
        finish()
    }

    private fun MethodOpenBio(){
        val intent = Intent(this, Bio::class.java)
        startActivity(intent)
        finish()
    }

    private fun MethodDispMenu() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun dispayuser() {
        val newcursor: Cursor? = dbh!!.gettext()
        newArrBosom = ArrayList<DatalistBosom>()
        while (newcursor!!.moveToNext()){
            val uname = newcursor.getString(0)
            newArrBosom.add(DatalistBosom(uname))
        }
        adapter = MyAdapterBosom(newArrBosom)
        recyclerViewBosom.adapter = adapter
        adapter.OnItemClickListener(object: MyAdapterBosom.onItemClickListener {
            override fun onItemClick(position: Int) {
                dbh.deleteuserdata(newArrBosom[position].name)
                dispayuser()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toogle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}