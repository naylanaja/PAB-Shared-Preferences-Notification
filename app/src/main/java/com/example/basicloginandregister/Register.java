package com.example.basicloginandregister;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        final EditText userName=(EditText) findViewById(R.id.newUname);
        final EditText email=(EditText) findViewById(R.id.newEmail);
        final EditText password=(EditText) findViewById(R.id.newPassword);
        Button btnRegis = (Button) findViewById(R.id.btnRegis);

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences= getSharedPreferences("MYPREFS", MODE_PRIVATE);
                String newUser = userName.getText().toString();
                String newEmail = email.getText().toString();
                String newPassword = password.getText().toString();

                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(newUser + newPassword + "data" , newUser + "\n" + newEmail);
                editor.commit();

                Intent loginScreen = new Intent(Register.this, MainActivity.class);
                startActivity(loginScreen);
            }
        });
    }
}
