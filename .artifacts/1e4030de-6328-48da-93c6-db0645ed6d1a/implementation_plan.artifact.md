# Fix Firebase Dependency Resolution Issue

The project is failing to build because it cannot resolve `com.google.firebase:firebase-auth-ktx` and `com.google.firebase:firebase-firestore-ktx`. This is likely due to the Firebase BoM being used with artifacts that might have changed or aren't being correctly mapped by the specific BoM version (34.18.0), or simply because the `-ktx` versions are being phased out in favor of the main artifacts which now include Kotlin support.

## Proposed Changes

### [Gradle Configuration]

#### [MODIFY] [libs.versions.toml](file:///D:/AndroidStudioProjects/FitMe/gradle/libs.versions.toml)
- Add Firebase BoM and specific Firebase libraries to the version catalog.
- Use the latest stable version of the BoM.

#### [MODIFY] [app/build.gradle.kts](file:///D:/AndroidStudioProjects/FitMe/app/build.gradle.kts)
- Update the dependencies block to use the libraries defined in the version catalog.
- Remove the `-ktx` suffix from Firebase dependencies, as these are now included in the base artifacts when using recent BoM versions.

## Verification Plan

### Automated Tests
- Run `./gradlew assembleDebug` to ensure the project builds successfully and dependencies are resolved.
