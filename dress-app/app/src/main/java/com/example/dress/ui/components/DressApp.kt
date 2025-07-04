import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.dress.ui.theme.DressTheme

@Composable
fun DressApp() {
    DressTheme {
        // Main layout and navigation setup goes here
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDressApp() {
    DressApp()
}