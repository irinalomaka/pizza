package com.nennos.pizza.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nennos.pizza.R
import kotlinx.android.synthetic.main.ac_thanks.*
import android.content.Intent


class ThanksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_thanks)
        backToHomeBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }
}