package com.example.enrollmentapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterFragment extends Fragment {
    EditText first_name,last_name,gender,state,country,dob,hometown,phone_number,telephone_number;
    Button register;
    DatabaseReference reference;
    UserDetails userDetails;
    long id=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_registration, container, false);
        initialise(rootView);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                id=snapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        userDetails=new UserDetails();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDetails.setFirst_name(first_name.getText().toString());
                userDetails.setLast_name(last_name.getText().toString());
                userDetails.setDob(dob.getText().toString());
                userDetails.setGender(gender.getText().toString());
                userDetails.setCountry(country.getText().toString());
                userDetails.setState(state.getText().toString());
                userDetails.setHometown(hometown.getText().toString());
                userDetails.setPhone_number(phone_number.getText().toString());
                userDetails.setTelephone_number(telephone_number.getText().toString());
                reference.child(phone_number.getText().toString()).setValue(userDetails);
                Toast.makeText(getContext(),"User Registered !",Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }

    public void initialise(View rootView){
        first_name=rootView.findViewById(R.id.first_name);
        last_name=rootView.findViewById(R.id.last_name);
        gender=rootView.findViewById(R.id.gender);
        state=rootView.findViewById(R.id.state);
        country=rootView.findViewById(R.id.country);
        dob=rootView.findViewById(R.id.dob);
        hometown=rootView.findViewById(R.id.hometown);
        phone_number=rootView.findViewById(R.id.phone_number);
        telephone_number=rootView.findViewById(R.id.telephone_number);
        register=rootView.findViewById(R.id.register_btn);
        reference= FirebaseDatabase.getInstance().getReference().child("User");
    }
}
