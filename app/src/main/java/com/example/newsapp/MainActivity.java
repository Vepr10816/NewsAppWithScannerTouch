package com.example.newsapp;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText Login, Password;
    Button SignIn,Registration;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Login = findViewById(R.id.et_email);
        Password = findViewById(R.id.et_password);
        SignIn = findViewById(R.id.sign_in);
        Registration = findViewById(R.id.registration);
        databaseHelper = new DatabaseHelper(this);
        SignIn.setOnClickListener(view -> {
            int roleid = 0;
            Cursor role = databaseHelper.checkLoginPassword(Login.getText().toString(),Password.getText().toString());
            //Login.setText(role.getString(0).toString());
            if(role!=null) {
                while (role.moveToNext()) {
                    roleid = role.getInt(0);
                }
            }
            if (roleid == 1) {
                Intent mainIntent = new Intent(MainActivity.this, AdminPage.class);
                mainIntent.putExtra("user", "1");
                MainActivity.this.startActivity(mainIntent);
                MainActivity.this.finish();
            }
            else if(roleid == 2) {
                Intent mainIntent = new Intent(MainActivity.this, AdminPage.class);
                mainIntent.putExtra("user", "2");
                MainActivity.this.startActivity(mainIntent);
                MainActivity.this.finish();
            }
            else
                Toast.makeText(getApplicationContext(), "Неправильный логин или пароль", Toast.LENGTH_LONG).show();
        });

        Registration.setOnClickListener(view -> {
            Intent mainIntent = new Intent(this, RegistrationPage.class);
            startActivity(mainIntent);
            /*this.finish();*/
        });
    }
}