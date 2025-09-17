package com.krasouski.colorchooser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.krasouski.colorchooser.ui.theme.ColorChooserTheme
import com.krasouski.presentation.ColorChooserScreen
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = com.krasouski.data.repository.ColorRepositoryImpl(applicationContext)
        val getSavedColorUseCase = com.krasouski.domain.GetSavedColorUseCase(repository)
        val saveColorUseCase = com.krasouski.domain.SaveColorUseCase(repository)
        val viewModel = com.krasouski.presentation.ColorChooserViewModel(getSavedColorUseCase, saveColorUseCase)

        setContent {
            ColorChooserTheme {
                Surface(
                    color = MaterialTheme.colorScheme.surfaceContainerLowest,
                    modifier = Modifier.fillMaxSize()
                ) { ColorChooserScreen(viewModel) }
            }
        }
    }
}