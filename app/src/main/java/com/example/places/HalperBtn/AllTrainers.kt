package com.example.places.HalperBtn

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.places.BTNSNEWMACHINE.BACK.DB.DBHalperBack
import com.example.places.BTNSNEWMACHINE.BACK.activity.Back
import com.example.places.BTNSNEWMACHINE.BOSOM.DB.DBHalperBosom
import com.example.places.BTNSNEWMACHINE.BOSOM.activity.Bosom
import com.example.places.BTNSNEWMACHINE.HANDS.DB.DBHalperHands
import com.example.places.BTNSNEWMACHINE.HANDS.activity.Hands
import com.example.places.BTNSNEWMACHINE.LEGS.DB.DBHalperLegs
import com.example.places.BTNSNEWMACHINE.LEGS.activity.Legs
import com.example.places.Main.MainActivity
import com.example.places.PopupMenu.Bio
import com.example.places.R
import com.example.places.Trainings.Trainings.Adapter.DatalistItemTrainer
import com.example.places.Trainings.Trainings.Adapter.MyAdapterAllMachines
import com.example.places.Trainings.Trainings.activity.Trainings
import com.google.android.material.navigation.NavigationView

class AllTrainers : AppCompatActivity() {

    lateinit var toogle: ActionBarDrawerToggle
    private lateinit var dbLegs: DBHalperLegs
    private lateinit var dbHands: DBHalperHands
    private lateinit var dbBack: DBHalperBack
    private lateinit var dbBosom: DBHalperBosom
    private lateinit var adapter: MyAdapterAllMachines
    private lateinit var newArrTrainings: ArrayList<DatalistItemTrainer>
    lateinit var recyclerViewTrainers: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_trainers)

        val drawerLayoutTrainers: DrawerLayout = findViewById(R.id.drawerLayoutTrainers)
        val navViewTrainers: NavigationView = findViewById(R.id.navViewTrainers)

        recyclerViewTrainers = findViewById(R.id.recyclerViewTrainers)

        dbLegs = DBHalperLegs(this)
        dbHands = DBHalperHands(this)
        dbBack = DBHalperBack(this)
        dbBosom = DBHalperBosom(this)

        toogle = ActionBarDrawerToggle(this, drawerLayoutTrainers, R.string.open, R.string.close)
        drawerLayoutTrainers.addDrawerListener(toogle)
        toogle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navViewTrainers.setNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.nav_training -> MethodDispTrainings()
                R.id.nav_bio -> MethodDispBio()
                R.id.nav_menu -> MethodDispMenu()
                R.id.nav_legs -> MethodDispLegs()
                R.id.nav_hands -> MethodDispHands()
                R.id.nav_back -> MethodDispBack()
                R.id.nav_bosom -> MethodDispBosom()
                R.id.nav_exit -> finishAffinity()
            }
            true
        }

        recyclerViewTrainers.layoutManager = LinearLayoutManager(this)
        recyclerViewTrainers.setHasFixedSize(true)
        dispayuserAll()

    }

    private fun dispayuserAll() {
        val newcursorLegs: Cursor? = dbLegs!!.gettext()
        val newcursorHands: Cursor? = dbHands!!.gettext()
        val newcursorBack: Cursor? = dbBack!!.gettext()
        val newcursorBosom: Cursor? = dbBosom!!.gettext()
        newArrTrainings = ArrayList<DatalistItemTrainer>()
        while (newcursorLegs!!.moveToNext()){
            val unameLegs = newcursorLegs.getString(0)
            newArrTrainings.add(DatalistItemTrainer(unameLegs, "Legs"))
        }
        while (newcursorHands!!.moveToNext()){
            val unameHands = newcursorHands.getString(0)
            newArrTrainings.add(DatalistItemTrainer(unameHands, "Hands"))
        }
        while (newcursorBack!!.moveToNext()){
            val unameBack = newcursorBack.getString(0)
            newArrTrainings.add(DatalistItemTrainer(unameBack, "Back"))
        }
        while (newcursorBosom!!.moveToNext()){
            val unameBosom = newcursorBosom!!.getString(0)
            newArrTrainings.add(DatalistItemTrainer(unameBosom, "Bosom"))
        }
        adapter = MyAdapterAllMachines(newArrTrainings)
        recyclerViewTrainers.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toogle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun MethodDispMenu() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun MethodDispTrainings() {
        val intent = Intent(this, Trainings::class.java)
        startActivity(intent)
        finish()
    }

    private fun MethodDispBio(){
        val intent = Intent(this, Bio::class.java)
        startActivity(intent)
        finish()
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

    fun btnToBack(view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}