package com.aasath.aasath;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aasath.aasath.Prevelent.Prevelent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ConfirmFianlOrderActivity extends AppCompatActivity


{
    private EditText nameEditText,phoneEditText,addressEditText,cityEditText;
    private Button confirmOrderbtn;
    private String totalAmount="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_fianl_order);

        totalAmount=getIntent().getStringExtra("Total Price");

        confirmOrderbtn=(Button)findViewById(R.id.confirm_final_order_btn);

        nameEditText=(EditText)findViewById(R.id.shipmentname);
        phoneEditText=(EditText)findViewById(R.id.shipmentphone);
        addressEditText=(EditText)findViewById(R.id.shipmentHome);
        cityEditText=(EditText)findViewById(R.id.shipmentCity);
        confirmOrderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Check();
                
            }
        });

    }

    private void Check() {
        if(TextUtils.isEmpty(nameEditText.getText().toString()))
        {
            Toast.makeText(this, "Please provide your full name", Toast.LENGTH_SHORT).show();
            
        }
        else if(TextUtils.isEmpty(phoneEditText.getText().toString()))
        {
            Toast.makeText(this, "Please provide your Phone number", Toast.LENGTH_SHORT).show();

        }
        else if(TextUtils.isEmpty(addressEditText.getText().toString()))
        {
            Toast.makeText(this, "Please provide your Address", Toast.LENGTH_SHORT).show();

        }
        else if(TextUtils.isEmpty(cityEditText.getText().toString()))
        {
            Toast.makeText(this, "Please provide your City Name", Toast.LENGTH_SHORT).show();

        }
        else{
            ConfirmOrder();
        }
   
   
    }

    private void ConfirmOrder()
    {
        final String saveCurrentDate,saveCurrentTime;
        Calendar calForDate=Calendar.getInstance();
        SimpleDateFormat currentDate=new SimpleDateFormat("MMM dd,YYYY");
        saveCurrentDate=currentDate.format(calForDate.getTime());


        SimpleDateFormat currentTime=new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime=currentDate.format(calForDate.getTime());


        final DatabaseReference orderRef= FirebaseDatabase.getInstance().getReference().child("Orders")
                .child(Prevelent.currentOnlineUser.getPhone());
        HashMap<String, Object>ordermap=new HashMap<>();
        ordermap.put("totalAmount",totalAmount);
        ordermap.put("name",nameEditText.getText().toString());
        ordermap.put("phone",phoneEditText.getText().toString());
        ordermap.put("address",addressEditText.getText().toString());
        ordermap.put("city",cityEditText.getText().toString());
        ordermap.put("date",saveCurrentDate);
        ordermap.put("time",saveCurrentTime);
        ordermap.put("state","Not shipped");

        orderRef.updateChildren(ordermap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    FirebaseDatabase.getInstance().getReference().child("Cart List")
                            .child("User View")
                            .child(Prevelent.currentOnlineUser.getPhone()).removeValue().
                            addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if(task.isSuccessful()){

                                        Toast.makeText(ConfirmFianlOrderActivity.this, "Your final order has placed sucessfully", Toast.LENGTH_SHORT).show();

                                        Intent intent=new Intent(ConfirmFianlOrderActivity.this,HomeActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();
                                    }

                                }
                            });
                }
            }
        });


    }


}