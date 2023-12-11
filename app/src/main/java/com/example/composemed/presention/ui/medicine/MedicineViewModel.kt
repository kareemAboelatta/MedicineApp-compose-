package com.example.composemed.presention.ui.medicine

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.utils.CustomError
import com.example.common.utils.UIState
import com.example.composemed.domain.model.Medication
import com.example.composemed.domain.usecases.GetMedicationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import okio.IOException

import javax.inject.Inject


@HiltViewModel
class MedicineViewModel @Inject constructor(
    private val getMedicationsUseCase: GetMedicationsUseCase,
) : ViewModel() {

    private val _medicineState = mutableStateOf<UIState<List<Medication>>>(UIState.Empty)
    val medicineState: State<UIState<List<Medication>>> get() = _medicineState


    init {
        fetchUsers()
    }

    fun fetchUsers() {
        _medicineState.value = UIState.Loading
        viewModelScope.launch {
            runCatching {
                getMedicationsUseCase.execute()
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



    private fun mapToCustomError(error: Throwable): CustomError {
        return when (error) {
            is IOException -> CustomError.NoInternetError
            is HttpException -> CustomError.ServerError(error.code())
            else -> CustomError.UnknownError
        }
    }


}
