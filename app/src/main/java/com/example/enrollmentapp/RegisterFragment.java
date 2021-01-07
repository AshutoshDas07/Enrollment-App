package com.example.enrollmentapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class RegisterFragment extends Fragment {
    EditText first_name,last_name,gender,state,country,dob,hometown,phone_number,telephone_number;
    Button register;
    boolean fn_flag,ln_flag, g_flag,s_flag,c_flag,dob_flag,h_flag,p_flag,p2_flag;
    DatabaseReference reference;
    UserDetails userDetails;
    long id=0;
    int year,month,day;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_registration, container, false);
        initialise(rootView);
        reference.orderByChild("timestamp").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                id=snapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        first_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String validate=first_name.getText().toString();
                if(validate.length()==0){
                    fn_flag=false;
                    //Toast.makeText(getContext(),"Field Can't be Empty",Toast.LENGTH_LONG).show();
                }else{
                    fn_flag=true;
                }
            }
        });
        last_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String validate=last_name.getText().toString();
                if(validate.length()==0){
                    ln_flag=false;
                    //Toast.makeText(getContext(),"Field Can't be Empty",Toast.LENGTH_LONG).show();
                }else{
                    ln_flag=true;
                }
            }
        });
        gender.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String validate=gender.getText().toString().toLowerCase();
                if(validate.equals("male")||validate.equals("female")||validate.equals("others")){
                    g_flag=true;
                }else{
                    g_flag=false;
                }
            }
        });
        phone_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                final String validate=phone_number.getText().toString();
                if(validate.length()==10){
                    p2_flag=true;
                }
                DatabaseReference childRef = FirebaseDatabase.getInstance().getReference();
                childRef.child("User");
                childRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.child(validate).exists()){
                            p_flag=false;
                        }else{
                            p_flag=true;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        country.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String validate=country.getText().toString();
                if(validate.length()==0){
                    c_flag=false;
                    //Toast.makeText(getContext(),"Field Can't be Empty",Toast.LENGTH_LONG).show();
                }else{
                    c_flag=true;
                }
            }
        });
        state.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String validate=state.getText().toString();
                if(validate.length()==0){
                    s_flag=false;
                }else{
                    s_flag=true;
                }
            }
        });
        dob.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String validate=dob.getText().toString();
                if(validate.length()==0){
                    dob_flag=false;
                }else{
                    dob_flag=true;
                }
            }
        });
        userDetails=new UserDetails();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fn_flag && ln_flag && dob_flag && g_flag && c_flag && s_flag && p_flag&&p2_flag) {
                    userDetails.setFirst_name(first_name.getText().toString());
                    userDetails.setLast_name(last_name.getText().toString());
                    userDetails.setDob(dob.getText().toString());
                    userDetails.setGender(gender.getText().toString());
                    userDetails.setCountry(country.getText().toString());
                    userDetails.setState(state.getText().toString());
                    userDetails.setHometown(hometown.getText().toString());
                    userDetails.setPhone_number(phone_number.getText().toString());
                    userDetails.setTelephone_number(telephone_number.getText().toString());
                    Long tsTemp = System.currentTimeMillis()/1000;
                    String ts =  tsTemp.toString();
                    userDetails.setTimestamp(ts);
                    reference.child(phone_number.getText().toString()).setValue(userDetails);
                    Toast.makeText(getContext(), "User Registered", Toast.LENGTH_LONG).show();
                }else if (!fn_flag){
                    first_name.setBackgroundResource(R.drawable.wronginputbox_shape);
                    Toast.makeText(getContext(),"First Name Can't be Empty",Toast.LENGTH_LONG).show();
                }else if(!ln_flag){
                    last_name.setBackgroundResource(R.drawable.wronginputbox_shape);
                    Toast.makeText(getContext(),"Last Name Can't be Empty",Toast.LENGTH_LONG).show();
                }else if(!dob_flag){
                    dob.setBackgroundResource(R.drawable.wronginputbox_shape);
                    Toast.makeText(getContext(),"Enter Date",Toast.LENGTH_LONG).show();
                }else if(!g_flag){
                    gender.setBackgroundResource(R.drawable.wronginputbox_shape);
                    Toast.makeText(getContext(),"Only Male, Female or Others Allowed",Toast.LENGTH_LONG).show();
                }else if(!c_flag){
                    country.setBackgroundResource(R.drawable.wronginputbox_shape);
                    Toast.makeText(getContext(),"Enter Country Name",Toast.LENGTH_LONG).show();
                }else if(!s_flag){
                    state.setBackgroundResource(R.drawable.wronginputbox_shape);
                    Toast.makeText(getContext(),"State Can't be Empty",Toast.LENGTH_LONG).show();
                }else if(!p2_flag){
                    phone_number.setBackgroundResource(R.drawable.wronginputbox_shape);
                    Toast.makeText(getContext(),"Enter Valid 10 digit Phone Number",Toast.LENGTH_LONG).show();
                }else if(!p_flag){
                    phone_number.setBackgroundResource(R.drawable.wronginputbox_shape);
                    Toast.makeText(getContext(),"Phone Number Already Exists",Toast.LENGTH_LONG).show();
                }
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
        dob.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                SelectDate();
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void SelectDate() {

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int syear, int smonth, int sdayOfMonth) {
                year=syear;
                month=smonth;
                day=sdayOfMonth;
                dob.setText(new StringBuilder().append(day)
                        .append("-").append(month+1).append("-").append(year));
            }
        },year, month, day);
        datePickerDialog.show();
    }

}
