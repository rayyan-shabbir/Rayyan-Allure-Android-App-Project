package com.example.rayyanallureapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Dialog;
import android.widget.EditText;
import android.widget.ImageView;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import com.example.rayyanallureapp.Database.DatabaseActivity;
import com.example.rayyanallureapp.Fragments.DynamicFragmentActivity;
import com.example.rayyanallureapp.Fragments.StaticFragmentActvity;
import com.example.rayyanallureapp.R;
import com.example.rayyanallureapp.RetrofitAPICall.RetrofitGetActivity;
import com.example.rayyanallureapp.RetrofitAPICall.RetrofitPostActivity;

public class HomeActivity extends AppCompatActivity {

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Intent fromAct = getIntent();
        name = fromAct.getStringExtra("Name");
        //String title = fromAct.getStringExtra("title");

        TextView txtData;
        txtData = findViewById(R.id.txtData);

        txtData.setText("Hey, " + name);
//        String title = "Home";

        Button btnGoMain;

        btnGoMain = findViewById(R.id.btnGoMain);

        btnGoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGoMain = new Intent(HomeActivity.this, MainActivity.class);

                startActivity(iGoMain);
            }
        });

        Button btnGoNotify = findViewById(R.id.btnGoNotify);

        btnGoNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotification();
            }
        });

        Button btnGoCalc;

        btnGoCalc = findViewById(R.id.btnGoCalc);

        btnGoCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGoCalc = new Intent(HomeActivity.this, CalculatorActivity.class);

                startActivity(iGoCalc);
            }
        });

        Button btnGoContacts = findViewById(R.id.btnGoContacts);

        btnGoContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGoContacts = new Intent(HomeActivity.this, ContactActivity.class);

                startActivity(iGoContacts);
            }
        });


        Button btnGoForm = findViewById(R.id.btnGoForm);

        btnGoForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGoForm = new Intent(HomeActivity.this, AddPlacesActivity.class);

                startActivity(iGoForm);
            }
        });

        Button btnGoPlaces = findViewById(R.id.btnGoPlaces);

        btnGoPlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGoPlaces = new Intent(HomeActivity.this, StaticFragmentActvity.class);

                startActivity(iGoPlaces);
            }
        });

        Button btnGoProfile = findViewById(R.id.btnGoProfile);

        btnGoProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGoProfile = new Intent(HomeActivity.this, DynamicFragmentActivity.class);

                startActivity(iGoProfile);
            }
        });

        Button btnGoDb = findViewById(R.id.btnGoDb);

        btnGoDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGoDb = new Intent(HomeActivity.this, DatabaseActivity.class);

                startActivity(iGoDb);
            }
        });

        Button btnGoAPI = findViewById(R.id.btnGoAPI);

        btnGoAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGoAPI = new Intent(HomeActivity.this, RetrofitGetActivity.class);

                startActivity(iGoAPI);
            }
        });

        Button btnGoAPIPost = findViewById(R.id.btnGoAPIPost);

        btnGoAPIPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGoAPIPost = new Intent(HomeActivity.this, RetrofitPostActivity.class);

                startActivity(iGoAPIPost);
            }
        });

//        getActionBar().setTitle(title);
        Button btnGoCustomDialog = findViewById(R.id.btnGoCustomDialog);

        btnGoCustomDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog();
            }
        });
    }

    // ... (existing code)

    // Update the showNotification method
    private void showNotification() {
        // Notification channel ID is required for Android O and above
        String channelId = "default_channel_id";
        String channelName = "User Notification";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Check if the device is running Android Oreo or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
            notificationManager.createNotificationChannel(channel);
        }

        // Create an explicit intent for an activity in your app
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        // Use ImmutablePendingIntent to handle PendingIntent
        PendingIntent pendingIntent;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent.getActivity(
                    this,
                    0,
                    intent,
                    PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
        } else {
            pendingIntent = PendingIntent.getActivity(
                    this,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
        }

        // Set the notification content
        Bitmap userIcon = BitmapFactory.decodeResource(getResources(), R.drawable.baseline_person_pin_24);
        Notification notification = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.baseline_notifications_24)
                .setContentTitle("Good Morning!")
                .setContentText("Currently " + name+ " using our app :)")
                .setLargeIcon(userIcon)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .build();

        // Show the notification
        notificationManager.notify(0, notification);
    }

    private void showCustomDialog() {
        // Create custom dialog
        Dialog customDialog = new Dialog(this);
        customDialog.setContentView(R.layout.custom_dialog_layout);

        // Initialize dialog views
        ImageView dialogIcon = customDialog.findViewById(R.id.dialogIcon);
        TextView dialogTitle = customDialog.findViewById(R.id.dialogTitle);
        TextView dialogMessage = customDialog.findViewById(R.id.dialogMessage);
        TextView dialogMessage2 = customDialog.findViewById(R.id.dialogMessage2);
        EditText dialogInput = customDialog.findViewById(R.id.dialogInput);
        Button dialogCancelButton = customDialog.findViewById(R.id.dialogCancelButton);
        Button dialogOKButton = customDialog.findViewById(R.id.dialogOKButton);

        // Customize dialog content
        dialogIcon.setImageResource(R.drawable.prog);
        dialogTitle.setText("Fact of the day!");
        dialogMessage.setText("\"Programming languages are not only a means to instruct computers but also a powerful tool for expressing abstract concepts and solving complex problems. They serve as a universal language that allows individuals from diverse backgrounds and cultures to collaborate and create innovative solutions that shape the future of technology.\"");
        dialogMessage2.setText("Was this information useful?");
        // Set button click listeners
        dialogCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cancel button clicked
                customDialog.dismiss();
            }
        });

        dialogOKButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // OK button clicked
                String inputText = dialogInput.getText().toString();
                // Process the inputText as needed
                customDialog.dismiss();
            }
        });

        // Show the custom dialog
        customDialog.show();
    }
}