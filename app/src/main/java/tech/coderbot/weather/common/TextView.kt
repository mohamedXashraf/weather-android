package tech.coderbot.weather.common

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import tech.coderbot.weather.ui.theme.PrimaryColor

@Composable
fun TextView(text: String, size: Int = 24, color: Color = PrimaryColor, weight: FontWeight = FontWeight.Normal)
{
    Text(text = text, style = TextStyle(
        color = color,
        fontSize = size.sp,
        fontWeight = weight
    ))
}