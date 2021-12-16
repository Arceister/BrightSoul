package id.kotlin.bright_soul

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import id.kotlin.bright_soul.databinding.ActivityRegisterBinding
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class register : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private lateinit var actionBar: ActionBar

    private lateinit var progressDialog: ProgressDialog

    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var fStore: FirebaseFirestore
    private var email = ""
    private var katasandi = ""
    private var namaBinding = ""
    private var tlBinding = ""
    private var jkBinding = ""
    private var pekerjaanBinding = ""

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
        fStore = FirebaseFirestore.getInstance()

        binding.daftar.setOnClickListener {
            validateData()
        }

        val jenisKelaminSpinner: Spinner = findViewById(R.id.spinnerJenisKelamin)
        ArrayAdapter.createFromResource(
            this,
            R.array.dropDownJenisKelamin,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            jenisKelaminSpinner.adapter = adapter
        }
    }

    private fun inputUserData(userId: String, email: String, nama: String, tanggalLahir: String, jenisKelamin: String, pekerjaan: String) {
        val userData = hashMapOf(
            "userId" to userId,
            "email" to email,
            "nama" to nama,
            "tanggal_lahir" to tanggalLahir,
            "jk" to jenisKelamin,
            "pekerjaan" to pekerjaan
        )

        fStore.collection("users").document(userId)
            .set(userData)
            .addOnSuccessListener {
                Log.d("Sukses", "Pendaftaran Sukses")
            }
            .addOnFailureListener { e ->
                Log.w("Gagal", "Error", e)
            }
    }

    private fun validateData() {
        email = binding.email.text.toString().trim()
        katasandi = binding.katasandi.text.toString().trim()
        namaBinding = binding.namaRegister.text.toString().trim()
        tlBinding = binding.tanggalLahirRegister.text.toString().trim()
        jkBinding = binding.spinnerJenisKelamin.selectedItem.toString().trim()
        pekerjaanBinding = binding.pekerjaanRegister.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.email.error= "Format email salah"
        }else if (TextUtils.isEmpty(katasandi)){
            binding.katasandi.error = "Masukkan kata sandi!"
        }else if (katasandi.length < 6){
            binding.katasandi.error = "kata sandi minimal 6 karakter "
        }else if (TextUtils.isEmpty(namaBinding)){
            binding.namaRegister.error = "Masukkan Nama!"
        }else if (TextUtils.isEmpty(tlBinding)){
            binding.tanggalLahirRegister.error = "Masukkan Tanggal Lahir!"
        }else if (TextUtils.isEmpty(pekerjaanBinding)){
            binding.pekerjaanRegister.error = "Masukkan Pekerjaan"
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
                val userId = firebaseUser!!.uid
                namaBinding = binding.namaRegister.text.toString().trim()
                tlBinding = binding.tanggalLahirRegister.text.toString().trim()
                jkBinding = binding.spinnerJenisKelamin.selectedItem.toString().trim()
                pekerjaanBinding = binding.pekerjaanRegister.text.toString().trim()
                if (email != null) {
                    inputUserData(userId, email, namaBinding, tlBinding, jkBinding, pekerjaanBinding)
                }
                Toast.makeText(this,"Pendaftaram berhasil dengan menggunalan  $email dengan User Id $userId", Toast.LENGTH_SHORT).show()
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
}