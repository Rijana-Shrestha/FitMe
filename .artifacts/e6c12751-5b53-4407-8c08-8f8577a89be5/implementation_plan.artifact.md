# Implementation Plan - Fix Firebase Dependency Resolution Issue

The project is failing to build because it cannot resolve `com.google.firebase:firebase-auth-ktx` and `com.google.firebase:firebase-firestore-ktx` when using Firebase BoM version `34.18.0`.

Starting from Firebase BoM v34.0.0, the Kotlin extension (`-ktx`) modules have been removed from the BoM as their functionality has been merged into the main artifacts.

## Proposed Changes

### [app module](file:///D:/AndroidStudioProjects/FitMe/app/build.gradle.kts)

#### [MODIFY] [app/build.gradle.kts](file:///D:/AndroidStudioProjects/FitMe/app/build.gradle.kts)
- Update Firebase dependencies to use the main modules instead of the deprecated `-ktx` modules.

```diff
-    implementation("com.google.firebase:firebase-auth-ktx")
-    implementation("com.google.firebase:firebase-firestore-ktx")
+    implementation("com.google.firebase:firebase-auth")
+    implementation("com.google.firebase:firebase-firestore")
```

## Verification Plan

### Automated Tests
- Run Gradle sync or `./gradlew :app:assembleDebug` to verify that dependencies resolve correctly.

### Manual Verification
- Confirm that the project builds successfully in Android Studio.
