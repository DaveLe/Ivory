package com.iter.ivory

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


// sign in response id and providers used with AuthUI
private const val RC_SIGN_IN = 420
private val providers = mutableListOf(
        AuthUI.IdpConfig.EmailBuilder().build(),
        AuthUI.IdpConfig.PhoneBuilder().build())

class MainActivity : AppCompatActivity(), VaccineFragment.OnListFragmentInteractionListener{

    var bottomnav : BottomNavigationView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val name : Vaccines = Vaccines("HPV2", Date())
//        Log.e("hello",name.startDate.toString())
//        Log.e("TIME",Vaccines.calculateRemindDate(Date(),4,1).toString() )
        setContentView(R.layout.activity_main)
        // setup bottom navigation bar
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        bottomnav = navigation

        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            // if user is already signed in get name and uid
            val uid = user.uid
            val name = user.displayName
            bottomnav!!.selectedItemId = R.id.navigation_personal

        }else{
            // handle sign in
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build(),
                    RC_SIGN_IN)

        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                // Successfully signed in - update the user object
                val user = FirebaseAuth.getInstance().currentUser!!
                val uid = user.uid
                val name = user.displayName
                if (bottomnav != null){
                    bottomnav!!.selectedItemId = R.id.navigation_personal
                }
            } else {
                // TODO: handle sign in failed
            }
        }else if (requestCode == 5){

            if (resultCode == RESULT_OK) {
                // Successfully signed in - update the user object
                val name = data!!.getStringExtra("name")
                val months = data.getIntExtra("months", -1)
                val years = data.getIntExtra("years", -1)

            } else {
                // TODO: handle sign in failed
            }
        }
        bottomnav!!.selectedItemId = R.id.navigation_personal

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

    override fun onListFragmentInteraction(new: Boolean) {
        val newAdder = Intent(this, VaccineInfo::class.java)
        startActivityForResult(newAdder, 5)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = getMenuInflater()
        inflater.inflate(R.menu.main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.add -> {
                val newAdder = Intent(this, AddVaccination::class.java)
                startActivityForResult(newAdder, 5)
            }
            else -> {
                return true;
            }

        }
        return super.onOptionsItemSelected(item)
    }
}