package com.example.whatsap.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.whatsap.Login.Login;
import com.example.whatsap.Login.User;
import com.example.whatsap.R;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.whatsap.MainActivity.phoneUser;

public class fragment extends Fragment {
    String phone;
    EditText edtname,edtPassword;
    TextView edtphone;
    RadioGroup radioGroup_sex;
    RadioButton radioButtonBoy;
    RadioButton radioButtonGirl;
    Button btnsave;
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        heandle();

    }

    private void hendelebtnSaVe() {
        phone = phoneUser;

        if(edtname.length() == 0){
            edtname.setError("Ban phai nhap ten dang nhap");
        }
        else if(edtphone.length() == 0){
            edtphone.setError("ban chua nhap so dien thoai dang nhap");
        }
        else if(edtPassword.length() == 0){
            edtPassword.setError("ban chua nhap password dang nhap");

        }
        else {
            String sex;
            if(radioButtonBoy.isChecked()){
                sex = radioButtonBoy.getText().toString();
            }else{
                sex = radioButtonGirl.getText().toString();
            }

            DatabaseReference myRef = database.getReference("user");
            User user = new User(phone,edtname.getText().toString(),edtPassword.getText().toString(),sex,"");
            myRef.child(phone).setValue(user);
            //Toast.makeText(getApplication(),"ban da dan ky thanh cong",Toast.LENGTH_LONG).show();
            startActivity(new Intent(getContext(), Login.class));
        }



    }

    private void heandle() {
        phone = phoneUser;
        Log.d("aaaaaaaap",phone);

        DatabaseReference currentUserDb = FirebaseDatabase.getInstance().getReference().child("user").child(phone);

        currentUserDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {

                    User user = dataSnapshot.getValue(User.class);
                    Log.d("aaaaaaaa",user.getmPhone());
                    edtname.setText(user.getmName());
                    edtphone.setText(user.getmPhone());
                    edtPassword.setText(user.getmPassword());
                    if(user.getmSex().equals("Nam")){
                        radioButtonBoy.setChecked(true);
                    }else {
                        radioButtonGirl.setChecked(true);
                    }


                } else {
                    //Toast.makeText(fragment.this,"Tài khoản không tồn tại",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment,null);
        edtname = view.findViewById(R.id.name);
        edtphone = view.findViewById(R.id.phone);
        edtPassword = view.findViewById(R.id.edt_password_register);
        radioGroup_sex   = view.findViewById(R.id.rd_group);
        radioButtonBoy   = view.findViewById(R.id.rBtn_nam);
        radioButtonGirl  = view.findViewById(R.id.rBtn_nu);
        btnsave = view.findViewById(R.id.save);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hendelebtnSaVe();
            }
        });
        return view;


    }
}
