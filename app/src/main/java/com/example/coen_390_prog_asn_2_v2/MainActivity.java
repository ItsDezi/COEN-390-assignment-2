package com.example.coen_390_prog_asn_2_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.coen_390_prog_asn_2_v2.database.databaseClass;
import com.example.coen_390_prog_asn_2_v2.database.entity.Profile;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    protected RecyclerView profileRecycleView;
    protected ProfileRecyclerViewAdapter profileRecyclerViewAdapter;
    public databaseClass db;

    ArrayAdapter<String> arrayAdapter;
    FloatingActionButton add;// = findViewById(R.id.floatingActionButton);
    boolean viewToggle = false;
    String byID = "By ID";
    String byName = " By Name";
    //ListView profilesList;
    //Profile[] profileArray;
   // List<Profile> list_of_profiles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //profilesList = findViewById(R.id.profilesList);
        add = findViewById(R.id.floatingActionButton);
        db = databaseClass.getInstance(getApplicationContext());
        setUpRecyclerView();
        //profileArray = db.profileDao().getAll().toArray(new Profile[0]);
        //populateList();
        /*String[] formatted_data = new String[profileArray.length];
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
        profilesList.setAdapter(arrayAdapter);*/
        //db.profileDao().insertAll(new Profile(0,"Bohn","Zoe",3.24, 2023, 06, 26));
        //List profiles = db.profileDao().getAll();

        //list_of_profiles = db.profileDao().getAll();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        Profile [] p = db.profileDao().getAll().toArray(new Profile[0]);
        toolbar.setSubtitle(p.length + " profiles, by surname");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//open dialog fragment
                add_profile dialog = new add_profile();
                dialog.show(getSupportFragmentManager(), "addProfile");
            }
        });
        //}
    }
    protected void setUpRecyclerView()
    {
        List<Profile> profiles = db.profileDao().getAll();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        profileRecyclerViewAdapter = new ProfileRecyclerViewAdapter(profiles);
        profileRecycleView = findViewById(R.id.profileRecyclerView);
        profileRecycleView.setLayoutManager(linearLayoutManager);
        profileRecycleView.setAdapter(profileRecyclerViewAdapter);
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
            this.profileRecyclerViewAdapter.showByID = viewToggle;
        }
        profileRecyclerViewAdapter.notifyDataSetChanged();
        Profile [] p = db.profileDao().getAll().toArray(new Profile[0]);
        if(viewToggle == false)
        {
            item.setTitle(byID);
            toolbar.setSubtitle(p.length + " profiles, by surname");
        }
        else {
            item.setTitle(byName);
            toolbar.setSubtitle(p.length + " profiles, by student ID");

        }
        /*String[] formatted_data = new String[profileArray.length];
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
        profilesList.setAdapter(arrayAdapter);*/
        return super.onOptionsItemSelected(item);
    }
    /*Profile[] sort_names(Profile profiles[])
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

    /*void populateList()
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
        //profilesList.setAdapter(arrayAdapter);
    }*/

}