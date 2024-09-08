package com.cenkeraydin.kekodchallengeone.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EgoViewModel :ViewModel() {
    private val _switchHappinessState = MutableLiveData<Boolean>()
    val switchHappinessState: LiveData<Boolean> get() = _switchHappinessState

    private val _switchKindnessState = MutableLiveData<Boolean>()
    val switchKindnessState: LiveData<Boolean> get() = _switchKindnessState

    private val _switchOptimismState = MutableLiveData<Boolean>()
    val switchOptimismState: LiveData<Boolean> get() = _switchOptimismState

    private val _switchRespectState = MutableLiveData<Boolean>()
    val switchRespectState: LiveData<Boolean> get() = _switchRespectState

    private val _switchGivingState = MutableLiveData<Boolean>()
    val switchGivingState: LiveData<Boolean> get() = _switchGivingState

    private val _switchEgoState = MutableLiveData<Boolean>()
    val switchEgoState: LiveData<Boolean> get() = _switchEgoState

    private val _toggleOrder = MutableLiveData<MutableList<Int>>(mutableListOf())
    val toggleOrder: LiveData<MutableList<Int>> get() = _toggleOrder

    fun addToggleOrder(switchId: Int) {
        _toggleOrder.value?.let { order ->
            if (!order.contains(switchId)) {
                order.add(switchId)
                _toggleOrder.value = order
            }
        }
    }

    fun removeToggleOrder(switchId: Int) {
        _toggleOrder.value?.let { order ->
            if (order.contains(switchId)) {
                order.remove(switchId)
                _toggleOrder.value = order
            }
        }
    }

    fun setSwitchHappinessState(state: Boolean) {
        _switchHappinessState.value = state
    }

    fun setSwitchKindnessState(state: Boolean) {
        _switchKindnessState.value = state
    }

    fun setSwitchOptimismState(state: Boolean) {
        _switchOptimismState.value = state
    }

    fun setSwitchRespectState(state: Boolean) {
        _switchRespectState.value = state
    }

    fun setSwitchGivingState(state: Boolean) {
        _switchGivingState.value = state
    }

    fun setSwitchEgoState(state: Boolean) {
        _switchEgoState.value = state
    }


}