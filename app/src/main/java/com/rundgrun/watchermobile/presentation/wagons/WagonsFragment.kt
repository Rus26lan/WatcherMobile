package com.rundgrun.watchermobile.presentation.wagons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.rundgrun.watchermobile.databinding.FragmentWagonsBinding
import com.rundgrun.watchermobile.presentation.navigator

class WagonsFragment: Fragment() {

    private var _binding: FragmentWagonsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWagonsBinding.inflate(inflater, container, false)


        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val buttonAnimation = AnimationUtils.loadAnimation(context, R.anim.button_anim)
//        binding.root.animation = buttonAnimation
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}