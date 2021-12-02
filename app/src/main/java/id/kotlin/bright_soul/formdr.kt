package id.kotlin.bright_soul

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class formdr : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.formdr)
        val button = findViewById<ImageButton>(R.id.buttonlanjut)
        button.setOnClickListener{
            val Intent = Intent(this,formdr::class.java)
            startActivity(Intent)
        }
    }
}