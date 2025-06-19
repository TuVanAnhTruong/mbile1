package com.example.tuvananhtruong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btnNextpage = findViewById(R.id.btnLogin);
        btnNextpage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText objphone = findViewById(R.id.editTextTextEmailAddress2);
                String txtphone = objphone.getText().toString();

                EditText objpass = findViewById(R.id.editTextNumberPassword2);
                String txtpass = objpass.getText().toString();
                if (txtpass.equals("123") && txtphone.equals("abc")) {
                    Intent it = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(it);
                }
                else{
                    Toast.makeText(LoginActivity.this, "Login failed!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}