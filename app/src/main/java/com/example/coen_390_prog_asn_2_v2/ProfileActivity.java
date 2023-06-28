package com.example.coen_390_prog_asn_2_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.coen_390_prog_asn_2_v2.database.databaseClass;
import com.example.coen_390_prog_asn_2_v2.database.entity.Profile;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    Toolbar toolbar;

    ArrayAdapter<String> arrayAdapter;
    TextView lastName;
    databaseClass db = databaseClass.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //databaseClass db = databaseClass.getInstance(getApplicationContext());


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        lastName = findViewById(R.id.name);
        lastName.setText("");
        Intent intent = getIntent();
        int profileKey = intent.getIntExtra("profile_key", 0);
        if (profileKey != 0)
        {
            db = databaseClass.getInstance(this);
            Profile profile = db.profileDao().findById(profileKey);
            lastName.setText(profile.ProfileKey);
        }
    }





}