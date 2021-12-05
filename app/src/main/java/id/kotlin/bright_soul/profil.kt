package id.kotlin.bright_soul

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import id.kotlin.bright_soul.databinding.ActivityNavigasiBinding
import id.kotlin.bright_soul.databinding.FragmentProfilBinding

class profil : Fragment() {

    private lateinit var binding: ActivityNavigasiBinding

    private lateinit var actionBar: ActionBar

    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var fStore: FirebaseFirestore

    // TODO: Rename and change types of parameters


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigasiBinding.inflate(layoutInflater)


//        setContentView(binding.root)
//
//        actionBar = supportActionBar!!
//        actionBar.title = "Masuk"

        firebaseAuth = FirebaseAuth.getInstance()
        fStore = FirebaseFirestore.getInstance()

//        binding.keluar.setOnClickListener{
//            firebaseAuth.signOut()
//        }
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_profil, container, false)

        val user = firebaseAuth!!.currentUser

        val document = user?.let { fStore.collection("users").document(it.uid) }
        var nama : String
        var tanggal_lahir : String
        var jk : String
        var pekerjaan : String
        if (document != null) {
            document.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        nama = document.getString("nama")!!
                        tanggal_lahir = document.getString("tanggal_lahir")!!
                        jk = document.getString("jk")!!
                        pekerjaan = document.getString("pekerjaan")!!
                        view.findViewById<TextView>(R.id.nama).text = nama
                        view.findViewById<TextView>(R.id.tglLahir).text = tanggal_lahir
                        view.findViewById<TextView>(R.id.jenisKelamin).text = jk
                        view.findViewById<TextView>(R.id.pekerjaan).text = pekerjaan
                    } else {
                        Log.d("Gagal", "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("Gagal2", "get failed with ", exception)
                }
        }

        view.findViewById<Button>(R.id.keluar).setOnClickListener {
            firebaseAuth.signOut()
            val intent = Intent(activity, login::class.java)
            startActivity(intent)
            activity?.finish()
        }
        // Inflate the layout for this fragment
        return view
    }

    fun readData() {
        fStore = FirebaseFirestore.getInstance()

    }
}

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//
//        return inflater.inflate(R.layout.fragment_profil, container, false)
//    }
//}