package com.example.places.Trainings.EditTraining

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.places.BTNSNEWMACHINE.BACK.DB.DBHalperBack
import com.example.places.BTNSNEWMACHINE.BOSOM.DB.DBHalperBosom
import com.example.places.BTNSNEWMACHINE.HANDS.DB.DBHalperHands
import com.example.places.BTNSNEWMACHINE.LEGS.DB.DBHalperLegs
import com.example.places.R
import com.example.places.Trainings.Trainings.DB.DBHalperTrainings
import com.example.places.Trainings.Trainings.activity.Trainings

class EditTraining : AppCompatActivity() {

    private lateinit var MachineInTrainingsRecyclerView: RecyclerView
    private lateinit var dbLegs: DBHalperLegs
    private lateinit var dbHands: DBHalperHands
    private lateinit var dbBack: DBHalperBack
    private lateinit var dbBosom: DBHalperBosom
    private lateinit var db: DBHalperTrainings
    private lateinit var adapter: MyAdapterAllMachines
    private lateinit var newArrTrainings: ArrayList<DatalistItemTrainer>
    private lateinit var names: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_training)

        dbLegs = DBHalperLegs(this)
        dbHands = DBHalperHands(this)
        dbBack = DBHalperBack(this)
        dbBosom = DBHalperBosom(this)

        db = DBHalperTrainings(this)

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
        MachineInTrainingsRecyclerView.adapter = adapter
    }
}