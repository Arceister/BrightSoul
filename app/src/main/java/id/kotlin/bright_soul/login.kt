package id.kotlin.bright_soul

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import id.kotlin.bright_soul.databinding.ActivityLoginBinding

class login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var actionBar: ActionBar

    private lateinit var progressDialog: ProgressDialog

    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var emailPengguna: EditText

    private val EMAIL_KEY = ""
    private val PREF_NAME = "Pref"
    private lateinit var sharedPreference: SharedPreferences

    private var email = ""
    private var katasandi = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar = supportActionBar!!
        actionBar.title = "Masuk"

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Mohon tunggu")
        progressDialog.setMessage("Masuk..")
        progressDialog.setCanceledOnTouchOutside(false)

        sharedPreference = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val emailPenggunaRemind = sharedPreference.getString(EMAIL_KEY, "0")
        binding.email.setText(emailPenggunaRemind)

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        binding.tidakPunyaAkun.setOnClickListener {
            startActivity(Intent(this,register::class.java))
        }

        binding.login.setOnClickListener{
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
        }else{
            firebaseLogin()
        }

    }

    private fun firebaseLogin(){
        val editor: SharedPreferences.Editor = sharedPreference.edit()
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email, katasandi)
            .addOnSuccessListener {
                editor.putString(EMAIL_KEY, email)
                editor.apply()
                progressDialog.dismiss()
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                val userId = firebaseUser!!.uid
                Toast.makeText(this,"masuk sebagai $email dan user id $userId", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,navigasi::class.java))
                finish()
            }
            .addOnFailureListener{ e->
                progressDialog.dismiss()
                Toast.makeText(this,"Gagal untuk masuk pada ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null){
            startActivity(Intent(this,navigasi::class.java))
            finish()
        }
    }

}