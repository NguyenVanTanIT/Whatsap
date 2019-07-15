package com.example.whatsap;

import android.Manifest;
import android.content.Intent;

import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.whatsap.Login.Login;
import com.example.whatsap.Login.User;
import com.example.whatsap.fragment.fragment;
import com.example.whatsap.fragment.fragment_frend;
import com.example.whatsap.fragment.fragment_sms;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {
    Toolbar toobar;
    TabLayout tabLayout;
    ViewPager viewpager;
    final ArrayList<User> contactList = new ArrayList<>();
    DatabaseReference mdata;

    private  int[] tabIcon = {
            R.drawable.chat,
            R.drawable.home,
            R.drawable.like,
    };
    @Override
//    public ArrayList<User> getContacList(){
//        return contactList;
//    }
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
            //getUserDetails(mContact);
            for (User user:contactList){
                Log.d("44",user.getmPhone());
                Log.d("44",user.getmName());
            }
            final ArrayList<User> phone1 = new ArrayList<>();


//            mdata = FirebaseDatabase.getInstance().getReference();
//            mdata.child("user").addChildEventListener(new ChildEventListener() {
//                @Override
//                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                    User user = dataSnapshot.getValue(User.class);
//                    //  phone1.add(user);
//                    String b = user.getmPhone();
//                    Log.d("bbbbbbbbbbbb",b);
//                    for (int i = 0 ; i < contactList.size(); i++){
//                        String a = contactList.get(i).getmPhone();
//                        //Log.d("aaaaaaaaaa",a);
//                        if (a.equals(b)){
//                            Log.d("111111111111111","111111111111111111111111111");
//                            Toast.makeText(getApplication(),contactList.get(i).getmName() +" "+ a, LENGTH_LONG).show();
//                            break;
//                        }
//                    }
//
//                }
//
//                @Override
//                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//                }
//
//                @Override
//                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//                }
//
//                @Override
//                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//                }
//            });


        }
}
    private void getPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.WRITE_CONTACTS, Manifest.permission.READ_CONTACTS}, 1);
        }
    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment( new fragment_sms(),"SMS");
        adapter.addFragment(new fragment_frend(), "Frend");
        adapter.addFragment(new fragment(), "Three");
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
