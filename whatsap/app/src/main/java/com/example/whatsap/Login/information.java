package com.example.whatsap.Login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.whatsap.R;

public class information extends AppCompatActivity {

    TextView tvNameInformation,tvPhoneInformation,tvSexInformation;
    ImageView imageViewAvatInformation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        tvNameInformation = findViewById(R.id.tv_name_information);
        tvPhoneInformation = findViewById(R.id.tv_phone_information);
        tvSexInformation   = findViewById(R.id.tv_sex_information);
        imageViewAvatInformation = findViewById(R.id.image_avata_information);
        
    }
}
