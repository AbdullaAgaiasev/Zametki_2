package com.example.a4_month_lesson_1

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.util.EventLogTags.Description
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.a4_month_lesson_1.data.Pref
import com.example.a4_month_lesson_1.databinding.ActivityMainBinding
import com.example.a4_month_lesson_1.ui.accept.AcceptFragment
import com.example.a4_month_lesson_1.ui.auth.AuthFragment
import com.example.a4_month_lesson_1.ui.home.HomeFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var pref: Pref
    private var auth = FirebaseAuth.getInstance()

    @SuppressLint("SuspiciousIndentation", "WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = Pref(this)
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        if (!pref.isUserSeen())
        navController.navigate(R.id.onBoardingFragment)
        if (auth.currentUser ==null) {
            navController.navigate(R.id.authFragment)
        }

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_profile,
                R.id.taskFragment
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        val bottomNavFragments = arrayListOf(
            R.id.navigation_home,
            R.id.navigation_dashboard,
            R.id.navigation_notifications,
            R.id.navigation_profile
        )
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            navView.isVisible = bottomNavFragments.contains(destination.id)
            if (destination.id==R.id.onBoardingFragment||destination.id==R.id.splashFragment) {
                supportActionBar?.hide()
            } else {
                supportActionBar?.show()
            }
        }
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
////            R.id.search -> {
////                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show()
////            }
////            R.id.settings -> {
////                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
////            }
////            R.id.update -> {
////                Toast.makeText(this, "Update Profile", Toast.LENGTH_SHORT).show()
////            }
//            R.id.btn_log_out -> {
//                val alert: AlertDialog = AlertDialog.Builder(this).create()
//                alert.setTitle("Logout")
//                alert.setMessage("Are you sure you want to logout?")
//                alert.setButton(AlertDialog.BUTTON_POSITIVE, "Yes") { dialog, which ->
//                    CoroutineScope(Dispatchers.IO).launch{
//                        withContext(Dispatchers.Main) {
//                            Firebase.auth.signOut()
//                        }
//                    }
//                    val intent = Intent(this@MainActivity,AcceptFragment::class.java)
//                    dialog.dismiss()
//                    startActivity(intent)
//                }
//                alert.setButton(AlertDialog.BUTTON_NEGATIVE, "No") { dialog, which ->
//                    dialog.dismiss()
//                }
//                alert.show()
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }
}