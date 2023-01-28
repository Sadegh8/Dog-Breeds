package com.example.dogbreeds.ui.common

sealed class UiEvent {
    object PopBackStack: UiEvent()
    data class Navigate(val route: String): UiEvent()
    data class ShowSnackBar(
        val message: String,
        val action: String? = null
    ): UiEvent()
    data class ShowToast(
        val message: String,
        val duration: Int
    ): UiEvent()
    object ShowFilterMenu: UiEvent()

}
