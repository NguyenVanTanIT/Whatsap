package com.example.whatsap;

import android.Manifest;
import android.content.Intent;

import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.view.View;

import com.example.whatsap.Login.Login;
import com.example.whatsap.Login.User;
import com.example.whatsap.fragment.fragment;
import com.example.whatsap.fragment.fragment_frend;
import com.example.whatsap.fragment.fragment_sms;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toobar;
    TabLayout tabLayout;
    ViewPager viewpager;
    public static ArrayList<User> contactList = new ArrayList<>();
    public static  String phoneUser;


    private  int[] tabIcon = {
            R.drawable.chat,
            R.drawable.home,
            R.drawable.like,
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toobar = findViewById(R.id.toobar);
        setSupportActionBar(toobar);
        toobar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toobar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { startActivity(new Intent(getApplicationContext(), Login.class)); }});

        tabLayout = findViewById(R.id.tabs);
        viewpager = findViewById(R.id.viewpager);

        setupViewPager(viewpager);
        tabLayout.setupWithViewPager(viewpager);
        setupIcon();

        Intent intent = this.getIntent();
        phoneUser = intent.getStringExtra("phoneUser");

        getPermissions();
        getContactList();
    }
    private void getContactList(){
    //  String ISOPrefix = getCountryISO();

        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null);
        while(phones.moveToNext()){
            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phone = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            phone = phone.replace(" ", "");
            phone = phone.replace("-", "");
            phone = phone.replace("(", "");
            phone = phone.replace(")", "");
            phone = phone.replace("+", "");
            if(phone.charAt(0)=='8'&&phone.charAt(1)=='4') {
                phone = phone.substring(2, phone.length());
                phone = '0'+phone;
            }
            User mContact = new User(phone, name);
            contactList.add(mContact);
        }
}
    private void getPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.WRITE_CONTACTS, Manifest.permission.READ_CONTACTS}, 1);
        }
    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment( new fragment_sms(),"Nhắn tin");
        adapter.addFragment(new fragment_frend(), "Danh bạ");
        adapter.addFragment(new fragment(), "Cá nhân");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
    }
    private void setupIcon() {
        tabLayout.getTabAt(0).setIcon(tabIcon[0]);
        tabLayout.getTabAt(1).setIcon(tabIcon[1]);
        tabLayout.getTabAt(2).setIcon(tabIcon[2]);
    }
    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
