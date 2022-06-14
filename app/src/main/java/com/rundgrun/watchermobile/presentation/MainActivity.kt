package com.rundgrun.watchermobile.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.rundgrun.watchermobile.R
import com.rundgrun.watchermobile.databinding.ActivityMainBinding
import com.rundgrun.watchermobile.presentation.fm.FMFragment
import com.rundgrun.watchermobile.presentation.fm.level.FMLevelFragment
import com.rundgrun.watchermobile.presentation.menu.MenuFragment
import com.rundgrun.watchermobile.presentation.option.OptionFragment
import com.rundgrun.watchermobile.presentation.wagons.WagonsFragment

fun Fragment.navigator() = activity as Navigator

class MainActivity : AppCompatActivity(), Navigator {
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        this.binding = binding
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, MenuFragment())
                .commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun back() {
        onBackPressed()
    }

    override fun toFM() {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container, FMFragment())
            .commit()
    }

    override fun toOption() {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container, OptionFragment())
            .commit()
    }


    override fun toWagons() {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container, WagonsFragment())
            .commit()
    }

    override fun toMenu() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    override fun toFMLevel(ip: String) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container, FMLevelFragment.newInstance(ip))
            .commit()
    }
}