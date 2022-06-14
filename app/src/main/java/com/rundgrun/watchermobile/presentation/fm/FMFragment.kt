package com.rundgrun.watchermobile.presentation.fm

import android.R
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.rundgrun.watchermobile.databinding.FragmentFmBinding
import com.rundgrun.watchermobile.presentation.navigator


class FMFragment: Fragment() {

    private var _binding: FragmentFmBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FMViewModel by viewModels()
    private val data = arrayOf("Линия 1", "Линия 2", "Линия 3", "Линия 4", "Линия 5")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFmBinding.inflate(inflater,container,false)

        val adapterLines: ArrayAdapter<String> =
            ArrayAdapter<String>(container!!.context, R.layout.simple_spinner_item, data)
        adapterLines.setDropDownViewResource(com.rundgrun.watchermobile.R.layout.spinner_dropdown_item);
        binding.spinnerLines.adapter = adapterLines
        binding.spinnerLines.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, selectedItem: Int, p3: Long) {
                viewModel.selectLineItem(selectedItem)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        val adapterWagons: ArrayAdapter<String> =
            ArrayAdapter<String>(container.context, R.layout.simple_spinner_item, ArrayList<String>())
        adapterWagons.setDropDownViewResource(com.rundgrun.watchermobile.R.layout.spinner_dropdown_item);
        binding.spinnerWagons.adapter = adapterWagons
        binding.spinnerWagons.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, selectedItem: Int, p3: Long) {
                viewModel.selectLineWagons(selectedItem)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }



        viewModel.wagonsListLiveData.observe(viewLifecycleOwner) {
            Handler(Looper.getMainLooper()).post {
                adapterWagons.clear()
                adapterWagons.addAll(it)
            }
        }

        binding.viewFmLevel.setOnClickListener{
            if(viewModel.currentIP.isNotBlank()){
                navigator().toFMLevel(viewModel.currentIP)
            }
        }
        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}