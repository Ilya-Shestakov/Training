package com.example.places.Trainings.Trainings.activity

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
import android.widget.Button
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
import com.example.places.HalperBtn.Bio
import com.example.places.R
import com.example.places.Trainings.Trainings.DB.DatalistTrainings
import com.example.places.Trainings.Trainings.Adapter.MyAdapterTainings
import com.example.places.Trainings.Trainings.DB.DBHalperDate
import com.example.places.Trainings.Trainings.DB.DBHalperTrainings
import com.google.android.material.navigation.NavigationView

class Trainings : AppCompatActivity() {

    lateinit var toogle: ActionBarDrawerToggle
    lateinit var dialog: Dialog
    lateinit var dbh: DBHalperTrainings
    lateinit var dbhDate: DBHalperDate
    private lateinit var newArrTrainings: ArrayList<DatalistTrainings>
    private lateinit var adapter: MyAdapterTainings
    lateinit var recyclerViewTrainings: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trainings)

        dbh = DBHalperTrainings(this)
        dbhDate = DBHalperDate(this)

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

        var dateDay = dialog.findViewById<TextView>(R.id.IdDay).text
        var dateMonth = dialog.findViewById<TextView>(R.id.IdMonth).text
        var dateYear = dialog.findViewById<TextView>(R.id.IdYear).text

        val date = "$dateDay : $dateMonth : $dateYear"

        dateDay = 0.toString()
        dateMonth = 0.toString()
        dateYear = 0.toString()

        //
        val savedataTraining = dbh.saveuserdata(names)
        val savedataTrainingDate = dbhDate.saveuserdata(date)
        //

        if (TextUtils.isEmpty(names)) {
            Toast.makeText(this, "Add trainer's name", Toast.LENGTH_SHORT).show()
        } else {
            if (savedataTrainingDate) {
                Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show()
                dispayuser()
                dialog.cancel()
            } else {
                Toast.makeText(this, "$savedataTraining, $savedataTrainingDate", Toast.LENGTH_SHORT).show()
                dispayuser()
                dialog.cancel()
            }
        }
    }

    fun dispayuser() {
        val newcursor: Cursor? = dbh.gettext()
        val newcursorDate: Cursor? = dbhDate.gettext()
        newArrTrainings = ArrayList<DatalistTrainings>()
        while (newcursorDate!!.moveToNext() and newcursor!!.moveToNext()){
            val uname = newcursor.getString(0)
            val udate = newcursorDate.getString(0)
            newArrTrainings.add(DatalistTrainings(uname, udate))
        }
        adapter = MyAdapterTainings(newArrTrainings)
        recyclerViewTrainings.adapter = adapter

        adapter.OnItemClickListener(object: MyAdapterTainings.onItemClickListener {
            override fun onItemClick(position: Int) {

                dialog = Dialog(this@Trainings)
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog.setContentView(R.layout.activity_question_delete)
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.show()

                val btnYes: Button = dialog.findViewById(R.id.btnYes)
                val btnNo: Button = dialog.findViewById(R.id.btnNo)

                btnYes.setOnClickListener {
                    dbh.deleteuserdata(newArrTrainings[position].name)
                    dbhDate.deleteuserdata(newArrTrainings[position].date)
                    dispayuser()
                    dialog.cancel()
                }
                btnNo.setOnClickListener {
                    dialog.cancel()
                }
            }
        }   )
    }

    // UP DOWN COUNT

    var countDay = 0
    var countMonth = 0
    var countYear = 0

    fun btnUpDay(view: View){
        if (countDay < 31){
            var idDay = dialog.findViewById<TextView>(R.id.IdDay)
            countDay++
            idDay.setText(countDay.toString())
        }
    }
    fun btnDownDay(view: View){
        var idDay = dialog.findViewById<TextView>(R.id.IdDay)
        if (countDay >= 1){
            countDay--
        }
        idDay.setText(countDay.toString())
    }
    fun btnUpMonth(view: View){
        if(countMonth<12){
            var idMonth = dialog.findViewById<TextView>(R.id.IdMonth)
            countMonth++
            idMonth.setText(countMonth.toString())
        }
    }
    fun btnDownMonth(view: View){
        var idMonth = dialog.findViewById<TextView>(R.id.IdMonth)
        if (countMonth >= 1){
            countMonth--
        }
        idMonth.setText(countMonth.toString())
    }
    fun btnUpYear(view: View){
        if (countYear < 99){
            var idYear = dialog.findViewById<TextView>(R.id.IdYear)
            countYear++
            idYear.setText(countYear.toString())
        }
    }
    fun btnDownYear(view: View){
        var idYear = dialog.findViewById<TextView>(R.id.IdYear)
        if (countYear >= 1){
            countYear--
        }
        idYear.setText(countYear.toString())
    }


    // FINISH UP DOWN COUNT

}