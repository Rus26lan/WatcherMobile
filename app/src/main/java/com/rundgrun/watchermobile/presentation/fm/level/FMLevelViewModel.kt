package com.rundgrun.watchermobile.presentation.fm.level

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rundgrun.watchermobile.domain.usecase.GetFMModuleListUseCase
import com.rundgrun.watchermobile.domain.models.fluidmesh.FmModule

class FMLevelViewModel : ViewModel() {
    private var _fmModuleListLiveData = MutableLiveData<List<FmModule>>()
    val fmModuleListLiveData = _fmModuleListLiveData

    private var _fmModuleIsConnect = MutableLiveData<Boolean>()
    val fmModuleIsConnect = _fmModuleIsConnect

    private val getFMModuleListUseCase = GetFMModuleListUseCase()

    @Volatile
    var play: Boolean = false;

    fun start(ip: String) {
        play = true
        Thread {
            while (play) {
                Thread.sleep(1000)
                try {
                    println(ip)
                    val list = getFMModuleListUseCase.execute(ip)
                    if (list.isNotEmpty()) {
                        Handler(Looper.getMainLooper()).post {
                            _fmModuleIsConnect.value = true
                            _fmModuleListLiveData.value = list
                        }
                    } else {
                        _fmModuleIsConnect.value = false
                    }
                } catch (e: Exception) {
                    Handler(Looper.getMainLooper()).post {
                        _fmModuleIsConnect.value = false
                    }
                }
            }
        }.start()
    }

    fun stop() {
        _fmModuleIsConnect.value = false
        play = false
    }

    override fun onCleared() {
        play = false
        super.onCleared()
    }
}