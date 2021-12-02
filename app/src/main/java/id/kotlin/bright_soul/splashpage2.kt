package id.kotlin.bright_soul

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.ActionBar

class splashpage2 : AppCompatActivity() {
    private lateinit var actionBar: ActionBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashpage2)

        val nextButton = findViewById<ImageButton>(R.id.btn1)
        nextButton.setOnClickListener{
            val Intent = Intent(this,splashpage3::class.java)
            startActivity(Intent)
        }

        actionBar = supportActionBar!!
        actionBar.hide()
    }
}