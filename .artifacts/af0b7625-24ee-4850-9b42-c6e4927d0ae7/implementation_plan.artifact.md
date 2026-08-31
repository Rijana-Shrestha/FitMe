# Fix Unresolved Reference: OnboardingGenderFragment

The build error `Unresolved reference: OnboardingGenderFragment` in `OnboardingActivity.kt` is caused by an incorrect package declaration in `OnboardingGenderFragment.kt`. Although the file is located in the `com.rijana.fitme.ui.onboarding` directory, its package is declared as `com.rijana.fitme`.

## Proposed Changes

### [Onboarding Component]

#### [MODIFY] [OnboardingGenderFragment.kt](file:///D:/AndroidStudioProjects/FitMe/app/src/main/java/com/rijana/fitme/ui/onboarding/OnboardingGenderFragment.kt)
- Update package declaration to `package com.rijana.fitme.ui.onboarding`.
- Add `import com.rijana.fitme.R` to resolve resource references.

## Verification Plan

### Automated Tests
- Run `./gradlew :app:compileDebugKotlin` to verify the build error is resolved.
