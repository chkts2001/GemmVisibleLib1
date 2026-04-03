package com.gemminiii.gemmvl1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gemminiii.library.Button.DefaultKvantMaterialButton.DefaultMaterialButton
import com.gemminiii.library.Button.DefaultKvantMaterialButton.builder.DefaultButtonBuilder

class MainActivity : AppCompatActivity() {
    lateinit var testBtn: DefaultMaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        testBtn = findViewById(R.id.test_btn)
        testBtn.sCornerRadius(5f)
        testBtn.sBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
        testBtn.sPaddings(6)
        testBtn.sIcon(
            com.gemminiii.library.R.drawable.ic_update_vector,
            30)
        testBtn.sText("тест реализации")
        testBtn.sTextSize(14)
        testBtn.sTextColor(android.R.color.white)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        testBtn.setOnClickListener {
            Toast.makeText(this, "кнопка работает", Toast.LENGTH_SHORT).show()
        }
    }

}