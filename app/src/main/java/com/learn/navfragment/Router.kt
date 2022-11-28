package com.learn.navfragment

import androidx.fragment.app.Fragment

fun Fragment.router(): Router {
    return requireActivity() as Router
}

interface Router {
    fun routeToDetailFragment()
    fun routeToListFragment()
    fun routeToMainFragment()
}