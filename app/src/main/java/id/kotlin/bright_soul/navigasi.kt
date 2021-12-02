package id.kotlin.bright_soul

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.widget.ImageButton
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import id.kotlin.bright_soul.databinding.ActivityNavigasiBinding
import id.kotlin.bright_soul.databinding.ActivityRegisterBinding

class navigasi : AppCompatActivity() {

    private lateinit var binding: ActivityNavigasiBinding

    private lateinit var actionBar: ActionBar

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar = supportActionBar!!
        actionBar.title = "Masuk"

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null){
            val fragHome = home()
            val fragProfil = profil()
            val fragNotes = list_mynotes()

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.framelayout, fragHome)
                commit()
            }

            findViewById<ImageButton>(R.id.homeBtn).setOnClickListener {

                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.framelayout, fragHome)
                    commit()
                }
            }

            findViewById<ImageButton>(R.id.profilBtn).setOnClickListener {

                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.framelayout, fragProfil)
                    commit()
                }
            }

            findViewById<ImageButton>(R.id.notesBtn).setOnClickListener {

                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.framelayout, fragNotes)
                    commit()
                }
            }
        } else{
            startActivity(Intent(this,login::class.java))
            finish()
        }
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_navigasi)
//        val fragHome = home()
//        val fragProfil = profil()
//        val fragNotes = list_mynotes()
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.framelayout, fragHome)
//            commit()
//        }
//
//        findViewById<ImageButton>(R.id.homeBtn).setOnClickListener {
//
//            supportFragmentManager.beginTransaction().apply {
//                replace(R.id.framelayout, fragHome)
//                commit()
//            }
//        }
//
//        findViewById<ImageButton>(R.id.profilBtn).setOnClickListener {
//
//            supportFragmentManager.beginTransaction().apply {
//                replace(R.id.framelayout, fragProfil)
//                commit()
//            }
//        }
//
//        findViewById<ImageButton>(R.id.notesBtn).setOnClickListener {
//
//            supportFragmentManager.beginTransaction().apply {
//                replace(R.id.framelayout, fragNotes)
//                commit()
//            }
//        }
//
//    }
}