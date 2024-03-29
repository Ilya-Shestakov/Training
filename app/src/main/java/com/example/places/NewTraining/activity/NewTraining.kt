package com.example.places.NewTraining.activity

import android.app.Dialog
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
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
import com.example.places.HalperBtn.Bio
import com.example.places.Main.MainActivity
import com.example.places.R
import com.example.places.HalperBtn.Settings
import com.example.places.NewTraining.Adapter.MyAdapterNewTraining
import com.example.places.NewTraining.DB.DBHalperNewTraining
import com.example.places.NewTraining.DB.DatalistNewAttitude
import com.example.places.Trainings.Trainings.DB.DBHalperTrainings
import com.example.places.Trainings.Trainings.activity.Trainings
import com.google.android.material.navigation.NavigationView
import java.text.SimpleDateFormat
import java.util.Date

class NewTraining : AppCompatActivity(), PopupMenu.OnMenuItemClickListener {

    lateinit var toogle: ActionBarDrawerToggle
    lateinit var db: DBHalperNewTraining
    lateinit var dbSave: DBHalperTrainings
    lateinit var dbL: DBHalperLegs
    lateinit var dbH: DBHalperHands
    lateinit var dbB: DBHalperBack
    lateinit var dbBs: DBHalperBosom
    lateinit var dialog: Dialog
    lateinit var newArrayAttitude: ArrayList<DatalistNewAttitude>
    private lateinit var adapter: MyAdapterNewTraining
    private lateinit var recyclerViewAttitude: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_training)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayoutNewTrainings)
        val navVoid: NavigationView = findViewById(R.id.navView)

        db = DBHalperNewTraining(this)
        dbSave = DBHalperTrainings(this)
        dbL = DBHalperLegs(this)
        dbH = DBHalperHands(this)
        dbB = DBHalperBack(this)
        dbBs = DBHalperBosom(this)

        recyclerViewAttitude = findViewById(R.id.recyclerViewAttitude)


        recyclerViewAttitude.layoutManager = LinearLayoutManager(this)
        recyclerViewAttitude.setHasFixedSize(true)
        dispayuser()

        toogle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var titleDate: TextView = findViewById(R.id.TitleDate)
        var date = Date()
        var s = SimpleDateFormat("dd/MM/yyyy")
        var dateStr: String = s.format(date)
        titleDate.setText(dateStr)

        navVoid.setNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.nav_training -> MethodDispTrainings()
                R.id.nav_bio -> MethodOpenBio()
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

    fun methodeditTextTypeDialog(view: View){
        var editTextTypeDialog = dialog.findViewById<TextView>(R.id.editTextTypeDialog)

        editTextTypeDialog.setOnClickListener {
            val popup = PopupMenu(this@NewTraining, it)
            popup.setOnMenuItemClickListener(this@NewTraining)
            val inflater = popup.menuInflater
            inflater.inflate(R.menu.opening_btn, popup.menu)
            popup.show()
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        var editTextTypeDialog = dialog.findViewById<TextView>(R.id.editTextTypeDialog)
        return when (item?.itemId){
            R.id.btnLegsPopup -> {
                editTextTypeDialog.setText("Legs")
                return true
            }
            R.id.btnHandsPopup -> {
                editTextTypeDialog.setText("Hands")
                return true
            }
            R.id.btnBackPopup -> {
                editTextTypeDialog.setText("Back")
                return true
            }
            R.id.btnBosomPopup -> {
                editTextTypeDialog.setText("Bosom")
                return true
            }
            else -> {false}
        }
    }





    //                          DIALOG






    fun btnAddAttitudeInMain(view: View){
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_new_attitude)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }







    //                              Save data






    fun btnAddAttitude(view: View){

        var editTextWeightDialog = dialog.findViewById<EditText>(R.id.editTextWeightDialog).text.toString().trim()
        var editTextCountDialog = dialog.findViewById<EditText>(R.id.editTextCountDialog).text.toString().trim()

        if (editTextCountDialog == ""){
            editTextCountDialog = "0"
        }
        if (editTextWeightDialog == ""){
            editTextWeightDialog = "0"
        }

        var editTextNameDialog = dialog.findViewById<EditText>(R.id.editTextNameDialog).text.toString().trim()
        var editTextTypeDialog = dialog.findViewById<TextView>(R.id.editTextTypeDialog).text.toString()

        if (editTextTypeDialog == ""){
            editTextTypeDialog = "Other"
        }
        if (editTextTypeDialog == "Legs"){
            dbL.saveuserdata(editTextNameDialog)
        }
        if (editTextTypeDialog == "Hands"){
            dbH.saveuserdata(editTextNameDialog)
        }
        if (editTextTypeDialog == "Back"){
            dbB.saveuserdata(editTextNameDialog)
        }
        if (editTextTypeDialog == "Bosom"){
            dbBs.saveuserdata(editTextNameDialog)
        }
        if (editTextNameDialog != ""){
            var notNeeed = editTextCountDialog.toInt() * editTextWeightDialog.toInt()
            var summEdits: String = notNeeed.toString()

            db.saveuserdata(editTextNameDialog, summEdits, editTextTypeDialog)
//
//            val cursorWeight: Cursor? = db.getweight()
//            val weightIt = cursorWeight?.getString(0)

//            db.savedataWeight(editTextWeightDialog)

            dialog.cancel()

            dispayuser()

        } else{
            Toast.makeText(this, "Add name", Toast.LENGTH_SHORT).show()
        }
    }



    //                              DISPLAY


    private var countWeight = 0
    private var countWeight2 = 0

