package com.iter.ivory

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.iter.ivory.dummy.DummyContent
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


// sign in response id and providers used with AuthUI
private const val RC_SIGN_IN = 420
private val providers = mutableListOf(
        AuthUI.IdpConfig.EmailBuilder().build(),
        AuthUI.IdpConfig.PhoneBuilder().build())

class MainActivity : AppCompatActivity(), VaccineFragment.OnListFragmentInteractionListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val name : Vaccines = Vaccines("HPV2", Date())
//        Log.e("hello",name.startDate.toString())
//        Log.e("TIME",Vaccines.calculateRemindDate(Date(),4,1).toString() )

        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            // if user is already signed in get name and uid
            val uid = user.uid
            val name = user.displayName

        }else{
            // handle sign in
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build(),
                    RC_SIGN_IN)

        }

        setContentView(R.layout.activity_main)
        // setup bottom navigation bar
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_personal
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                // Successfully signed in - update the user object
                val user = FirebaseAuth.getInstance().currentUser!!
                val uid = user.uid
                val name = user.displayName

            } else {
                // TODO: handle sign in failed
            }
        }
    }


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            // TODO: decide on which navigation menus to include
            R.id.navigation_personal -> {
                val vaccineFragment: Fragment = VaccineFragment.newInstance(true)
                supportFragmentManager.beginTransaction().replace(R.id.content_view, vaccineFragment).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_suggested -> {
                val vaccineFragment: Fragment = VaccineFragment.newInstance(true)
                supportFragmentManager.beginTransaction().replace(R.id.content_view, vaccineFragment).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
        //do nothing
    }
}