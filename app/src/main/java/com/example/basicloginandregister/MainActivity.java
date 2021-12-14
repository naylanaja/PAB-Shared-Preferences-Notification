package com.example.basicloginandregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText uname = (EditText) findViewById(R.id.uname);
        final EditText password = (EditText) findViewById(R.id.password);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        Button btnRegister = (Button) findViewById(R.id.btnRegister);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("Notif", "Notif", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = uname.getText().toString();
                String pass = password.getText().toString();

                SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);

                String userDetail = preferences.getString(user + pass +  "data", "Username or Password is incorrect.");
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("display",userDetail);
                editor.commit();

                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "Notif");
                builder.setContentTitle("Login Berhasil");
                builder.setContentText(userDetail);
                builder.setSmallIcon(R.drawable.ic_baseline_check_circle_24);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);
                managerCompat.notify(1,builder.build());

                Intent displayScreen = new Intent(MainActivity.this, DisplayScreen.class);
                startActivity(displayScreen);

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerScreen = new Intent(MainActivity.this, Register.class);
                startActivity(registerScreen);
            }
        });
    }
}