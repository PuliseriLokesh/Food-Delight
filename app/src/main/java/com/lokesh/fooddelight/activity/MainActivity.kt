package com.lokesh.fooddelight.activity

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.lokesh.fooddelight.R
import com.lokesh.fooddelight.fragment.FaqsFragment
import com.lokesh.fooddelight.fragment.FavouritesFragment
import com.lokesh.fooddelight.fragment.MyProfileFragment
import com.lokesh.fooddelight.fragment.OrderHistoryFragment
import com.lokesh.fooddelight.fragment.RestaurantFragment

class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var toolbar: Toolbar
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView

    lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var previousMenu: MenuItem? = null

        drawerLayout = findViewById(R.id.drawerLayout)
        toolbar = findViewById(R.id.toolbar)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        frameLayout = findViewById(R.id.frameLayout)
        navigationView = findViewById(R.id.navigationView)
        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)

        setUpToolbar()
        openHome()

        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this@MainActivity,
            drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener {

            if (previousMenu != null) {
                previousMenu?.isChecked = false
            }
            it.isCheckable = true
            it.isChecked = true
            previousMenu = it

            when (it.itemId) {
                R.id.homeMenu -> {
                    openHome()
                }
                R.id.myProfile -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frameLayout,
                        MyProfileFragment()
                    ).commit()
                    drawerLayout.closeDrawers()
                    setUpToolbar()
                    supportActionBar?.title = "My Profile"
                }
                R.id.favourites -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frameLayout,
                        FavouritesFragment()
                    ).commit()
                    drawerLayout.closeDrawers()
                    setUpToolbar()
                    supportActionBar?.title = "Favourites"
                }
                R.id.orderHistory -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frameLayout,
                        OrderHistoryFragment()
                    ).commit()
                    drawerLayout.closeDrawers()
                    setUpToolbar()
                    supportActionBar?.title = "Order History"
                }
                R.id.faqs -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frameLayout,
                        FaqsFragment()
                    ).commit()
                    drawerLayout.closeDrawers()
                    setUpToolbar()
                    supportActionBar?.title = "Frequently Asked Questions"
                }
                R.id.logout -> {
                    val alert = AlertDialog.Builder(this)
                    alert.setTitle("Logout")
                    alert.setMessage("Are you sure want to Log out!!")
                    alert.setPositiveButton("Confirm") { _, _ ->
                        sharedPreferences.edit().clear().apply()
                        val intent = Intent(this@MainActivity,LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    alert.setNegativeButton("Cancel") { _, _ ->

                    }
                    alert.create()
                    alert.show()
                }
            }
            return@setNavigationItemSelectedListener true
        }
    }

    fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "FOOD DELIGHT"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun openHome() {
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, RestaurantFragment())
            .commit()
        drawerLayout.closeDrawers()
        supportActionBar?.title = "Home"
        navigationView.setCheckedItem(R.id.homeMenu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        val frag = supportFragmentManager.findFragmentById(R.id.frameLayout)
        when (frag) {
            !is RestaurantFragment -> openHome()
            else -> ActivityCompat.finishAffinity(this@MainActivity)
        }
    }
}