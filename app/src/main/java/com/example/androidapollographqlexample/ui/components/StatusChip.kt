package com.example.androidapollographqlexample.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidapollographqlexample.ui.theme.AndroidApolloGraphQLExampleTheme

@Composable
fun StatusChip(
    status: String,
    modifier: Modifier = Modifier
) {
    val (backgroundColor, textColor) = when (status.lowercase()) {
        "alive" -> Color(0xFF4CAF50) to Color.White
        "dead" -> Color(0xFFF44336) to Color.White
        "unknown" -> Color(0xFF9E9E9E) to Color.White
        else -> Color(0xFF9E9E9E) to Color.White
    }
    
    Text(
        text = status,
        style = MaterialTheme.typography.labelSmall,
        color = textColor,
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun StatusChipPreview(modifier: Modifier = Modifier) {
    AndroidApolloGraphQLExampleTheme {
        StatusChip(
            status = "Alive",
            modifier = modifier
        )
    }
}
