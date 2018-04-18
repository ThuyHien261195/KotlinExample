package com.example.thuyhien.kotlinrxjava.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.view.MenuItem
import com.example.thuyhien.kotlinrxjava.R
import com.example.thuyhien.kotlinrxjava.view.MainView
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity(), MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true

            when (menuItem.itemId) {
                R.id.action_coupon -> {
                    val intent = Intent(this, CouponActivity::class.java)
                    startActivity(intent)
                }
                R.id.action_socket -> {
                    val intent = Intent(this, CommentActivity::class.java)
                    startActivity(intent)
                }
                R.id.action_service -> {
                    val intent = Intent(this, ExampleServiceActivity::class.java)
                    startActivity(intent)
                }
                R.id.action_bound_service -> {
                    val intent = Intent(this, BoundServiceActivity::class.java)
                    startActivity(intent)
                }
                R.id.action_count_intent_service -> {
                    val intent = Intent(this, CountActivity::class.java)
                    startActivity(intent)
                }
                R.id.action_alarm -> {
                    val intent = Intent(this, AlarmActivity::class.java)
                    startActivity(intent)
                }
                R.id.action_local_broadcast -> {
                    val intent = Intent(this, LocalBroadcastActivity::class.java)
                    startActivity(intent)
                }
            }

            drawerLayout.closeDrawers()
            true
        }
        setSupportActionBar(toolbarMain)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.mipmap.ic_menu_black_24dp)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        android.R.id.home -> {
            drawerLayout.openDrawer(GravityCompat.START)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}


