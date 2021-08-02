package com.aasath.aasath;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aasath.aasath.Model.products;
import com.aasath.aasath.Prevelent.Prevelent;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.SimpleTimeZone;

public class ProductDetailsActivity extends AppCompatActivity {



    private Button addtocartbtn;
    private ImageView productImage;
    private ElegantNumberButton numberBtn;
    private TextView productPrice,productDescription,productName;
    private String productId="",state="Normal";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        productId=getIntent().getStringExtra("pid");

        addtocartbtn=(Button)findViewById(R.id.pd_add_to_card_button);


        numberBtn=(ElegantNumberButton) findViewById(R.id.numbet_btn);
        productImage=(ImageView)findViewById(R.id.product_image);
        productDescription=(TextView)findViewById(R.id.product_description_details);
        productName=(TextView)findViewById(R.id.product_name_details);
        productPrice=(TextView)findViewById(R.id.product_price);


        getProductDetails(productId);


        addtocartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(state.equals("Order Placed")|| state.equals("Order Shipped")){
                    Toast.makeText(ProductDetailsActivity.this, "You can add purchase more products,once your order is shipped or confirm", Toast.LENGTH_LONG).show();
                }
                else{
                    addtocardlist();

                }


            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        checkOrderState();

    }

    private void addtocardlist() {

        String saveCurrentDate,saveCurrentTime;

        Calendar calForDate=Calendar.getInstance();
        SimpleDateFormat currentDate=new SimpleDateFormat("MMM dd,YYYY");
        saveCurrentDate=currentDate.format(calForDate.getTime());


        SimpleDateFormat currentTime=new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime=currentDate.format(calForDate.getTime());


        final DatabaseReference cartListRef=FirebaseDatabase.getInstance().getReference().child("Cart List");
        final HashMap<String, Object>cartMap=new HashMap<>();

        cartMap.put("pid",productId);
        cartMap.put("pname",productName.getText().toString());
        cartMap.put("price",productPrice.getText().toString());
        cartMap.put("date",saveCurrentDate);
        cartMap.put("time",saveCurrentTime);
        cartMap.put("quantity",numberBtn.getNumber());
        cartMap.put("discount","");

        cartListRef.child("User View").child(Prevelent.currentOnlineUser.getPhone()).
                child("products").child(productId).
                updateChildren(cartMap).
                addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            cartListRef.child("Admin View").child(Prevelent.currentOnlineUser.getPhone()).
                                    child("products").child(productId).updateChildren(cartMap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(ProductDetailsActivity.this, "Added to cart List", Toast.LENGTH_SHORT).show();

                                            Intent intent=new Intent(ProductDetailsActivity.this,HomeActivity.class);
                                            startActivity(intent);

                                        }
                                    });
                        }

                    }
                });







    }

    private void getProductDetails(String productId) {
        DatabaseReference productsRef= FirebaseDatabase.getInstance().getReference().child("products");
        productsRef.child(productId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    products products=dataSnapshot.getValue(products.class);

                    productName.setText(products.getPName());
                    productPrice.setText(products.getPrice());
                    productDescription.setText(products.getDescription());
                    Picasso.get().load(products.getImage()).into(productImage);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void checkOrderState() {
        DatabaseReference orderRef;
        orderRef = FirebaseDatabase.getInstance().getReference().child("Orders").child(Prevelent.currentOnlineUser.getPhone());
        orderRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String shippingState = dataSnapshot.child("state").getValue().toString();

                    if (shippingState.equals("shipped"))
                    {
                        state="Order Shipped";

                    } else if (shippingState.equals("Not shipped"))
                    {
                        state="Order Placed";

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}