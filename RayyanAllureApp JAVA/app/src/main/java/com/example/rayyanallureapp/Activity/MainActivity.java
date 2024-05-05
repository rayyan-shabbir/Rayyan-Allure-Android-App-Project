package com.example.rayyanallureapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rayyanallureapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView txtTime;
    private TextView txtDate;
    private TextView txtDay;

    // Handler and Runnable for updating time
    private Handler handler = new Handler();
    private Runnable updateTimeRunnable;

    // Notification channel ID and name (for Android 8.0 and higher)
    private static final String CHANNEL_ID = "notification_channel";
    private static final String CHANNEL_NAME = "Notification Channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel(); // Create notification channel

        txtTime = findViewById(R.id.txtTime);
        txtDate = findViewById(R.id.txtDate);
        txtDay = findViewById(R.id.txtDay);

        // Initialize the updateTimeRunnable
        updateTimeRunnable = new Runnable() {
            @Override
            public void run() {
                displayDateTime();
                // Repeat this runnable every second (1000 milliseconds)
                handler.postDelayed(this, 1000);
            }
        };

        // Start updating time when the activity is created
        handler.post(updateTimeRunnable);

        Button btnGoLogin;
        btnGoLogin = findViewById(R.id.btnGoLogin);

        btnGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Stop the handler when navigating to the LoginActivity
                handler.removeCallbacks(updateTimeRunnable);

                Intent iGoLogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(iGoLogin);
            }
        });

        Button btnGoSignup;
        btnGoSignup = findViewById(R.id.btnGoSignup);

        btnGoSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Stop the handler when navigating to the LoginActivity
                handler.removeCallbacks(updateTimeRunnable);

                Intent iGoSignup = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(iGoSignup);
            }
        });

        Button btnGoNotifications;
        btnGoNotifications = findViewById(R.id.btnGoNotifications);

        btnGoNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check and request the permission dynamically
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, 1);
                } else {
                    // Permission already granted, show notifications
                    showNotifications();
                }
            }
        });
    }

    private void createNotificationChannel() {
        // Create a notification channel (for Android 8.0 and higher)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

            // Add a log statement
            Log.d("Notification", "Notification channel created");
        }
    }

    private void showNotifications() {
        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_notifications_24)
                .setContentTitle("Friendly Notification")
                .setContentText("We hope that you're enjoying our app :) ")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Show the notification
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        NotificationManagerCompat.from(this).notify(1, builder.build());
    }


    private void displayDateTime() {
        // Get current time, date, and day
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.getDefault());

        String currentTime = timeFormat.format(calendar.getTime());
        String currentDate = dateFormat.format(calendar.getTime());
        String currentDay = dayFormat.format(calendar.getTime());

        // Display in TextViews
        txtTime.setText(currentTime);
        txtDate.setText(currentDate);
        txtDay.setText(currentDay);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Stop the handler when the activity is destroyed to avoid memory leaks
        handler.removeCallbacks(updateTimeRunnable);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Permission granted, show the notification
            showNotifications();
        }
    }
}
