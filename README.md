# Android Debugging Helper

A small native Android app that guides you through enabling Android Developer Options and Wireless debugging, and pairing your phone with a trusted computer using ADB.

## Build APK automatically with GitHub Actions

1. Open the **Actions** tab in this repository.
2. Select **Build Android Debugging Helper APK**.
3. Choose **Run workflow** if a run has not already started automatically.
4. Open the completed workflow run.
5. Under **Artifacts**, download `AndroidDebuggingHelper-debug-apk`.
6. Extract the downloaded ZIP to find `app-debug.apk`, then transfer it to your phone and install it.

A push to the `main` branch also starts a build automatically.

## How wireless debugging works

1. On the phone, open Settings > About phone and tap Build number seven times to enable Developer Options (menu location varies by manufacturer).
2. In Developer Options, enable **Wireless debugging**.
3. Tap **Pair device with pairing code**.
4. On the computer with Android SDK Platform-Tools installed, run `adb pair PHONE_IP:PAIRING_PORT`, then enter the code shown on the phone.
5. Return to the main Wireless debugging screen and run `adb connect PHONE_IP:CONNECT_PORT` using the separate connection port shown there.
6. Verify the connection with `adb devices`.

## Android restrictions and security

A regular third-party app cannot silently enable Developer Options, enable Wireless debugging, or approve ADB pairing. You must confirm these actions in Android Settings. Wireless debugging is generally available on Android 11 or newer. Pair only with trusted computers, use trusted Wi-Fi, and turn Wireless debugging off when done.

The app requests no runtime permissions and does not implement networking or data collection.
