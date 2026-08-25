# Fix KSP Plugin Sync Error

The project is failing to sync because the `com.google.devtools.ksp` plugin is applied in the `:app` module without a specified version, and it is not declared in the root build file or the version catalog.

## Proposed Changes

### Build Configuration

#### [MODIFY] [libs.versions.toml](file:///D:/AndroidStudioProjects/FitMe/gradle/libs.versions.toml)
- Add KSP version `1.9.24-1.0.20` (compatible with Kotlin `1.9.24`).
- Add KSP plugin declaration to the `[plugins]` section.

#### [MODIFY] [build.gradle.kts](file:///D:/AndroidStudioProjects/FitMe/build.gradle.kts) (root)
- Declare the KSP plugin in the `plugins` block with `apply false` to make it available to sub-modules.

#### [MODIFY] [build.gradle.kts](file:///D:/AndroidStudioProjects/FitMe/app/build.gradle.kts) (app)
- Update the plugin application to use the version catalog alias for consistency.

## Verification Plan

### Automated Tests
- Run Gradle sync to ensure the plugin is resolved correctly.
- Execute `./gradlew kspDebugKotlin` (or similar) to verify KSP is functioning.
