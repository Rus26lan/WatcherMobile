package com.rundgrun.watchermobile.presentation.fm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.rundgrun.watchermobile.data.repository.FluidMeshRepositoryImpl
import com.rundgrun.watchermobile.domain.models.fluidmesh.FmIP
import com.rundgrun.watchermobile.domain.repository.FluidMeshRepository
import com.rundgrun.watchermobile.domain.repository.Line


class FMViewModel(application: Application) : AndroidViewModel(application) {
    private var _wagonsListLiveData = MutableLiveData<List<String>>()
    val wagonsListLiveData = _wagonsListLiveData

    private val fmRepository: FluidMeshRepository = FluidMeshRepositoryImpl(context = application)
    private var fmIpList = emptyList<FmIP>()
    var currentIP = ""


    fun selectLineItem(numberLineItem: Int) {
        fmIpList = when (numberLineItem) {
            0 -> {
                fmRepository.getFMListByLines(Line.Line1)
            }
            1 -> {
                fmRepository.getFMListByLines(Line.Line2)
            }
            2 -> {
                fmRepository.getFMListByLines(Line.Line3)
            }
            3 -> {
                fmRepository.getFMListByLines(Line.Line4)
            }
            4 -> {
                fmRepository.getFMListByLines(Line.Line5)
            }
            else -> {
                ArrayList()
            }
        }
        _wagonsListLiveData.value = fmIpList.map { it.name }
    }

    fun selectLineWagons(selectedItem: Int) {
        currentIP = fmIpList[selectedItem].ip
    }
}