package com.example.composemed.presention.ui.medicine

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.utils.UIState
import com.example.common.utils.mapToCustomError
import com.example.composemed.domain.model.models.Medication
import com.example.composemed.domain.usecases.GetRemoteMedicationsUseCase
import com.example.composemed.domain.usecases.SavedMedicineUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

import javax.inject.Inject


@HiltViewModel
class MedicineViewModel @Inject constructor(
    private val getRemoteMedicationsUseCase: GetRemoteMedicationsUseCase,
    private val savedMedicineUseCase: SavedMedicineUseCase,
) : ViewModel() {

    private val _medicineState = mutableStateOf<UIState<List<Medication>>>(UIState.Empty)
    val medicineState: State<UIState<List<Medication>>> get() = _medicineState



    private val _saveMedicineState = mutableStateOf<UIState<Medication>>(UIState.Empty)
    val saveMedicineState: State<UIState<Medication>> get() = _saveMedicineState


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
                savedMedicineUseCase.execute(medication)
            }.onSuccess {
                _saveMedicineState.value = UIState.Success(medication)
            }.onFailure { throwable ->
                val customError = mapToCustomError(throwable)
                _saveMedicineState.value = UIState.Error(customError)
            }
        }
    }


}
