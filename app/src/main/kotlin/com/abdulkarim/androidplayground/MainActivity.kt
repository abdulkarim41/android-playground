package com.abdulkarim.androidplayground

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.abdulkarim.securedatastore.SecureDataStorage
import com.abdulkarim.sharedpref.SharedPrefHelper
import com.abdulkarim.sharedpref.SpKey
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var pref: SharedPrefHelper
    @Inject lateinit var secureDataStorage: SecureDataStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        pref.put(SpKey.name, "Abdul Karim")
        val name = pref.get(SpKey.name, "")
        Log.e("KKK","from general sharedpref : $name")

        secureDataStorage.put(SpKey.name,"Abdul Karim")
        secureDataStorage.put(SpKey.phone,"01877516041")
        secureDataStorage.put(SpKey.token,"zOk01877516041132132132131321KKTSTED&898941")
        val secureName = secureDataStorage.get(SpKey.name)
        val securePhone = secureDataStorage.get(SpKey.phone)
        val secureToken = secureDataStorage.get(SpKey.token)
        Log.e("KKK","from secure storage : $secureName")
        Log.e("KKK","from secure storage : $securePhone")
        Log.e("KKK","from secure storage : $secureToken")

    }
}