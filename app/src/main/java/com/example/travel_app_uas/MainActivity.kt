package com.example.travel_app_uas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import com.example.travel_app_uas.databinding.ActivityWelcomeBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister1.setOnClickListener {
            Log.d("MainActivity", "Get Started button clicked")
            startActivity(Intent(this, TablayoutActivity::class.java))
            //val homeFragment = HomeFragment()

            // Memulai transaksi fragment
            //supportFragmentManager.beginTransaction()
            //    .replace(R.id.fragment_home, homeFragment)
            //    .addToBackStack(null) // Opsional: Tambahkan ke back stack
            //    .commit()

        }
    }
}