package id.kotlin.bright_soul

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Pilihmetode : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pilihmetode)
        val buttonback: ImageButton = findViewById(R.id.buttonback)
        buttonback.setOnClickListener {
            val intent = Intent(this,Pembayaran::class.java)
            startActivity(intent)
        }
    }
}