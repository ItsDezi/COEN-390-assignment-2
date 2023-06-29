package com.example.coen_390_prog_asn_2_v2;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coen_390_prog_asn_2_v2.database.databaseClass;
import com.example.coen_390_prog_asn_2_v2.database.entity.Access;
import com.example.coen_390_prog_asn_2_v2.database.entity.Profile;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class add_profile extends DialogFragment {


    private String mParam1;
    private String mParam2;
    EditText first_name;
    EditText last_name;
    EditText student_id;
    EditText gpa;
    AppCompatButton cancel;
    AppCompatButton save;


    public add_profile() {
    }

    public static add_profile newInstance(String param1, String param2) {
        add_profile fragment = new add_profile();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_profile, container);
        first_name = view.findViewById(R.id.dialogFirstName);
        last_name = view.findViewById(R.id.dialogLastName);
        student_id = view.findViewById(R.id.dialogStudentID);
        gpa = view.findViewById(R.id.dialogGPA);
        cancel = view.findViewById(R.id.dialogCancelButton);
        save = view.findViewById(R.id.dialogSaveButton);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ID = Integer.valueOf(student_id.getText().toString());
                String FN = first_name.getText().toString();
                String LN = last_name.getText().toString();
                double GPA = Double.valueOf(gpa.getText().toString());
                //perform checks for valid entries
                if (FN == "" || LN == "") {
                    Toast.makeText(getContext(), "Fields must not be empty.", Toast.LENGTH_SHORT).show();
                    return;
                } else if (GPA < 0 || GPA > 4.3) {
                    Toast.makeText(getContext(), "GPA must be a value between 0 and 4.3.", Toast.LENGTH_SHORT).show();
                    return;
                } else if (ID < 10000000 || ID > 99999999) {
                    Toast.makeText(getContext(), "ID must be 8 digits.", Toast.LENGTH_SHORT).show();
                    return;
                }
                databaseClass db = databaseClass.getInstance(getContext());

                //checking for duplicate Student ID
                if (db.profileDao().findById(ID) != null)
                {
                    Toast.makeText(getContext(), "Error: duplicate student ID", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Checking for special characters
                Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
                Pattern pp = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);

                Matcher nameMatcher = p.matcher(FN);
                Matcher surnameMatcher = pp.matcher(LN);
                boolean nameHasSpecialChar = nameMatcher.find();
                boolean surnameHasSpecialChar = surnameMatcher.find();
                if (nameHasSpecialChar == true)
                {
                    Toast.makeText(getContext(), "name and surname can not contain special characters", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (surnameHasSpecialChar == true)
                {
                    Toast.makeText(getContext(), "name and surname can not contain special characters", Toast.LENGTH_SHORT).show();
                    return;
                }
                //adding data to db and returning to Main Activity
                LocalDateTime temp= java.time.LocalDateTime.now();
                Profile new_prof = new Profile(Integer.valueOf(ID), FN, LN, GPA, temp.getYear(), temp.getMonthValue(),temp.getDayOfMonth(), temp.getHour(), temp.getMinute());
                Access new_acc = new Access(Integer.valueOf(ID), "Created",temp.getYear(), temp.getMonthValue(),temp.getDayOfMonth(), temp.getHour(), temp.getMinute(), temp.getSecond());
                db.profileDao().insertAll(new_prof);
                db.accessDao().insertAll(new_acc);
                ((MainActivity) requireActivity()).profileArray = db.profileDao().getAll().toArray(new Profile[0]);
                ((MainActivity) requireActivity()).arrayAdapter.notifyDataSetChanged();
                ((MainActivity) requireActivity()).populateList();
                dismiss();

            }
        });
        return view;
    }

    public boolean idLengthCheck(String num) {
            int count = num.length();

            if (count == 8) {
            return true;
        } else {
            return false;
        }
    }
}