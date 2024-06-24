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
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
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
import com.example.places.Trainings.Trainings.DB.DBTrainings
import com.example.places.Trainings.Trainings.activity.Trainings
import com.google.android.material.navigation.NavigationView
import java.text.SimpleDateFormat
import java.util.Date

class NewTraining : AppCompatActivity(), PopupMenu.OnMenuItemClickListener {

    lateinit var toogle: ActionBarDrawerToggle

    lateinit var db: DBHalperNewTraining
    lateinit var dbSave: DBTrainings
    lateinit var dbL: DBHalperLegs
    lateinit var dbH: DBHalperHands
    lateinit var dbB: DBHalperBack
    lateinit var dbBs: DBHalperBosom

    var staticWeight: Int = 0

    lateinit var dialog: Dialog

    lateinit var newArrayAttitude: ArrayList<DatalistNewAttitude>
    private lateinit var adapter: MyAdapterNewTraining
    private lateinit var recyclerViewAttitude: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_training)


        var textItogWeight = findViewById<TextView>(R.id.textItogWeight)

        textItogWeight.setText(staticWeight.toString())

        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayoutNewTrainings)
        val navVoid: NavigationView = findViewById(R.id.navView)

        db = DBHalperNewTraining(this)
        dbSave = DBTrainings(this)
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
            R.id.btnOtherPopup -> {
                editTextTypeDialog.setText("Other")
                return true
            }
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





    //                            EDITS





    fun methodEditName(view: View){

        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.edit_training_name_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

    }


    fun methodEditDate(view: View){
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.edit_training_date_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }




    //                              Save data ATTITUDE




    fun btnAddAttitude(view: View){


        // Weight
        var editTextWeightDialog = dialog.findViewById<EditText>(R.id.editTextWeightDialog).text.toString().trim()
        var editTextCountDialog = dialog.findViewById<EditText>(R.id.editTextCountDialog).text.toString().trim()

        var count = "1"

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

            var summEdits = "|  $editTextCountDialog/$editTextWeightDialog"

            var textItogWeight = findViewById<TextView>(R.id.textItogWeight)        // Счётчик на экране

            var itogWeightAtt = textItogWeight.text.toString().toInt() + (editTextCountDialog.toInt() * editTextWeightDialog.toInt())     // Пром значение

            textItogWeight.setText(itogWeightAtt.toString())

            db.saveuserdata(editTextNameDialog, summEdits, count, editTextTypeDialog)

            dialog.cancel()

            dispayuser()

        } else{
            Toast.makeText(this, "Add name", Toast.LENGTH_SHORT).show()
        }
    }



    //                              DISPLAY


    fun dispayuser() {

        val newcursor: Cursor? = db.gettext()
        newArrayAttitude = ArrayList<DatalistNewAttitude>()

        //            CURSOR

        while (newcursor!!.moveToNext()){

            val uname = newcursor.getString(0)
            val uweight = newcursor.getString(1)
            val ucount = newcursor.getString(2)
            val utype = newcursor.getString(3)

            newArrayAttitude.add(DatalistNewAttitude(uname, uweight, ucount, utype))

            var resOdin = splitString(newcursor.getString(1).toString())

            staticWeight += ((resOdin.first).toInt() * (resOdin.second).toInt()) * newcursor.getString(2).toInt()

        }


        var textItogWeight = findViewById<TextView>(R.id.textItogWeight)       // Счётчик на экране

        textItogWeight.setText(staticWeight.toString())

        adapter = MyAdapterNewTraining(newArrayAttitude)
        recyclerViewAttitude.adapter = adapter



        //              TAP



        adapter.OnItemClickListener(object: MyAdapterNewTraining.onItemClickListener {
            override fun onItemClick(position: Int) {

                //Toast.makeText(this@NewTraining, "$countWeight", Toast.LENGTH_SHORT).show()

                dialog = Dialog(this@NewTraining)
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog.setContentView(R.layout.delete_question)
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.show()

                val btnDelete: ConstraintLayout = dialog.findViewById(R.id.btnDeleteInNewTR)
                val btnCopy: ConstraintLayout = dialog.findViewById(R.id.btnCopyInNewTR)

                btnDelete.setOnClickListener {



//                    var textItogWeight = findViewById<TextView>(R.id.textItogWeight)       // Счётчик на экране
//
//                    val result = splitString(newArrayAttitude[position].weight)
//
//                    var weightAttitued = (result.first).toInt() * (result.second).toInt()
//
//                    staticWeight = (textItogWeight.text.toString().toInt() - weightAttitued)
//
//                    textItogWeight.setText(staticWeight.toString())

                    db.deletecount(newArrayAttitude[position].name, newArrayAttitude[position].weight, newArrayAttitude[position].count, newArrayAttitude[position].type)
                    dispayuser()
                    dialog.cancel()

//                    var textItogWeight = findViewById<TextView>(R.id.textItogWeight)        // Счётчик на экране
//
//                    var itogWeightAtt = textItogWeight.text.toString().toInt() - (newArrayAttitude[position].count.toInt() * newArrayAttitude[position].weight.toInt())     // Пром значение
//
//                    textItogWeight.setText(itogWeightAtt.toString())

                }

                btnCopy.setOnClickListener {



                    db.updatauserdatanewtrainingsCount(newArrayAttitude[position].name, newArrayAttitude[position].weight, newArrayAttitude[position].type, newArrayAttitude[position].count, (newArrayAttitude[position].count.toInt() + 1).toString())
                    dispayuser()
                    dialog.cancel()

//
//
//                    var textItogWeight = findViewById<TextView>(R.id.textItogWeight)       // Счётчик на экране
//
//                    val result = splitString(newArrayAttitude[position].weight)
//
//                    var weightAttitued = (result.first).toInt() * (result.second).toInt()
//
//                    staticWeight = (textItogWeight.text.toString().toInt() + weightAttitued)
//
//                    textItogWeight.setText(staticWeight.toString())



                }
            }
        })
    }






    //                           FUN SPLIT STRING WEIGHT
    fun splitString(inputString: String): Pair<String, String> {
        val index = inputString.indexOf('/')
        return if (index != -1) {
            Pair(inputString.substring(3, index), inputString.substring(index + 1))
        } else {
            Pair("Error: Slash not found in the input string", "")
        }
    }


