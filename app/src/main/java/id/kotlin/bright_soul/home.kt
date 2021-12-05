package id.kotlin.bright_soul

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class home : Fragment() {
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var fStore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        fStore = FirebaseFirestore.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false)
        val v = inflater.inflate(R.layout.fragment_home, container, false)

        val user = firebaseAuth!!.currentUser

        val document = user?.let { fStore.collection("users").document(it.uid) }

        if (document != null) {
            document.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        v.findViewById<TextView>(R.id.txtNamaDepan).text = document.getString("nama")!!.split(" ")[0]
                    } else {
                        Log.d("Gagal", "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("Gagal2", "get failed with ", exception)
                }
        }

        val konsul = v.findViewById<ImageButton>(R.id.consulBtn)
        konsul.setOnClickListener{
            val consulFragment = konsultasi()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.framelayout, consulFragment)
            transaction.commit()
        }

        val emercall = v.findViewById<ImageButton>(R.id.emercallBtn)
        emercall.setOnClickListener{
            val emercallFrag = emercall()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.framelayout, emercallFrag)
            transaction.commit()
        }

        return v
    }



}