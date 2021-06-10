package com.joeljebitto.ageinminutesapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.joeljebitto.ageinminutesapp.R
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btnDatepicker).setOnClickListener { view ->
            clickDatePicker(view)
        }
    }

    private fun clickDatePicker(view: View) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year_S, month_S, date_S ->

                val tvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)
                val tvSelectedDateInMinutes = findViewById<TextView>(R.id.tvSelectedDateInMinutes)
                val selectedDate = "$date_S/$month_S/$year_S"
                tvSelectedDate.text = selectedDate
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                val selectedDateInMinutes = theDate!!.time / 60000
                val currentDate= sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateIoMinutes = currentDate!!.time / 60000
                val differenceInMinutes = currentDateIoMinutes - selectedDateInMinutes
                tvSelectedDateInMinutes.text = differenceInMinutes.toString()
            },
            year,
            month,
            day
        )

        dpd.datePicker.setMaxDate(Date().time)
        dpd.show()
    }

}