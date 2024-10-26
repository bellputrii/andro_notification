package com.bell.logintabs

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment() {

    private lateinit var inputEmail: TextInputEditText
    private lateinit var inputPassword: TextInputEditText
    private lateinit var btnLogin: Button
    private lateinit var checkbox: CheckBox
    private lateinit var registerText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        inputEmail = view.findViewById<TextInputLayout>(R.id.input_username_login).editText as TextInputEditText
        inputPassword = view.findViewById<TextInputLayout>(R.id.input_password).editText as TextInputEditText
        btnLogin = view.findViewById(R.id.btn_login_button)
        checkbox = view.findViewById(R.id.checkbox)
        registerText = view.findViewById(R.id.register_text)

        btnLogin.setOnClickListener {
            performLogin()
        }

        registerText.setOnClickListener {
            // Arahkan ke HomeActivity saat registrasi diklik
            val username = inputEmail.text.toString() // Gunakan email sebagai username
            navigateToHome(username)
        }

        return view
    }

    private fun performLogin() {
        val email = inputEmail.text.toString()
        val password = inputPassword.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            // Tampilkan pesan kesalahan
            return
        }

        // Arahkan ke HomeActivity dengan username
        navigateToHome(email)
    }

    private fun navigateToHome(username: String) {
        val intent = Intent(requireActivity(), HomeActivity::class.java)
        intent.putExtra("USERNAME", username) // Kirim username ke HomeActivity
        startActivity(intent)
        requireActivity().finish() // Tutup LoginActivity jika diperlukan
    }
}
