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
import com.example.coen_390_prog_asn_2_v2.database.entity.Access;
import com.example.coen_390_prog_asn_2_v2.database.entity.Profile;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    ArrayAdapter<String> arrayAdapter;

    List<Access> accessList;
    ListView accessListView;
    Toolbar toolbar;
    TextView surname;
    TextView name;
    TextView profileKey;
    TextView gpa;
    TextView creationDate;
    Profile profile;
    Access[] accessArray;
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

        accessListView = findViewById(R.id.accessListView);

        Intent intent = getIntent();
        int profileKeyInt = intent.getIntExtra("profileKey",-1);
        databaseClass db = databaseClass.getInstance(getApplicationContext());
        accessList = db.accessDao().findById(profileKeyInt);
        accessArray = db.accessDao().findById(profileKeyInt).toArray(new Access[0]);
        profileArray = db.profileDao().getAll().toArray(new Profile[0]);
        profile = db.profileDao().findById(profileKeyInt);
        surname.setText("Surname: " + profile.last_name);
        name.setText("Name: " + profile.first_name);
        profileKey.setText("ID: " + String.valueOf(profile.ProfileKey));
        gpa.setText("GPA: " + String.valueOf(profile.gpa));
        creationDate.setText("Profile Created: " + profile.getCreation_year() + "-" + profile.getCreation_month() + "-" + profile.getCreation_day() + " @ " + profile.getCreation_hour() + ":" + profile.getCreation_minute());
        String[] formatted_data = new String[accessArray.length];
        String[] formatted_data2 = new String[accessArray.length];
        for (int i = 0; i < accessArray.length; i++) {
            formatted_data[i] =  accessArray[i].year + "-" + accessArray[i].month + "-" + accessArray[i].day + " @ " + accessArray[i].hour + ":" + accessArray[i].minute + ":" + accessArray[i].second + " " + accessArray[i].access_type;
        }
        List<String> tempList = Arrays.asList(formatted_data);
        Collections.reverse(tempList);
        formatted_data = tempList.toArray(new String[0]);
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, formatted_data);
        accessListView.setAdapter(arrayAdapter);
        toolbar = (Toolbar) findViewById(R.id.profileToolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalDateTime temp= java.time.LocalDateTime.now();
                Access new_acc = new Access(Integer.valueOf(profile.ProfileKey), "Closed",temp.getYear(), temp.getMonthValue(),temp.getDayOfMonth(), temp.getHour(), temp.getMinute(), temp.getSecond());
                db.accessDao().insertAll(new_acc);
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    }