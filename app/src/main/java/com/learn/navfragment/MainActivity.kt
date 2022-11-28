package com.learn.navfragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.learn.navfragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Router {
    private var binding: ActivityMainBinding? = null

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?
        ) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            showUpButton()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, true)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolBar)
    }

    override fun onDestroy() {
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
        super.onDestroy()
    }

    override fun routeToDetailFragment() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer, DetailFragment()
            ).addToBackStack(null)
            .commit()
    }

    override fun routeToListFragment() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer, ListFragment()
            ).addToBackStack(null)
            .commit()
    }

    override fun routeToMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer, MainFragment()
            ).addToBackStack(null).commit()
        supportFragmentManager
            .popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

    }

    private fun showUpButton() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setDisplayShowHomeEnabled(false)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}