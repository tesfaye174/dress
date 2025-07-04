# ProGuard rules for the Dress App

# Keep the MainActivity class
-keep class com.example.dress.MainActivity { *; }

# Keep all classes in the data.models package
-keep class com.example.dress.data.models.** { *; }

# Keep all public methods in the ui.theme package
-keep public class com.example.dress.ui.theme.** { *; }

# Keep all composable functions
-keep @androidx.compose.runtime.Composable class * { *; }

# Keep annotations
-keepattributes *Annotation*

# Optimize the code
-optimizations !code/simplification/arithmetic

# Add any additional rules as needed for libraries or specific classes