package com.bell.intentapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bell.intentapp.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding

    companion object {
        const val EXTRA_ADDRESS = "extra_address"
        const val EXTRA_NAME = "extra_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(EXTRA_NAME)
        with(binding) {
            btnToThirdActivity.setOnClickListener {
                val resultIntent = Intent().apply {
                    putExtra(EXTRA_ADDRESS, editAddress.text.toString())
                    putExtra(EXTRA_NAME, name)
                }
                // Menetapkan result code dan data Intent
                setResult(Activity.RESULT_OK, resultIntent)
                // Menyelesaikan aktivitas
                finish()
            }
        }
    }
}
