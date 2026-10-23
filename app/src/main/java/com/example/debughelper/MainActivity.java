package com.example.debughelper;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private int dp(float value) {
        return (int) (value * getResources().getDisplayMetrics().density + 0.5f);
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        getWindow().setStatusBarColor(0xFFF4F7FB);
        getWindow().setNavigationBarColor(0xFFF4F7FB);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);

        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(0xFFF4F7FB);
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(20), dp(22), dp(20), dp(28));
        scroll.addView(root);

        TextView welcome = text("WELCOME TO SHAMEERUDEEN TESTING APP", 23, 0xFF0B7A75);
        welcome.setTypeface(null, 1);
        root.addView(welcome);
        gap(root, 8);
        TextView thanks = text("THANKS FOR BEING THE PART", 17, 0xFF44546A);
        thanks.setTypeface(null, 1);
        root.addView(thanks);
        gap(root, 12);
        TextView title = text("Android Debugging Helper", 25, 0xFF172B4D);
        title.setTypeface(null, 1);
        root.addView(title);
        gap(root, 8);
        root.addView(text(
                "A simple guided setup for wireless ADB debugging. Android requires you to enable debugging and approve pairing yourself.",
                15, 0xFF44546A));

        section(root, "1. Enable Developer Options",
                "Open Developer Options. If it is not enabled yet, go to Settings > About phone > tap Build number 7 times. You may need your device PIN. On some phones, Build number is under Software information.");
        button(root, "Open Developer Options", v -> openSettings(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS));

        section(root, "2. Turn on Wireless debugging",
                "In Developer Options, turn on Wireless debugging. This usually requires Android 11 or newer. Keep your phone and computer on the same trusted Wi-Fi network.");
        button(root, "Open Developer Options", v -> openSettings(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS));
        button(root, "Open Wi-Fi settings", v -> openSettings(Settings.ACTION_WIFI_SETTINGS));

        section(root, "3. Pair with your Mac",
                "On the phone, open Wireless debugging > Pair device with pairing code. On your Mac, install Android SDK Platform-Tools and run the pairing command shown below, replacing the placeholders with the values shown on your phone:\n\nadb pair PHONE_IP:PAIRING_PORT\n\nEnter the pairing code shown on the phone. The pairing port is different from the connection port.");
        button(root, "Show Mac pairing steps", v -> showDialog("Pair from your Mac",
                "1. On the phone, open Wireless debugging > Pair device with pairing code.\n\n2. In Mac Terminal run:\n\nadb pair PHONE_IP:PAIRING_PORT\n\n3. Enter the code shown on your phone. Replace the placeholders with the displayed values."));

        section(root, "4. Connect and verify",
                "Return to the main Wireless debugging screen and note the IP address and port shown there. Run the connect command using that port (not the pairing port).");
        button(root, "Show connection commands", v -> showDialog("Connect from your Mac",
                "Run these commands in Terminal, replacing the placeholders with the values on your phone:\n\nadb connect PHONE_IP:CONNECT_PORT\nadb devices\n\nAccept any debugging authorization prompt shown on the phone. Both devices should be on the same Wi-Fi network."));

        section(root, "Keep your phone secure",
                "Pair only with computers you trust. A paired computer can use ADB debugging tools with your phone. Use a trusted private Wi-Fi network and turn Wireless debugging off when finished. Menu names vary by manufacturer.");
        gap(root, 18);
        root.addView(text("No permissions, network access, or data collection are built into this helper.", 13, 0xFF64748B));
        setContentView(scroll);
    }

    private TextView text(String value, int size, int color) {
        TextView view = new TextView(this);
        view.setText(value);
        view.setTextSize(size);
        view.setTextColor(color);
        view.setLineSpacing(dp(3), 1.0f);
        return view;
    }

    private void gap(LinearLayout root, int height) {
        root.addView(new View(this), new LinearLayout.LayoutParams(1, dp(height)));
    }

    private void section(LinearLayout root, String heading, String body) {
        gap(root, 20);
        TextView h = text(heading, 18, 0xFF172B4D);
        h.setTypeface(null, 1);
        root.addView(h);
        gap(root, 7);
        root.addView(text(body, 14, 0xFF344563));
    }

    private void button(LinearLayout root, String label, View.OnClickListener listener) {
        gap(root, 10);
        Button button = new Button(this);
        button.setText(label);
        button.setAllCaps(false);
        button.setOnClickListener(listener);
        root.addView(button, new LinearLayout.LayoutParams(-1, dp(50)));
    }

    private void openSettings(String action) {
        try {
            startActivity(new Intent(action));
        } catch (Exception e) {
            Toast.makeText(this,
                    "Open Settings manually and search for Developer Options.",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void showDialog(String title, String message) {
        new android.app.AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}
