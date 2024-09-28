package com.bell.countryapp

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.text.format.DateFormat
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.DialogFragment
import com.bell.countryapp.databinding.DialogExitBinding
import com.bell.countryapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),
    DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var provinces: Array<String>
    private val countries = arrayOf(
        "Indonesia",
        "United States",
        "United Kingdom",
        "Germany",
        "France",
        "Australia",
        "Japan",
        "China",
        "Brazil",
        "Canada"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        provinces = resources.getStringArray(R.array.provinces)

        with(binding) {
            val adapterCountries =
                ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, countries)
            adapterCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            spinnerCountry.adapter = adapterCountries

            val adapterProvinces =
                ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, provinces)
            adapterProvinces.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerProvinces.adapter = adapterProvinces

            spinnerCountry.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                        Toast.makeText(
                            this@MainActivity,
                            countries[position], Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }
                }
            datePicker.init(
                datePicker.year,
                datePicker.month,
                datePicker.dayOfMonth
            )
            { _, year, monthOfYear, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
                Toast.makeText(this@MainActivity, selectedDate, Toast.LENGTH_SHORT).show()
            }
            timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
                val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                Toast.makeText(this@MainActivity, selectedTime, Toast.LENGTH_SHORT).show()
            }
            btnShowCalender.setOnClickListener {
                val datePicker = DatePicker()
                datePicker.show(supportFragmentManager, "date picker")
            }
            btnShowAlertDialog.setOnClickListener {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("Keluar")
                builder.setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
                builder.setPositiveButton("Ya") { dialog, which ->
                    finish()
                }
                builder.setNegativeButton("Tidak") { dialog, which ->
                    dialog.dismiss()
                }
                val dialog = builder.create()
                dialog.show()
            }
            btnShowTimePicker.setOnClickListener {
                val timePicker = TimePicker()
                timePicker.show(supportFragmentManager, "time picker")
            }
        }

        fun onDateSet(view: android.widget.DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
            val selectedDate = "$dayOfMonth/${month + 1}/$year"
            Toast.makeText(this@MainActivity, selectedDate, Toast.LENGTH_SHORT).show()
        }

        fun onTimeSet(view: android.widget.TimePicker?, hourOfDay: Int, minute: Int) {
            val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
            Toast.makeText(this@MainActivity, selectedTime, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDateSet(
        p0: android.widget.DatePicker?,
        p1: Int,
        p2: Int,
        p3: Int
    ) {
        TODO("Not yet implemented")
    }

    override fun onTimeSet(p0: android.widget.TimePicker?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    class DatePicker : DialogFragment() {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val monthOfYear = calendar.get(Calendar.MONTH)
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
            return DatePickerDialog(
                requireActivity(),
                activity as DatePickerDialog.OnDateSetListener,
                year,
                monthOfYear,
                dayOfMonth
            )
        }
    }

    class TimePicker : DialogFragment() {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val calendar = Calendar.getInstance()
            val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            return TimePickerDialog(
                requireActivity(),
                activity as TimePickerDialog.OnTimeSetListener,
                hourOfDay,
                minute,
                DateFormat.is24HourFormat(requireActivity())

            )
        }
    }

    class DialogExit : DialogFragment() {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val builder = AlertDialog.Builder(requireActivity())
            val inflater = requireActivity().layoutInflater
            val binding = DialogExitBinding.inflate(inflater)
            with(binding) {
                btnYes.setOnClickListener {
                    requireActivity().finish()
                }
                btnNo.setOnClickListener {
                    dismiss()
                }
            }
            builder.setView(binding.root)
            return builder.create()
        }
    }
}





