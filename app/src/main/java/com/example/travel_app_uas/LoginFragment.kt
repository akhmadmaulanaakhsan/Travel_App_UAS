package com.example.travel_app_uas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.travel_app_uas.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var prefManager: PrefManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefManager = PrefManager.getInstance(requireContext())

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Mohon isi semua data",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                if (isValidUsernamePassword(username, password)) {
                    prefManager.setLoggedIn(true)
                    checkLoginStatus()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Username atau password salah",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        binding.tvRegister.setOnClickListener {
            val tabLayoutActivity = requireActivity() as? TablayoutActivity
            tabLayoutActivity?.navigateToRegister()
        }
    }

    private fun isValidUsernamePassword(username: String, password: String): Boolean {
        val storedUsername = prefManager.getUsername()
        val storedPassword = prefManager.getPassword()
        return username == storedUsername && password == storedPassword
    }

    private fun checkLoginStatus() {
        val isLoggedIn = prefManager.isLoggedIn()
        //val navController = requireActivity().findNavController(R.id.nav_host_fragment_activity_bottom_navigation)
        if (isLoggedIn) {
            Toast.makeText(
                requireContext(), "Login berhasil",
                Toast.LENGTH_SHORT
            ).show()
            Log.d("LoginFragment", "Sebelum navigasi ke home fragment")

            //navController.navigate(R.id.navigation_home)
            val intent = Intent(requireContext(), BottomNavigation::class.java)
            intent.putExtra("openHomeFragment", true)
            startActivity(intent)




            Log.d("LoginFragment", "Setelah navigasi ke home fragment")
        } else {
            Toast.makeText(
                requireContext(), "Login gagal",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
