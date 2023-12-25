package com.example.travel_app_uas


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.travel_app_uas.databinding.ActivityMainBinding
import com.example.travel_app_uas.databinding.ActivityRegisterloginBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TablayoutActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "MyTabLayout"
        //supportActionBar?.hide()
        var binding = ActivityRegisterloginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager = binding.viewPager
        tabLayout = binding.tabLayout

        with(binding) {
            viewPager.adapter = TabAdapter(this@TablayoutActivity)

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = when (position) {
                    0 -> "Register"
                    1 -> "Login"
                    else -> ""
                }
            }.attach()
        }
    }

    // Fungsi ini dipanggil dari RegisterFragment setelah pendaftaran berhasil
    fun navigateToLogin() {
        viewPager.currentItem = 1 // Pindah ke halaman Login
    }

    fun navigateToRegister() {
        viewPager.currentItem = 0 // Pindah ke halaman Register
    }

}