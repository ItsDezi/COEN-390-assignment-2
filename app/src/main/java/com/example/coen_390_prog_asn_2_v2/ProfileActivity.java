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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coen_390_prog_asn_2_v2.database.databaseClass;
import com.example.coen_390_prog_asn_2_v2.database.entity.Profile;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView surname;
    TextView name;
    TextView profileKey;
    TextView gpa;
    TextView creationDate;
    Profile profile;
    Profile[] profileArray;
    List<Profile> list_of_profiles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        surname = findViewById(R.id.surnameTV);
        name = findViewById(R.id.nameTV);
        profileKey = findViewById(R.id.profileKeyTV);
        gpa = findViewById(R.id.gpaTV);
        name = findViewById(R.id.nameTV);
        creationDate = findViewById(R.id.creationDateTV);

        Intent intent = getIntent();
        int profileKeyInt = intent.getIntExtra("profileKey",-1);
        databaseClass db = databaseClass.getInstance(getApplicationContext());
        profileArray = db.profileDao().getAll().toArray(new Profile[0]);
        profile = db.profileDao().findById(profileKeyInt);
        surname.setText(profile.last_name);
        name.setText(profile.first_name);
        profileKey.setText(String.valueOf(profile.ProfileKey));
        gpa.setText(String.valueOf(profile.gpa));
        creationDate.setText(profile.getCreation_year() + "-" + profile.getCreation_month() + "-" + profile.getCreation_day());


        toolbar = (Toolbar) findViewById(R.id.profileToolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    }