package com.example.travel_app_uas

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.travel_app_uas.TablayoutActivity
import com.example.travel_app_uas.databinding.FragmentRegisterBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var prefManager: PrefManager
    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefManager = PrefManager.getInstance(requireContext())

        binding.etdate.setOnClickListener {
            showDatePickerDialog()
        }

        binding.btnRegister.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            val dateOfBirth = binding.etdate.text.toString()
            val email = binding.etEmail.text.toString()
            val confirmPassword = binding.etPasswordConfirm.text.toString()
            val checkBoxAgreement = binding.checkBox.isChecked

            if (username.isEmpty() || password.isEmpty() || dateOfBirth.isEmpty() || email.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Mohon isi semua data",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (password != confirmPassword) {
                Toast.makeText(
                    requireContext(),
                    "Password tidak sama",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (!checkBoxAgreement) {
                Toast.makeText(
                    requireContext(),
                    "Anda harus menyetujui persyaratan sebelum mendaftar",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            } else {
                prefManager.saveUsername(username)
                prefManager.savePassword(password)
                prefManager.setLoggedIn(true)
                if (isValidDateOfBirth(dateOfBirth)) {
                    checkLoginStatus()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Anda harus berusia minimal 15 tahun",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        binding.tvLogin.setOnClickListener {
            val tabLayoutActivity = requireActivity() as? TablayoutActivity
            tabLayoutActivity?.navigateToLogin()
        }
    }

    private fun checkLoginStatus() {
        val isLoggedIn = prefManager.isLoggedIn()
        if (isLoggedIn) {
            Toast.makeText(
                requireContext(), "Registrasi berhasil",
                Toast.LENGTH_SHORT
            ).show()
            val tabLayoutActivity = requireActivity() as? TablayoutActivity
            tabLayoutActivity?.navigateToLogin()
        } else {
            Toast.makeText(
                requireContext(), "Registrasi gagal",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun showDatePickerDialog() {
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }

        DatePickerDialog(
            requireContext(),
            dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun updateDateInView() {
        val myFormat = "dd-MM-yyyy" // or your desired format
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        binding.etdate.setText(sdf.format(calendar.time))
    }

    private fun isValidDateOfBirth(dateOfBirth: String): Boolean {
        try {
            val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            val birthDate = sdf.parse(dateOfBirth)
            val currentDate = Date()

            val diffInMillis = currentDate.time - birthDate.time
            val age = diffInMillis / (1000L * 60 * 60 * 24 * 365)

            return age >= 15
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }
}
