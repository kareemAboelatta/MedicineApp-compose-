package com.example.composemed.home.ui.pages.medicine


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.utils.UIState
import com.example.common.utils.mapToCustomError
import com.example.composemed.home.domain.model.Medication
import com.example.composemed.home.domain.usecases.GetRemoteMedicationsUseCase
import com.example.composemed.home.domain.usecases.SaveMedicineUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

import javax.inject.Inject


@HiltViewModel
class MedicineViewModel @Inject constructor(
    private val getRemoteMedicationsUseCase: GetRemoteMedicationsUseCase,
    private val saveMedicineUseCase: SaveMedicineUseCase,
) : ViewModel() {

    private val _medicineState = MutableStateFlow<UIState<List<Medication>>>(UIState.Empty)
    val medicineState: StateFlow<UIState<List<Medication>>> get() = _medicineState


    private val _saveMedicineState = MutableStateFlow<UIState<Medication>>(UIState.Empty)
    val saveMedicineState: StateFlow<UIState<Medication>> get() = _saveMedicineState


    init {
        fetchMedicines()
    }

    fun fetchMedicines() {
        _medicineState.value = UIState.Loading
        viewModelScope.launch {
            runCatching {
                getRemoteMedicationsUseCase.execute()
            }.onSuccess { fetchedMedicines ->
                if (fetchedMedicines.isEmpty()) {
                    _medicineState.value = UIState.Empty
                } else {
                    _medicineState.value = UIState.Success(fetchedMedicines)
                }
            }.onFailure { throwable ->
                val customError = mapToCustomError(throwable)
                _medicineState.value = UIState.Error(customError)
            }
        }
    }


    fun saveMedicine(medication: Medication) {
        _saveMedicineState.value = UIState.Loading
        viewModelScope.launch {
            runCatching {
                saveMedicineUseCase.execute(medication)
            }.onSuccess {
                _saveMedicineState.value = UIState.Success(medication)
            }.onFailure { throwable ->
                val customError = mapToCustomError(throwable)
                _saveMedicineState.value = UIState.Error(customError)
            }
        }
    }


}
