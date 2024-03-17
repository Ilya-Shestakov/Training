package com.example.places.Trainings.activity

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
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.places.BTNSNEWMACHINE.BACK.activity.Back
import com.example.places.BTNSNEWMACHINE.BOSOM.activity.Bosom
import com.example.places.BTNSNEWMACHINE.HANDS.activity.Hands
import com.example.places.BTNSNEWMACHINE.LEGS.activity.Legs
import com.example.places.Main.MainActivity
import com.example.places.PopupMenu.Bio
import com.example.places.R
import com.example.places.Trainings.Trainings.DB.DBHalperTrainings
import com.example.places.Trainings.Trainings.DB.DatalistTrainings
import com.example.places.Trainings.Trainings.Adapter.MyAdapterTainings
import com.google.android.material.navigation.NavigationView

class Trainings : AppCompatActivity() {

    lateinit var toogle: ActionBarDrawerToggle
    lateinit var dialog: Dialog
    lateinit var db: DBHalperTrainings
    private lateinit var newArrTrainings: ArrayList<DatalistTrainings>
    private lateinit var adapter: MyAdapterTainings
    lateinit var recyclerViewTrainings: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trainings)

        val drawerLayoutTrainings: DrawerLayout = findViewById(R.id.drawerLayoutTrainings)
        val navVoidTrainings: NavigationView = findViewById(R.id.navViewTraining)

        toogle = ActionBarDrawerToggle(this, drawerLayoutTrainings, R.string.open, R.string.close)
        drawerLayoutTrainings.addDrawerListener(toogle)
        toogle.syncState()

        db = DBHalperTrainings(this)

        recyclerViewTrainings = findViewById(R.id.recyclerViewTrinings)

        recyclerViewTrainings.layoutManager = LinearLayoutManager(this)
        recyclerViewTrainings.setHasFixedSize(true)
        dispayuser()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navVoidTrainings.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_training -> MethodDispTrainings()
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

    private fun MethodDispTrainings() {
        val intent = Intent(this, Trainings::class.java)
        startActivity(intent)
        finish()
    }

    fun btnAddMachineToTrainings(view: View) {
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.activity_add_new_training)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
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
    fun btnAddTrainingInDialog(view: View) {
        val name = dialog.findViewById<EditText>(R.id.editTextTraining)
        val names = name.text.toString().trim()

        val dateDay = dialog.findViewById<TextView>(R.id.IdDay).toString()
        val dateMonth = dialog.findViewById<TextView>(R.id.IdMonth).toString()
        val dateYear = dialog.findViewById<TextView>(R.id.IdYear).toString()
        val date = "$dateDay : $dateMonth : $dateYear"

        val savedataTraining = db.saveuserdatatrainings(names, date)

        if (TextUtils.isEmpty(names)) {
            Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show()
            dialog.cancel()
        } else {
            if (savedataTraining) {
                Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show()
                if (names != "") {
                    dispayuser()
                }
                dialog.cancel()
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                dialog.cancel()
            }
        }
    }
    private fun dispayuser() {

        val newcursor: Cursor? = db!!.gettext()
        newArrTrainings = ArrayList<DatalistTrainings>()
        while (newcursor!!.moveToNext()){
            val uname = newcursor.getString(0)
            newArrTrainings.add(DatalistTrainings(uname, "00:00:00"))
        }
        adapter = MyAdapterTainings(newArrTrainings)
        recyclerViewTrainings.adapter = adapter

//        adapter.OnItemClickListener(object: MyAdapterTainings.onItemClickListener {
//            override fun onItemClick(position: Int) {
//                val intent = Intent(this@Trainings, EditTraining::class.java)
//                //intent.putExtra("name", newArrBack[position].name)
//                startActivity(intent)
//                finish()
//            }
//        })

    }
}