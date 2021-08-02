package com.aasath.aasath;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aasath.aasath.Model.Users;
import com.aasath.aasath.Prevelent.Prevelent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

import static com.aasath.aasath.Prevelent.Prevelent.UserPasswordKey;
import static com.aasath.aasath.Prevelent.Prevelent.UserPhoneKey;

public class MainActivity extends AppCompatActivity {

    private Button joinNewButton, loginButton;
    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        joinNewButton = (Button) findViewById(R.id.main_join_now_btn);
        loginButton = (Button) findViewById(R.id.main_login_now_btn);
        loadingBar = new ProgressDialog(this);


        Paper.init(this);


        loginButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        joinNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        String UserPhoneKey= Paper.book().read(Prevelent.UserPhoneKey);
        String UserPasswordKey= Paper.book().read(Prevelent.UserPasswordKey);

        if (UserPhoneKey != "" && UserPasswordKey != "") {
            if (!TextUtils.isEmpty(UserPhoneKey) && !TextUtils.isEmpty(UserPasswordKey)) {
                AllowAccess(UserPhoneKey, UserPasswordKey);

                loadingBar.setTitle("Already login");
                loadingBar.setMessage("Please wait,. ");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();


            }
        }
    }


    private void AllowAccess (String phone,String password){
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child("Users").child(phone).exists()) {

                    Users usersData = dataSnapshot.child("Users").child(phone).getValue(Users.class);


                    if (usersData.getPhone().equals(phone)) {

                        if (usersData.getPassword().equals(password)) {
                            Toast.makeText(MainActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();

                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(intent);


                        } else {
                            Toast.makeText(MainActivity.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                        }
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Account with this" + phone + "Number do not exits", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }
}








