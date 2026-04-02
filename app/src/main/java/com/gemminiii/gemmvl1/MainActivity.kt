package com.gemminiii.gemmvl1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gemminiii.library.Button.DefaultKvantMaterialButton.DefaultMaterialButton

class MainActivity : AppCompatActivity() {
    lateinit var testBtn: DefaultMaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        testBtn = findViewById(R.id.test_btn)
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