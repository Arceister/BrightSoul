package id.kotlin.bright_soul

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.ActionBar

class MainActivity : AppCompatActivity() {

    private lateinit var actionBar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nextButton = findViewById<ImageButton>(R.id.btn1)
        nextButton.setOnClickListener{
            val Intent = Intent(this,splashpage2::class.java)
            startActivity(Intent)
        }

        actionBar = supportActionBar!!
        actionBar.hide()
    }
}