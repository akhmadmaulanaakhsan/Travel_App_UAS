package com.example.travel_app_uas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.travel_app_uas.LoginFragment
import com.example.travel_app_uas.PrefManager
import com.example.travel_app_uas.R
import com.example.travel_app_uas.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var prefManager: PrefManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefManager = PrefManager.getInstance(requireContext())
        checkLoginStatus()

        with(binding) {
            txtUsername.text = prefManager.getUsername()
            btnLogout.setOnClickListener {
                prefManager.setLoggedIn(false)
                navigateToLoginFragment()
            }
            btnClear.setOnClickListener {
                prefManager.clear()
                requireActivity().finish()
            }
        }
    }

    private fun checkLoginStatus() {
        val isLoggedIn = prefManager.isLoggedIn()
        if (!isLoggedIn) {
            navigateToLoginFragment()
        }
    }

    private fun navigateToLoginFragment() {
        val loginFragment = LoginFragment()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()

        // Gantilah R.id.fragment_container dengan ID yang sesuai dengan wadah fragment di layout Anda
        transaction.replace(R.id.fragment_login, loginFragment)

        // Tambahkan fragment ini ke back stack, sehingga jika diperlukan, pengguna dapat kembali ke fragment sebelumnya
        transaction.addToBackStack(null)

        transaction.commit()

        // Akhiri aktivitas saat ini
        requireActivity().finish()

    }
}