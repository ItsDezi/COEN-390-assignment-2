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
import android.widget.Toast;

import com.example.coen_390_prog_asn_2_v2.database.databaseClass;
import com.example.coen_390_prog_asn_2_v2.database.entity.Access;
import com.example.coen_390_prog_asn_2_v2.database.entity.Profile;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDateTime;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;

    ArrayAdapter<String> arrayAdapter;
    FloatingActionButton add;// = findViewById(R.id.floatingActionButton);
    boolean viewToggle = false;
    String byID = "By ID";
    String byName = " By Name";
    ListView profilesList;
    Profile[] profileArray;
    List<Profile> list_of_profiles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profilesList = findViewById(R.id.profilesList);
        add = findViewById(R.id.floatingActionButton);
        databaseClass db = databaseClass.getInstance(getApplicationContext());
        profileArray = db.profileDao().getAll().toArray(new Profile[0]);
        //populateList();
        String[] formatted_data = new String[profileArray.length];
        if(viewToggle == false) {
            profileArray = sort_names(profileArray);
            for (int i = 0; i < profileArray.length; i++) {
                formatted_data[i] = i + 1 + ". " + profileArray[i].last_name + ", " + profileArray[i].first_name;
            }
        }
        else
        {
            profileArray = sort_id_numbers(profileArray);
            for (int i = 0; i < profileArray.length; i++) {
                formatted_data[i] = i + 1 + ". " + profileArray[i].ProfileKey;
            }
        }
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, formatted_data);
        profilesList.setAdapter(arrayAdapter);
        profilesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LocalDateTime tempt= java.time.LocalDateTime.now();
                Profile temp = profileArray[position];
                Access new_acc = new Access(temp.ProfileKey, "Opened",tempt.getYear(), tempt.getMonthValue(),tempt.getDayOfMonth(), tempt.getHour(), tempt.getMinute(), tempt.getSecond());
                db.accessDao().insertAll(new_acc);
                //Toast.makeText(MainActivity.this, "YAY!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra("profileKey", temp.ProfileKey);
                startActivity(intent);
            }
        });


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        toolbar.setSubtitle(profileArray.length + " profiles, by surname");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//open dialog fragment
                add_profile dialog = new add_profile();
                dialog.show(getSupportFragmentManager(), "addProfile");
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.toggleProfileView)
        {
            viewToggle = !viewToggle;

        }
        String[] formatted_data = new String[profileArray.length];
        //MenuItem toggle = findViewById(R.id.toggleProfileView);
        if(viewToggle == false) {
            toolbar.setSubtitle(profileArray.length + " profiles, by surname");
            item.setTitle(byID);
            profileArray = sort_names(profileArray);
            for (int i = 0; i < profileArray.length; i++) {
                formatted_data[i] = i + 1 + ". " + profileArray[i].last_name + ", " + profileArray[i].first_name;
            }
        }
        else
        {
            toolbar.setSubtitle(profileArray.length + " profiles, by student ID");
            item.setTitle(byName);
            profileArray = sort_id_numbers(profileArray);
            for (int i = 0; i < profileArray.length; i++) {
                formatted_data[i] = i + 1 + ". " + profileArray[i].ProfileKey;
            }
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, formatted_data);
        profilesList.setAdapter(arrayAdapter);
        return super.onOptionsItemSelected(item);
    }
    Profile[] sort_names(Profile profiles[])
    {
        Profile temp;
        for (int i = 0; i < profiles.length; i++) {
            for (int j = i + 1; j < profiles.length; j++) {
                if (profiles[i].last_name.toLowerCase().compareTo(profiles[j].last_name.toLowerCase()) > 0) {
                    temp = profiles[i];
                    profiles[i] = profiles[j];
                    profiles[j] = temp;
                } else if (profiles[i].last_name.toLowerCase().compareTo(profiles[j].last_name.toLowerCase()) == 0) {
                    if(profiles[i].first_name.toLowerCase().compareTo(profiles[j].first_name.toLowerCase()) > 0)
                    {
                        temp = profiles[i];
                        profiles[i] = profiles[j];
                        profiles[j] = temp;
                    }

                }

            }
        }
        return profiles;
    }

    Profile[] sort_id_numbers(Profile[] profiles)
    {
        Profile temp;
        for(int i = 0; i < profiles.length; i++)
        {
            for(int j = i+ 1; j < profiles.length; j++)
            {
                if (profiles[i].ProfileKey > profiles[j].ProfileKey) {
                    temp = profiles[i];
                    profiles[i] = profiles[j];
                    profiles[j] = temp;
                }            }
        }
        return profiles;
    }

    void populateList()
    {
        String[] formatted_data = new String[profileArray.length];
        if(viewToggle == false) {
            profileArray = sort_names(profileArray);
            for (int i = 0; i < profileArray.length; i++) {
                formatted_data[i] = i + 1 + ". " + profileArray[i].last_name + ", " + profileArray[i].first_name;
            }
        }
        else
        {
            profileArray = sort_id_numbers(profileArray);
            for (int i = 0; i < profileArray.length; i++) {
                formatted_data[i] = i + 1 + ". " + profileArray[i].ProfileKey;
            }
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, formatted_data);
        profilesList.setAdapter(arrayAdapter);
    }

}