//
//
//    lateinit var frontSlesh: String
//    lateinit var backSlesh: String
//    var indexElement: Int = 0
//    var ItogWeight: Int = 0
//
//
//    fun fullWeight(string: String): Int{
//
//        for(i in 0..string.length - 1){
//            while (string[i] != '/'){
//                frontSlesh += string[i].toString()
//                indexElement = i+1
//            }
//            for (g in indexElement..string.length){
//                backSlesh != string[g].toString()
//            }
//        }
//
//        ItogWeight = frontSlesh.toInt() * backSlesh.toInt()
//
//        return ItogWeight
//
//        ItogWeight = 0
//        frontSlesh = ""
//        backSlesh = ""
//    }




//                          DESTROY


    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }




    fun methodSaveTraining(view: View){

        dialog = Dialog(this@NewTraining)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.question_save)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        val btnYes: ConstraintLayout = dialog.findViewById(R.id.btnSave)
        val btnNo: ConstraintLayout = dialog.findViewById(R.id.btnNo)

        btnYes.setOnClickListener {

            val TitleDate = findViewById<TextView>(R.id.TitleDate).text
            val TitleName = findViewById<TextView>(R.id.TitleName).text

            var textItogWeight = findViewById<TextView>(R.id.textItogWeight)  // Счётчик на экране

            dbSave.savedatatrainings(TitleName.toString(), textItogWeight.text.toString() + "кг", TitleDate.toString())

            db.deleteAllData("Legs")
            db.deleteAllData("Hands")
            db.deleteAllData("Back")
            db.deleteAllData("Bosom")
            db.deleteAllData("Other")


            val intent = Intent(this@NewTraining, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        btnNo.setOnClickListener {
            dispayuser()
            dialog.cancel()
        }
    }

    fun btnCheckName(view: View){
        var titleName: TextView = findViewById(R.id.TitleName)
        var editName: EditText = dialog.findViewById(R.id.editTextNewTrainings)
        titleName.setText(editName.text)
        dialog.cancel()
    }
    fun btnCheckDate(view: View){
        var titleDate: TextView = findViewById(R.id.TitleDate)

        var dateDay = dialog.findViewById<TextView?>(R.id.IdDay).text
        var dateMonth = dialog.findViewById<TextView?>(R.id.IdMonth).text
        var dateYear = dialog.findViewById<TextView?>(R.id.IdYear).text

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

        dialog.cancel()
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

    fun btnToBackInTraining(view: View){
        val intent = Intent(this@NewTraining, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


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



}