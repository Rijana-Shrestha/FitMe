# Implementation Plan - Fix Duplicate String Resources

The project fails to build because `app_name` (and many other strings) are defined multiple times in `app/src/main/res/values/strings.xml`. I will consolidate these strings, removing duplicates while preserving unique values and ensuring the most appropriate values are kept when differences exist.

## Proposed Changes

### [app]

#### [MODIFY] [strings.xml](file:///D:/AndroidStudioProjects/FitMe/app/src/main/res/values/strings.xml)
- Consolidate all string resources.
- Remove duplicate keys.
- Prefer the most complete/accurate values when duplicates differ (e.g., keeping "Rijana" as the user name).
- Organize strings into logical sections (Auth, Onboarding, Home, Search, Profile, Chat, etc.).

## Verification Plan

### Automated Tests
- Run `./gradlew :app:packageDebugResources` to verify that the build error is resolved.
- Run a full build `./gradlew assembleDebug` to ensure no other resource issues exist.

### Manual Verification
- Verify the `strings.xml` file structure is clean and logical.
