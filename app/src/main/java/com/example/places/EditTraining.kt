package com.example.places

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.places.BTNSNEWMACHINE.BACK.DB.DBHalperBack
import com.example.places.BTNSNEWMACHINE.BACK.DB.DatalistBack
import com.example.places.BTNSNEWMACHINE.BACK.adapter.MyAdapterBack
import com.example.places.BTNSNEWMACHINE.BOSOM.DB.DBHalperBosom
import com.example.places.BTNSNEWMACHINE.BOSOM.activity.Bosom
import com.example.places.BTNSNEWMACHINE.HANDS.DB.DBHalperHands
import com.example.places.BTNSNEWMACHINE.LEGS.DB.DBHalperLegs

class EditTraining : AppCompatActivity() {

    lateinit var MachineInTrainingsRecyclerView: RecyclerView
    lateinit var dbLegs: DBHalperLegs
    lateinit var dbHands: DBHalperHands
    lateinit var dbBack: DBHalperBack
    lateinit var dbBosom: DBHalperBosom
    private lateinit var adapter: MyAdapterAllMachines

    private lateinit var newArrTrainings: ArrayList<DatalistTrainings>

    override fun onDestroy() {
        super.onDestroy()
        val intent = Intent(this, Trainings::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_training)

        dbLegs = DBHalperLegs(this)
        dbHands = DBHalperHands(this)
        dbBack = DBHalperBack(this)
        dbBosom = DBHalperBosom(this)

        MachineInTrainingsRecyclerView = findViewById(R.id.MachineInTrainings)

        MachineInTrainingsRecyclerView.layoutManager = LinearLayoutManager(this)
        MachineInTrainingsRecyclerView.setHasFixedSize(true)
        dispayuserAll()

    }

    private fun dispayuserAll() {
        val newcursorLegs: Cursor? = dbLegs!!.gettext()
        val newcursorHands: Cursor? = dbHands!!.gettext()
        val newcursorBack: Cursor? = dbBack!!.gettext()
        val newcursorBosom: Cursor? = dbBosom!!.gettext()
        newArrTrainings = ArrayList<DatalistTrainings>()
        while (newcursorLegs!!.moveToNext()){
            val unameLegs = newcursorLegs.getString(0)
            newArrTrainings.add(DatalistTrainings(unameLegs))
        }
        while (newcursorHands!!.moveToNext()){
            val unameHands = newcursorHands.getString(0)
            newArrTrainings.add(DatalistTrainings(unameHands))
        }
        while (newcursorBack!!.moveToNext()){
            val unameBack = newcursorBack.getString(0)
            newArrTrainings.add(DatalistTrainings(unameBack))
        }
        while (newcursorBosom!!.moveToNext()){
            val unameBosom = newcursorBosom!!.getString(0)
            newArrTrainings.add(DatalistTrainings(unameBosom))
        }
        adapter = MyAdapterAllMachines(newArrTrainings)
        MachineInTrainingsRecyclerView.adapter = adapter
    }
}