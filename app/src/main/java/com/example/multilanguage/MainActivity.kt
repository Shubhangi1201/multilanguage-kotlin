package com.example.multilanguage

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.Locale

class MainActivity : AppCompatActivity(), myapp.LanguageChangeListener {
    private lateinit var tv: TextView
    private lateinit var can: TextView
    private lateinit var candle: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv = findViewById<TextView>(R.id.hellotv)
        can = findViewById<TextView>(R.id.hellotdfsddv)
        candle = findViewById<TextView>(R.id.hellotvef)
        val hindi = findViewById<Button>(R.id.tohindiBtn)
        val english = findViewById<Button>(R.id.toenglishBtn)

        myapp.AppLanguageDelegate.addObserver(this)

        // Button click listener
        hindi.setOnClickListener {
            // Change language to a new language code (e.g., "hi" for Hindi)
            myapp.AppLanguageDelegate.setLocale(this, "hi")
        }

    }

    override fun onLanguageChange() {
       tv.text = getString(R.string.hello)
        can.text = getString(R.string.hello)
        candle.text = getString(R.string.hello)

    }


}