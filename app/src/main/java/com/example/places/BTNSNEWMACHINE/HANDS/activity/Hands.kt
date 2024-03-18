package com.example.places.BTNSNEWMACHINE.HANDS.activity

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
import com.example.places.BTNSNEWMACHINE.BOSOM.activity.Bosom
import com.example.places.BTNSNEWMACHINE.HANDS.delete.EditHandsMachine
import com.example.places.BTNSNEWMACHINE.HANDS.DB.DBHalperHands
import com.example.places.BTNSNEWMACHINE.HANDS.DB.DatalistHands
import com.example.places.BTNSNEWMACHINE.LEGS.activity.Legs
import com.example.places.Main.MainActivity
import com.example.places.BTNSNEWMACHINE.HANDS.adapter.MyAdapterHands
import com.example.places.R
import com.example.places.PopupMenu.Bio
import com.example.places.Trainings.Trainings.activity.Trainings
import com.google.android.material.navigation.NavigationView

class Hands : AppCompatActivity() {

    lateinit var toogle: ActionBarDrawerToggle
    private lateinit var db: DBHalperHands
    lateinit var dbh: DBHalperHands
    private lateinit var newArrHands: ArrayList<DatalistHands>
    private lateinit var adapter: MyAdapterHands
    lateinit var recyclerViewHands: RecyclerView
    lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hands)

        //Find
        val drawerLayoutHands: DrawerLayout = findViewById(R.id.drawerLayoutHands)
        val navVoidHands: NavigationView = findViewById(R.id.navViewHands)
        recyclerViewHands = findViewById(R.id.recyclerViewHands)
        db = DBHalperHands(this)
        dbh = DBHalperHands(this)

        recyclerViewHands.layoutManager = LinearLayoutManager(this)
        recyclerViewHands.setHasFixedSize(true)
        dispayuser()

        //Drawer
        toogle = ActionBarDrawerToggle(this, drawerLayoutHands, R.string.open, R.string.close)
        drawerLayoutHands.addDrawerListener(toogle)
        toogle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navVoidHands.setNavigationItemSelectedListener {
            when(it.itemId)
            {
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
        newArrHands = ArrayList<DatalistHands>()
        while (newcursor!!.moveToNext()){
            val uname = newcursor.getString(0)
            newArrHands.add(DatalistHands(uname))
        }
        adapter = MyAdapterHands(newArrHands)
        recyclerViewHands.adapter = adapter
        adapter.OnItemClickListener(object: MyAdapterHands.onItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@Hands, EditHandsMachine::class.java)
                intent.putExtra("name", newArrHands[position].name)
                startActivity(intent)
                finish()
            }
        })
    }

    //Btn start Dialog
    fun btnAddMachoneToLegs(view: View){
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_new_machine)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    //Btn in Dialog
    fun btnAddLegsMachine(view: View){

        var name = dialog.findViewById<EditText>(R.id.editText)

        val names = name.text.toString().trim()
        val savedata = db.saveuserdata(names)
        if (TextUtils.isEmpty(names)){
            Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show()
            dialog.cancel()
        }
        else {
            if (savedata == true) {
                Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show()
                if (names != ""){
                    dispayuser()
                }
                dialog.cancel()
            } else {
                Toast.makeText(this, "Exist", Toast.LENGTH_SHORT).show()
                dialog.cancel()
            }
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toogle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}