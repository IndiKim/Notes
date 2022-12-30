package com.geektech.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.geektech.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        openFragment(NoteFragment())
    }

    private fun openFragment(fragment: Fragment) {
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit();


    }
}