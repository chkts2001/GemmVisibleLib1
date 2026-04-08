package com.gemminiii.gemmvl1

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gemminiii.library.Button.DefaultKvantMaterialButton.DefaultMaterialButton
import com.gemminiii.library.Button.DefaultKvantMaterialButton.builder.DefaultButtonBuilder
import com.gemminiii.library.Button.DefaultKvantMaterialButton.core.ButtonIcon
import com.gemminiii.library.Button.DefaultKvantMaterialButton.factory.ButtonFactory

class MainActivity : AppCompatActivity() {
    lateinit var testBtn: DefaultMaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val container = findViewById<LinearLayout>(R.id.main)

        ViewCompat.setOnApplyWindowInsetsListener(container) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }
//
//        val button = DefaultButtonBuilder(this)
//            .sWidth(LinearLayout.LayoutParams.MATCH_PARENT)
//            .sHeight(60)
//            .sBackgroundColor(android.R.color.transparent)
//            .sText("test click", 20, android.R.color.holo_blue_dark, Typeface.DEFAULT_BOLD)
//            .sCornerRadius(15f)
//            .sStroke(15, android.R.color.holo_blue_dark)
//            .sIcon(com.gemminiii.library.R.drawable.ic_update_vector, 35, android.R.color.holo_blue_dark)
//            .sIconGravity(ButtonIcon.IconPosition.START)
//            .build()
//        container.addView(button)

        testBtn = findViewById(R.id.test_btn)
        val factoryBuilder = ButtonFactory.getInstance().blackDefaultButton(this, null, com.gemminiii.library.R.drawable.ic_update_vector)
        val factoryConfig = factoryBuilder.getCurrentConfig()
        //factoryBuilder.applyTo(testBtn)
        val btn = factoryBuilder.build()
        container.addView(btn)
        Log.d("FactoryDebug", "------------------------------------------------------------")
        DefaultButtonBuilder(this)
            .setCurrentConfig(factoryConfig)
            .sIcon(null)
            .sText("text")
            .sBackgroundColor(android.R.color.holo_blue_dark)
            .applyTo(testBtn)
//        DefaultButtonBuilder(this)
//            .sWidth(300)
//            .sHeight(300)
//            .sBackgroundColor(android.R.color.transparent)
////            .sText("test click", 20, android.R.color.holo_blue_dark, Typeface.DEFAULT_BOLD)
////            .sCornerRadius(15f)
//            .sStroke(3, android.R.color.holo_blue_dark)
//            .applyTo(testBtn)
//            .sIcon(com.gemminiii.library.R.drawable.ic_update_vector, 35, android.R.color.holo_blue_dark)
//            .sIconGravity(ButtonIcon.IconPosition.START)
//        testBtn.updateConfig {
//            width = 150
//            height = 150
//            backgroundColor = android.R.color.transparent
//            strokeWidth = 3
//            strokeColor = android.R.color.holo_red_dark
//        }

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