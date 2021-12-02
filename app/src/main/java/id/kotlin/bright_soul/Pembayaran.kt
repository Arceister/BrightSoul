package id.kotlin.bright_soul

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Pembayaran : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pembayaran)
        val button: ImageButton = findViewById(R.id.buttonlanjut2)
        button.setOnClickListener {
            val intent = Intent(this,Pesananberhasil::class.java)
            startActivity(intent)
        }
        val buttonback:ImageButton = findViewById(R.id.buttonback)
        buttonback.setOnClickListener {
            val intent = Intent(this,formdr::class.java)
            startActivity(intent)
        }
        val buttonchange:ImageButton = findViewById(R.id.buttonchange2)
        buttonchange.setOnClickListener {
            val intent = Intent(this,Pilihmetode::class.java)
            startActivity(intent)
        }
    }
}