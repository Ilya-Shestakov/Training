package com.example.places.BTNSNEWMACHINE.LEGS.activity

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
import com.example.places.BTNSNEWMACHINE.HANDS.activity.Hands
import com.example.places.BTNSNEWMACHINE.LEGS.DB.DBHalperLegs
import com.example.places.BTNSNEWMACHINE.LEGS.DB.DatalistLegs
import com.example.places.BTNSNEWMACHINE.LEGS.adapters.MyAdapterLegs
import com.example.places.BTNSNEWMACHINE.LEGS.dialogs.EditLegsMachine
import com.example.places.MainActivity
import com.example.places.R
import com.google.android.material.navigation.NavigationView

class Legs : AppCompatActivity() {

    lateinit var toogle: ActionBarDrawerToggle
    private lateinit var db: DBHalperLegs
    lateinit var dbh: DBHalperLegs
    private lateinit var newArry: ArrayList<DatalistLegs>
    private lateinit var adapter: MyAdapterLegs
    lateinit var recyclerView: RecyclerView
    lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_legs)

        //Find
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val navVoid: NavigationView = findViewById(R.id.navView)
        recyclerView = findViewById(R.id.recyclerView)
        db = DBHalperLegs(this)
        dbh = DBHalperLegs(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        dispayuser()

        //Drawer
            toogle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
            drawerLayout.addDrawerListener(toogle)
            toogle.syncState()

            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            navVoid.setNavigationItemSelectedListener {
                when(it.itemId)
                {
                    R.id.nav_bio -> Toast.makeText(applicationContext, "Tap to ", Toast.LENGTH_SHORT).show()
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

    fun btnToBack(view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun MethodDispBack() {
        val intent = Intent(this, Back::class.java)
        startActivity(intent)
        finish()
    }

    private fun MethodDispMenu() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun MethodDispHands() {
        val intent = Intent(this, Hands::class.java)
        startActivity(intent)
        finish()
    }

    private fun dispayuser() {
        val newcursor: Cursor? = dbh!!.gettext()
        newArry = ArrayList<DatalistLegs>()
        while (newcursor!!.moveToNext()){
            val uname = newcursor.getString(0)
            newArry.add(DatalistLegs(uname))
        }
        adapter = MyAdapterLegs(newArry)
        recyclerView.adapter = adapter
        adapter.OnItemClickListener(object: MyAdapterLegs.onItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@Legs, EditLegsMachine::class.java)
                intent.putExtra("name", newArry[position].name)
                startActivity(intent)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        finishAffinity()
    }

    //Btn start Dialog
    fun btnAddMachoneToLegs(view: View){
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_new_machine_legs)
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