package com.example.places.PopupMenu

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import com.example.places.MainActivity
import com.example.places.R

class Bio : AppCompatActivity() {

    lateinit var valueAge: TextView
    lateinit var valueWeight: TextView
    lateinit var valueDate: EditText
    private lateinit var slider: SeekBar
    private lateinit var sliderWeight: SeekBar
    private var pref: SharedPreferences? = null
    private var valueAgeInt: Int = 1
    private var valueWeightInt: Int = 1
    private var valueDateStr: String = "00:00:00"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bio)

        valueAge = findViewById(R.id.Age)
        valueWeight = findViewById(R.id.Weight)
        valueDate = findViewById(R.id.Date)
        slider = findViewById(R.id.seekBar)
        sliderWeight= findViewById(R.id.seekBarWeight)

        pref = getSharedPreferences("TABLE", MODE_PRIVATE)

        valueAgeInt = pref?.getInt("Age", 1)!!
        valueWeightInt = pref?.getInt("Weight", 1)!!
        valueDateStr = pref?.getString("Date", "00:00:00").toString()
        valueAge.text = valueAgeInt.toString()
        valueWeight.text = valueWeightInt.toString()
        valueDate.setText(valueDateStr)


        slider.min = 10
        slider.max = 100

        slider.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                valueAge.text = p1.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })


        sliderWeight.min = 30
        sliderWeight.max = 200

        sliderWeight.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                valueWeight.text = p1.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })
    }

    fun savedata(p0: Int, p1: Int, p2: String){
        val editor = pref?.edit()
        editor?.putInt("Age", p0)
        editor?.putInt("Weight", p1)
        editor?.putString("Date", p2)
        editor?.apply()
    }

    fun btnSave_Bio(view: View){
        valueAge = findViewById(R.id.Age)
        valueWeight = findViewById(R.id.Weight)
        valueDate = findViewById(R.id.Date)
        savedata(valueAge.text.toString().toInt(), valueWeight.text.toString().toInt(), valueDate.text.toString())
        Toast.makeText(this, "Completed! Data save!", Toast.LENGTH_SHORT).show()
    }

    fun btnToBack_Bio(view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}