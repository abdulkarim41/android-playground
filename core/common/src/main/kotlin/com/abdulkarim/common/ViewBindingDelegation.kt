package com.abdulkarim.common

import android.app.Activity
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


class ActivityViewBindingDelegate<T : ViewBinding>(
    private val bindingInflater: (LayoutInflater) -> T
) : ReadOnlyProperty<Activity, T> {

    private var binding: T? = null

    override fun getValue(thisRef: Activity, property: KProperty<*>): T {
        return binding ?: bindingInflater(thisRef.layoutInflater).also {
            thisRef.setContentView(it.root)
            binding = it
        }
    }
}

fun <T : ViewBinding> viewBinding(bindingInflater: (LayoutInflater) -> T) =
    ActivityViewBindingDelegate(bindingInflater)