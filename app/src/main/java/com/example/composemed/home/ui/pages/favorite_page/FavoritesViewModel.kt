package com.example.composemed.home.ui.pages.favorite_page

import androidx.lifecycle.ViewModel
import com.example.common.utils.UIState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.common.utils.mapToCustomError
import com.example.composemed.home.domain.model.Medication
import com.example.composemed.home.domain.usecases.GetSavedMedicationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getSavedMedicationsUseCase: GetSavedMedicationsUseCase
) : ViewModel() {

    private val _savedMedicineState = mutableStateOf<UIState<List<Medication>>>(UIState.Empty)
    val savedMedicineState: State<UIState<List<Medication>>> get() = _savedMedicineState

    init {
        fetchSavedMedications()
    }

    fun fetchSavedMedications() {
        _savedMedicineState.value = UIState.Loading
        viewModelScope.launch {
            runCatching {
                getSavedMedicationsUseCase.execute()
            }.onSuccess { savedMedicines ->
                if (savedMedicines.isEmpty()) {
                    _savedMedicineState.value = UIState.Empty
                } else {
                    _savedMedicineState.value = UIState.Success(savedMedicines)
                }
            }.onFailure { throwable ->
                val customError = mapToCustomError(throwable)
                _savedMedicineState.value = UIState.Error(customError)
            }
        }
    }
}