//                                   SAVE

    fun methodSaveTraining(view: View){

        val TitleDate = findViewById<TextView>(R.id.TitleDate).text
        val TitleName = findViewById<TextView>(R.id.TitleName).text

        dbSave.saveuserdata(TitleName.toString(), countWeight.toString(), TitleDate.toString())

        countWeight2 = 0
        countWeight = 0

        val intent = Intent(this@NewTraining, MainActivity::class.java)
        startActivity(intent)
        finish()

    }


    fun dispayuser() {

        val newcursor: Cursor? = db.gettext()
        newArrayAttitude = ArrayList<DatalistNewAttitude>()
        while (newcursor!!.moveToNext()){

            val uname = newcursor.getString(0)
            val uweight = newcursor.getString(1)
            val utype = newcursor.getString(2)

            newArrayAttitude.add(DatalistNewAttitude(uname, uweight, utype))

            countWeight2 = uweight.toString().toInt()
        }
        countWeight += countWeight2
        countWeight2 = 0
        adapter = MyAdapterNewTraining(newArrayAttitude)
        recyclerViewAttitude.adapter = adapter
        adapter.OnItemClickListener(object: MyAdapterNewTraining.onItemClickListener {
            override fun onItemClick(position: Int) {

                Toast.makeText(this@NewTraining, "$countWeight", Toast.LENGTH_SHORT).show()

                dialog = Dialog(this@NewTraining)
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog.setContentView(R.layout.activity_question_delete)
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.show()

                val btnYes: Button = dialog.findViewById(R.id.btnCopy)
                val btnNo: Button = dialog.findViewById(R.id.btnDelete)

                btnYes.setOnClickListener {
                    db.saveuserdata(newArrayAttitude[position].name, newArrayAttitude[position].weight, newArrayAttitude[position].type)
                    dispayuser()
                    dialog.cancel()
                }
                btnNo.setOnClickListener {
                    db.deleteuserdata(newArrayAttitude[position].name, newArrayAttitude[position].weight, newArrayAttitude[position].type)
                    dispayuser()
                    dialog.cancel()
//                    countWeight -= newArrayAttitude[position].weight.toInt()
                }
            }
        })
    }


    fun btnCheckName(view: View){
        var titleName: TextView = findViewById(R.id.TitleName)
        var editName: EditText = findViewById(R.id.editTextNewTrainings)
        titleName.setText(editName.text)
    }
    fun btnCheckDate(view: View){
        var titleDate: TextView = findViewById(R.id.TitleDate)

        var dateDay = findViewById<TextView?>(R.id.IdDay).text
        var dateMonth = findViewById<TextView?>(R.id.IdMonth).text
        var dateYear = findViewById<TextView?>(R.id.IdYear).text

        var date = ""

        if (dateYear.toString().toInt() <= 9){
            if (dateMonth.toString().toInt() <= 9){
                date = "$dateDay/0$dateMonth/200$dateYear"
            }
            else{
                date = "$dateDay/$dateMonth/200$dateYear"
            }
        }
        else{
            if (dateMonth.toString().toInt() <= 9){
                date = "$dateDay/0$dateMonth/20$dateYear"
            }
            else{
                date = "$dateDay/$dateMonth/20$dateYear"
            }
        }
        titleDate.setText(date)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toogle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun MethodDispTrainings() {
        val intent = Intent(this, Trainings::class.java)
        startActivity(intent)
        finish()
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
        val intent = Intent(this, Bio::class.java)
        startActivity(intent)
        finish()
    }



    var countDay = 0
    var countMonth = 0
    var countYear = 0

    fun btnUpDay(view: View){
        if (countDay < 31){
            var idDay = findViewById<TextView>(R.id.IdDay)
            countDay++
            idDay.setText(countDay.toString())
        }
    }
    fun btnDownDay(view: View){
        var idDay = findViewById<TextView>(R.id.IdDay)
        if (countDay >= 1){
            countDay--
        }
        idDay.setText(countDay.toString())
    }
    fun btnUpMonth(view: View){
        if(countMonth<12){
            var idMonth = findViewById<TextView>(R.id.IdMonth)
            countMonth++
            idMonth.setText(countMonth.toString())
        }
    }
    fun btnDownMonth(view: View){
        var idMonth = findViewById<TextView>(R.id.IdMonth)
        if (countMonth >= 1){
            countMonth--
        }
        idMonth.setText(countMonth.toString())
    }
    fun btnUpYear(view: View){
        if (countYear < 99){
            var idYear = findViewById<TextView>(R.id.IdYear)
            countYear++
            idYear.setText(countYear.toString())
        }
    }
    fun btnDownYear(view: View){
        var idYear = findViewById<TextView>(R.id.IdYear)
        if (countYear >= 1){
            countYear--
        }
        idYear.setText(countYear.toString())
    }



}