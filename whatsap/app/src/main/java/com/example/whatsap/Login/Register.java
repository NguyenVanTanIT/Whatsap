package com.example.whatsap.Login;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.whatsap.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    EditText edtNameRegister,edtPhoneRegister,edtPassword;
    RadioGroup radioGroup_sex;
    RadioButton radioButtonBoy;
    RadioButton radioButtonGirl;
    Button btnSend;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edtNameRegister = findViewById(R.id.edt_name_register);
        edtPassword     = findViewById(R.id.edt_password_register);
        edtPhoneRegister = findViewById(R.id.edt_phone_register);
        radioGroup_sex   = findViewById(R.id.rd_group);
        radioButtonBoy   = findViewById(R.id.rBtn_nam);
        radioButtonGirl  = findViewById(R.id.rBtn_nu);
        btnSend          = findViewById(R.id.btn_send_register);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRegister();
            }
        });
    }


    private void handleRegister() {

        if(edtNameRegister.length() == 0){
           edtNameRegister.setError("Ban phai nhap ten dang nhap");
        }
        else if(edtPhoneRegister.length() == 0){
            edtPhoneRegister.setError("ban chua nhap so dien thoai dang nhap");
        }
        else if(edtPassword.length() == 0){
            edtPassword.setError("ban chua nhap password dang nhap");

        }else if (radioButtonGirl.isChecked() == false && radioButtonBoy.isChecked() == false){
           Toast.makeText(getApplication(),"ban chua chon gioi tinh dang nhap",Toast.LENGTH_LONG).show();
        }
        else {
            String sex;
           if(radioButtonBoy.isChecked()){
               sex = radioButtonBoy.getText().toString();
           }else{
               sex = radioButtonGirl.getText().toString();
           }
            String phone = edtPhoneRegister.getText().toString();
            DatabaseReference myRef = database.getReference("user");
            User user = new User(phone,edtNameRegister.getText().toString(),edtPassword.getText().toString(),sex,"");
            myRef.child(phone).setValue(user);

            Toast.makeText(getApplication(),"ban da dan ky thanh cong",Toast.LENGTH_LONG).show();
        }

    }
}
