package com.example.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DriverActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseFirestore db;
    EditText DrnameEt, vehicleEt;
    Button addbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        DrnameEt = findViewById(R.id.DrnameEt);
        vehicleEt = findViewById(R.id.VehicleEt);
        addbutton = findViewById(R.id.addvehicleBtn);

    }

    public void addVehicle(View view){
        Driverinfo();
        Toast.makeText(this, "Info Added", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(DriverActivity.this, DriverMapActivity.class));
    }

    public void Driverinfo(){
        String uid = mAuth.getCurrentUser().getUid();
        String moblieno = mAuth.getCurrentUser().getPhoneNumber();
        String drivername = DrnameEt.getText().toString();
        String vehicleno = vehicleEt.getText().toString();
        Map<String , String> driver = new HashMap<>();
        driver.put("Driver Name",drivername);
        driver.put("Mobile Number", moblieno);
        driver.put("UID", uid);
        driver.put("Vehicle Number", vehicleno);
        db.collection("Drivers").add(driver);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mAuth.signOut();
        startActivity(new Intent(DriverActivity.this, MainActivity.class));
    }
}
