package id.kotlin.bright_soul

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.FragmentManager

class listdokter : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v : View = inflater.inflate(R.layout.fragment_listdokter, container, false)
        v.findViewById<ImageView>(R.id.imageView14).setOnClickListener {
            val intent = Intent(activity, formdr::class.java)
            startActivity(intent)
            activity?.finish()
        }

        // Inflate the layout for this fragment
        return v
    }


}