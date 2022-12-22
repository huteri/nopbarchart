package me.huteri.nopbarchart

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.huteri.nopbarchart.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.barChart.setData(1.2, listOf(BarItem(1.2, Color.parseColor("#97C941")), BarItem(1.0, Color.parseColor("#F2B130")), BarItem(1.0, Color.parseColor("#EB2726"))))
    }
}