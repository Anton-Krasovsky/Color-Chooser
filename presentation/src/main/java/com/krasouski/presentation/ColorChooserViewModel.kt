package com.krasouski.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krasouski.domain.ColorType
import com.krasouski.domain.GetSavedColorUseCase
import com.krasouski.domain.SaveColorUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
 

class ColorChooserViewModel(
    private val getSavedColor: GetSavedColorUseCase,
    private val saveColor: SaveColorUseCase
) : ViewModel() {

    private val _selectedColor = MutableStateFlow(ColorType.NONE)
    val selectedColor: StateFlow<ColorType> = _selectedColor.asStateFlow()

    init {
        getSavedColor().onEach { color ->
            _selectedColor.value = color
        }.launchIn(viewModelScope)
    }

    fun selectColor(color: ColorType) {
        viewModelScope.launch {
            saveColor(color)
        }
    }
}