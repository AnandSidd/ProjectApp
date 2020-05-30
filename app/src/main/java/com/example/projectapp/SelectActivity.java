package com.example.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SelectActivity extends AppCompatActivity {

    Button custBtn, driverBtn, ContBtn;
    EditText fnameET, lnameET,ageET;
    LinearLayout customerInfo;
    FirebaseUser currentUser;
    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        custBtn = findViewById(R.id.CustBtn);
        driverBtn = findViewById(R.id.DriverBtn);
        customerInfo = findViewById(R.id.CustInfo);
        ContBtn = findViewById(R.id.buttonContinue);
        fnameET = findViewById(R.id.fnameeditText);
        lnameET = findViewById(R.id.LnameeditText);
        ageET = findViewById(R.id.ageeditText);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        custBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customerInfo.setVisibility(View.VISIBLE);
                custBtn.setVisibility(View.INVISIBLE);
                driverBtn.setVisibility(View.INVISIBLE);
                addtodb();
            }
        });
        driverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addtodb();
                startActivity(new Intent(SelectActivity.this, DriverActivity.class));
            }
        });

    }

    public void ContinuePressed(View view){
        SetCustomerInfo();
        startActivity(new Intent(SelectActivity.this, CustMapActivity.class));
    }

    public void SetCustomerInfo(){
        String fname = fnameET.getText().toString();
        String lname = lnameET.getText().toString();
        String age = ageET.getText().toString();
        String moblieNo = currentUser.getPhoneNumber();
        String uid = currentUser.getUid();

        CustomerInfo customerInfo = new CustomerInfo(fname, lname, moblieNo, age, uid);
        db.collection("Customers").add(customerInfo);
    }
    public void addtodb(){
        String uid = currentUser.getUid();
        String moblieNo = currentUser.getPhoneNumber();
        Map<String, String> user = new HashMap<>();
        user.put("UID", uid);
        user.put("Mobile Number", moblieNo);
        db.collection("Users").document(uid).set(user);
    }
}
