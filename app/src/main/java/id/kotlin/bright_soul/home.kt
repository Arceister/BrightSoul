package id.kotlin.bright_soul

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class home : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false)
        val v = inflater.inflate(R.layout.fragment_home, container, false)

        val konsul = v.findViewById<ImageButton>(R.id.consulBtn)
        konsul.setOnClickListener{
            val consulFragment = konsultasi()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.framelayout, consulFragment)
            transaction.commit()
        }

        val emercall = v.findViewById<ImageButton>(R.id.emercallBtn)
        emercall.setOnClickListener{
            val emercallFrag = emercall()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.framelayout, emercallFrag)
            transaction.commit()
        }

        return v
    }



}