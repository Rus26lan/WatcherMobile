package com.rundgrun.watchermobile.presentation.fm.level

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rundgrun.watchermobile.databinding.FragmentFmLevelBinding

private const val ARG_IP = "arg_ip"

class FMLevelFragment : Fragment() {

    private var _binding: FragmentFmLevelBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FMLevelViewModel by viewModels()
    private val adapter = FMAdapter()

    private var ip: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            ip = it.getString(ARG_IP) ?: ""
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFmLevelBinding.inflate(inflater, container, false)
        binding.fmRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.fmRecyclerView.adapter = adapter
        viewModel.fmModuleIsConnect.observe(viewLifecycleOwner){
            changeConnectState(it)
        }
        viewModel.fmModuleListLiveData.observe(viewLifecycleOwner) {
            adapter.fmList = it
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.start(ip)
    }

    override fun onStop() {
        viewModel.stop()
        super.onStop()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun changeConnectState(isConnect: Boolean){
        if(isConnect){
            binding.fmRecyclerView.visibility = VISIBLE
            binding.notConnectionText.visibility = GONE
        } else {
            binding.fmRecyclerView.visibility = GONE
            binding.notConnectionText.visibility = VISIBLE
        }
    }


    companion object{
        @JvmStatic
        fun newInstance(ip: String) =
            FMLevelFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_IP, ip)
                }
            }
    }
}