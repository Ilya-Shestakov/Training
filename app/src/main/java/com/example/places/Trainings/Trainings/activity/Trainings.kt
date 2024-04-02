package com.example.places.Trainings.Trainings.activity

import android.app.Dialog
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.places.BTNSNEWMACHINE.BACK.activity.Back
import com.example.places.BTNSNEWMACHINE.BOSOM.activity.Bosom
import com.example.places.BTNSNEWMACHINE.HANDS.activity.Hands
import com.example.places.BTNSNEWMACHINE.LEGS.activity.Legs
import com.example.places.Main.MainActivity
import com.example.places.HalperBtn.Bio
import com.example.places.NewTraining.activity.NewTraining
import com.example.places.R
import com.example.places.HalperBtn.Settings
import com.example.places.NewTraining.Adapter.MyAdapterNewTraining
import com.example.places.Trainings.Trainings.Adapter.MyAdapterTrainings
import com.example.places.Trainings.Trainings.DB.DBTrainings
import com.example.places.Trainings.Trainings.DB.DatalistTraining
import com.google.android.material.navigation.NavigationView

class Trainings : AppCompatActivity() {

    lateinit var toogle: ActionBarDrawerToggle
    lateinit var dialog: Dialog
    lateinit var dbh: DBTrainings
    lateinit var db: DBTrainings
    private lateinit var newArrTrainings: ArrayList<DatalistTraining>
    private lateinit var adapter: MyAdapterTrainings
    lateinit var recyclerViewTrainings: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trainings)

        dbh = DBTrainings(this)
        db = DBTrainings(this)

        val drawerLayoutTrainings: DrawerLayout = findViewById(R.id.drawerLayoutTrainings)
        val navVoidTrainings: NavigationView = findViewById(R.id.navViewTraining)

        toogle = ActionBarDrawerToggle(this, drawerLayoutTrainings, R.string.open, R.string.close)
        drawerLayoutTrainings.addDrawerListener(toogle)
        toogle.syncState()

        recyclerViewTrainings = findViewById(R.id.recyclerViewTrinings)

        recyclerViewTrainings.layoutManager = LinearLayoutManager(this)
        recyclerViewTrainings.setHasFixedSize(true)
        dispayuser()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navVoidTrainings.setNavigationItemSelectedListener {
            when (it.itemId) {
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

    fun btnToTrainings(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
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
    private fun MethodOpenBio() {
        val intent = Intent(this, Bio::class.java)
        startActivity(intent)
        finish()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toogle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun dispayuser() {
        val newcursor: Cursor? = dbh.gettext()
        newArrTrainings = ArrayList<DatalistTraining>()
        while (newcursor!!.moveToNext()){
            val uname = newcursor.getString(0)
            val udate = newcursor.getString(1)
            val uweight = newcursor.getString(2)
            newArrTrainings.add(DatalistTraining(uname, udate, uweight))
        }
        adapter = MyAdapterTrainings(newArrTrainings)
        recyclerViewTrainings.adapter = adapter
        adapter.OnItemClickListener(object: MyAdapterTrainings.onItemClickListener {
            override fun onItemClick(position: Int) {
                dialog = Dialog(this@Trainings)
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog.setContentView(R.layout.delete_question)
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.show()

                val btnYes: ConstraintLayout = dialog.findViewById(R.id.btnDelete)
                val btnNo: ConstraintLayout = dialog.findViewById(R.id.btnNo)

                btnYes.setOnClickListener {
                    db.deleteuserdatatraining(newArrTrainings[position].name, newArrTrainings[position].weight, newArrTrainings[position].date)
                    dispayuser()
                    dialog.cancel()
                }
                btnNo.setOnClickListener{
                    dispayuser()
                    dialog.cancel()
                }
            }
        })
    }

}