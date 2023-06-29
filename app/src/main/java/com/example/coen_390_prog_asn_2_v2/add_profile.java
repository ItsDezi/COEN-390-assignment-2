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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link add_profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class add_profile extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText first_name;
    EditText last_name;
    EditText student_id;
    EditText gpa;
    AppCompatButton cancel;
    AppCompatButton save;


    public add_profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment add_profile.
     */
    // TODO: Rename and change types and number of parameters
    public static add_profile newInstance(String param1, String param2) {
        add_profile fragment = new add_profile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
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
                LocalDateTime temp= java.time.LocalDateTime.now();
                Profile new_prof = new Profile(Integer.valueOf(ID), FN, LN, GPA, temp.getYear(), temp.getMonthValue(),temp.getDayOfMonth(), temp.getHour(), temp.getMinute());
                Access new_acc = new Access(Integer.valueOf(ID), "Created",temp.getYear(), temp.getMonthValue(),temp.getDayOfMonth(), temp.getHour(), temp.getMinute(), temp.getSecond());
                databaseClass db = databaseClass.getInstance(getContext());
                db.profileDao().insertAll(new_prof);
                db.accessDao().insertAll(new_acc);
                ((MainActivity) requireActivity()).profileArray = db.profileDao().getAll().toArray(new Profile[0]);
                ((MainActivity) requireActivity()).arrayAdapter.notifyDataSetChanged();
                ((MainActivity) requireActivity()).populateList();
                dismiss();

            }
        });
        return view;
        //return inflater.inflate(R.layout.fragment_add_profile, container, false);
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