package com.example.trainingassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import com.example.trainingassistant.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{

    lateinit var homeFragment: HomeFragment
    lateinit var mealPlanFragment: MealPlanFragment
    lateinit var trainingPlanFragment: TrainingPlanFragment
    lateinit var calendarFragment: CalendarFragment
    lateinit var bmiFragment: BmiFragment
    lateinit var aboutUsFragment: AboutUsFragment
    lateinit var aboutAppFragment: AboutAppFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val database = Firebase.database
        val myRef = database.getReference("/sports/sportCollection")

        myRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.value
                Log.d("TAG", "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException())
            }

        })

        //Menu Fragments Navigation

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.title = "Menu"

        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.open,
            R.string.close
        ){

        }
        drawerToggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        homeFragment = HomeFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_Home->{
                homeFragment = HomeFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, homeFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.nav_TrainingPlans->{
                trainingPlanFragment = TrainingPlanFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, trainingPlanFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.nav_BMI->{
                bmiFragment = BmiFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, bmiFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.nav_MealPlan->{
                mealPlanFragment = MealPlanFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, mealPlanFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.nav_Calendar->{
                calendarFragment = CalendarFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, calendarFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.nav_AboutApp->{
                aboutAppFragment = AboutAppFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, aboutAppFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.nav_AboutUs->{
                aboutUsFragment = AboutUsFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, aboutUsFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if(drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        else{
            super.onBackPressed()
        }

    }
}
