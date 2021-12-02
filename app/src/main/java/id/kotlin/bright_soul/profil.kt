package id.kotlin.bright_soul

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.auth.FirebaseAuth
import id.kotlin.bright_soul.databinding.ActivityNavigasiBinding
import id.kotlin.bright_soul.databinding.FragmentProfilBinding

class profil : Fragment() {

    private lateinit var binding: ActivityNavigasiBinding

    private lateinit var actionBar: ActionBar

    private lateinit var firebaseAuth: FirebaseAuth

    // TODO: Rename and change types of parameters


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigasiBinding.inflate(layoutInflater)


//        setContentView(binding.root)
//
//        actionBar = supportActionBar!!
//        actionBar.title = "Masuk"

        firebaseAuth = FirebaseAuth.getInstance()

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

        view.findViewById<Button>(R.id.keluar).setOnClickListener {
            firebaseAuth.signOut()
            val intent = Intent(activity, login::class.java)
            startActivity(intent)
            activity?.finish()
        }
        // Inflate the layout for this fragment
        return view
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