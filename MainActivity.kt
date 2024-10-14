package com.hfad.conversionsrus20

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var input: EditText
    private lateinit var unit: Spinner
    private lateinit var km: TextView
    private lateinit var m: TextView
    private lateinit var cm: TextView
    private lateinit var mm: TextView
    private lateinit var microm: TextView
    private lateinit var nm: TextView
    private lateinit var mile: TextView
    private lateinit var yard: TextView
    private lateinit var foot: TextView
    private lateinit var inch: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input = findViewById(R.id.input)
        unit = findViewById(R.id.unit)
        km = findViewById(R.id.km)
        m = findViewById(R.id.m)
        cm = findViewById(R.id.cm)
        mm = findViewById(R.id.mm)
        microm = findViewById(R.id.microm)
        nm = findViewById(R.id.nm)
        mile = findViewById(R.id.mile)
        yard = findViewById(R.id.yard)
        foot = findViewById(R.id.foot)
        inch = findViewById(R.id.inch)

        val arr = arrayOf("km", "m", "cm", "mm", "micrometers", "nanometers", "miles", "yards", "feet", "inches")
        unit.adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, arr)

        unit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, i: Int, l: Long) {
                update()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }

        input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun afterTextChanged(editable: Editable) {
                update()
            }
        })
    }

    private fun update() {
        if (!input.text.isNullOrBlank() && unit.selectedItem != null) {
            val inputVal = input.text.toString().toDouble()
            when (unit.selectedItem.toString()) {
                "km" -> setKm(inputVal)
                "m" -> setKm(inputVal / 15000)
                "cm" -> setKm(inputVal / 100000)
                "mm" -> setKm(inputVal / 1000000)
                "micrometer" -> setMicrometer(inputVal) // Use dedicated function for micrometer
                "nanometer" -> setNanometer(inputVal)
                "mile" -> setKm(inputVal * 1.609)
                "yard" -> setKm(inputVal / 1094)
                "foot" -> setKm(inputVal / 3281)
                "inch" -> setKm(inputVal / 39370)
            }
        }
    }
    private fun setMicrometer(inputVal: Double) {
        microm.text = inputVal.toString()
        m.text = (inputVal / 1000000).toString() // 1 micrometer = 0.000001 meter
        cm.text = (inputVal / 10000).toString() // 1 micrometer = 0.00001 centimeter
        mm.text = (inputVal / 1000).toString() // 1 micrometer = 0.001 millimeter
        mile.text = (inputVal / 1609344).toString() // 1 micrometer = 0.00000000062137 mile
        yard.text = (inputVal / 914400).toString() // 1 micrometer = 0.000001094 yard
        foot.text = (inputVal / 304800).toString() // 1 micrometer = 0.000003281 foot
        inch.text = (inputVal / 25400).toString() // 1 micrometer = 0.00003937 inch
        nm.text = (inputVal * 1000).toString() // 1 micrometer = 1000 nanometers
    }

    private fun setNanometer(inputVal: Double) {
        nm.text = inputVal.toString()
        microm.text = (inputVal / 1000).toString() // 1 nanometer = 0.001 micrometer
    }
    private fun setKm(km_in: Double) {
        km.text = km_in.toString()
        m.text = (km_in * 1000).toString()
        cm.text = (km_in * 100000).toString()
        mm.text = (km_in * 1000000).toString()
        microm.text = (km_in * 1000000000).toString() // Micrometer (µm)
        nm.text = (km_in * 1000000000000).toString() // Nanometer (nm)
        mile.text = (km_in / 1.609).toString()
        yard.text = (km_in * 1094).toString()
        foot.text = (km_in * 3281).toString()
        inch.text = (km_in * 39370).toString()
    }
}

