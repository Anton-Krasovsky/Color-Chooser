package com.krasouski.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.krasouski.domain.ColorType

private fun ColorType.toButtonColor(): Color =
    when (this) {
        ColorType.RED -> Color.Red
        ColorType.GREEN -> Color(0xFF4CAF50)
        ColorType.BLUE -> Color(0xFF2196F3)
        else -> Color.Gray
    }

@Composable
private fun buttonTextColor(): Color = MaterialTheme.colorScheme.surface

@Composable
fun ColorChooserScreen(viewModel: ColorChooserViewModel) {
    val selectedColor by viewModel.selectedColor.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            ColorButton(
                label = "R",
                color = ColorType.RED,
                selected = selectedColor == ColorType.RED,
                onClick = { viewModel.selectColor(ColorType.RED) }
            )
            ColorButton(
                label = "G",
                color = ColorType.GREEN,
                selected = selectedColor == ColorType.GREEN,
                onClick = { viewModel.selectColor(ColorType.GREEN) }
            )
            ColorButton(
                label = "B",
                color = ColorType.BLUE,
                selected = selectedColor == ColorType.BLUE,
                onClick = { viewModel.selectColor(ColorType.BLUE) }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        val textColor = MaterialTheme.colorScheme.onSurface
        Text(
            text = "Your color is ${if (selectedColor == ColorType.NONE) "None" else selectedColor.displayName}",
            color = textColor,
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Composable
private fun ColorButton(
    label: String,
    color: ColorType,
    selected: Boolean,
    onClick: () -> Unit
) {
    val buttonColor = color.toButtonColor()
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = buttonTextColor()
        ),
        border = if (selected) BorderStroke(2.dp, MaterialTheme.colorScheme.onSurface) else null,
        shape = RoundedCornerShape(50),
        modifier = Modifier
            .width(88.dp)
            .height(56.dp)
    ) {
        Text(
            label,
            style = MaterialTheme.typography.headlineSmall
        )
    }
}


