package com.example.whatsap.Login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.LeakedClosableViolation;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.whatsap.ChatActivity;
import com.example.whatsap.MainActivity;
import com.example.whatsap.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Login extends AppCompatActivity {

    EditText edt_phone, edt_password;
    Button btn_login;
    TextView tv_register;
    DatabaseReference mdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_password = findViewById(R.id.edt_password);
        edt_phone    = findViewById(R.id.edt_phone);

        btn_login    = findViewById(R.id.btn_login);

        tv_register  = findViewById(R.id.tv_register);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonLongin();
            }
        });
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRegister();
            }
        });

    }

    private void handleRegister() {
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }

    @SuppressLint("NewApi")
    private void handleButtonLongin() {
        if(edt_password.length() == 0 ){
            edt_password.setError("Banj phai nhap pasword");
        }else if (edt_phone.length() == 0 ){
            edt_phone.setError("Ban chua nhap so dien thoai");
        }else{
            handleLogin();
        }
    }
    private void handleLogin() {
        DatabaseReference currentUserDb = FirebaseDatabase.getInstance().getReference().child("user").child(edt_phone.getText().toString());

        currentUserDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null){

                   User user = dataSnapshot.getValue(User.class);
                    if (user.getmPassword().equals(edt_password.getText().toString())){
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        intent.putExtra("phoneUser",edt_phone.getText().toString());
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login.this,"Mật khẩu sai",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(Login.this,"Tài khoản không tồn tại",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
   }
}
