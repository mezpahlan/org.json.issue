# Uncomment this when debugging only!
# -dontobfuscate

# Common attributes
-keepattributes SourceFile
-keepattributes LineNumberTable
-keepattributes InnerClasses
-keepattributes Signature
-keepattributes Exceptions
-keepattributes EnclosingMethod
-keepattributes *Annotation*


# Keep native methods
-keepclassmembers class * {
    native <methods>;
}

# Remove calls to Log
-assumenosideeffects class android.util.Log {
  public static *** v(...);
  public static *** d(...);
  public static *** i(...);
  public static *** w(...);
  public static *** e(...);
}
