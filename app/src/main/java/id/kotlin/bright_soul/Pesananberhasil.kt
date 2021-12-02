package id.kotlin.bright_soul

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Pesananberhasil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pesananberhasil)
        val button: ImageButton = findViewById(R.id.buttonback)
        button.setOnClickListener {
            val intent = Intent(this,Pembayaran::class.java)
            startActivity(intent)
        }
    }
}