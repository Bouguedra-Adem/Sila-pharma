package com.example.myapplication.view


import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.example.myapplication.R
import androidx.appcompat.app.AppCompatActivity





// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SplashScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(com.example.myapplication.R.layout.fragment_splash_screen, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar!!.hide()

        activity!!.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        Handler().postDelayed({
            //start fragment
           view.findNavController()
               .navigate(
                   com.example.myapplication.R.id.action_splashScreen_to_listVilleFragment,
                   null,
                   NavOptions.Builder()
                       .setPopUpTo(
                           com.example.myapplication.R.id.splashScreen,
                           true).build()
               )
            //finish this activity
            activity!!.window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            (activity as AppCompatActivity).supportActionBar!!.show()
        },4000)

    }
}
