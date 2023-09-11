package com.example.kotlinwithcompose.ui.theme

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

val ThemeShapes = Shapes(
    small = RoundedCornerShape(18.dp),
    medium = CutCornerShape(14.dp),
    large = CutCornerShape(10.dp)
)
