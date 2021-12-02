package id.kotlin.bright_soul

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import id.kotlin.bright_soul.databinding.ActivityRegisterBinding

class register : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private lateinit var actionBar: ActionBar

    private lateinit var progressDialog: ProgressDialog

    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var katasandi = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar = supportActionBar!!
        actionBar.title = "Daftar"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Mohon tunggu")
        progressDialog.setMessage("Daftar..")
        progressDialog.setCanceledOnTouchOutside(false)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.daftar.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        email = binding.email.text.toString().trim()
        katasandi = binding.katasandi.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.email.error= "Format email salah"
        }else if (TextUtils.isEmpty(katasandi)){
            binding.katasandi.error = "Masukkan katasandi"
        }else if (katasandi.length < 6){
            binding.katasandi.error = "katasandi minimal 6 karakter "
        }else{
            firebasedaftar()
        }
    }

    private fun firebasedaftar() {
        progressDialog.show()

        firebaseAuth.createUserWithEmailAndPassword(email, katasandi)
            .addOnSuccessListener {
                progressDialog.dismiss()
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this,"Pendaftaram berhasil dengan menggunalan  $email", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,navigasi::class.java))
                finish()
            }
            .addOnFailureListener{ e->
                progressDialog.dismiss()
                Toast.makeText(this,"Daftar gagal pada ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_register)
//    }
}