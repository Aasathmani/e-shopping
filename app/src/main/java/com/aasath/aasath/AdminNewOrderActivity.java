package com.aasath.aasath;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.aasath.aasath.Model.AdminOrders;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminNewOrderActivity extends AppCompatActivity
{
    private RecyclerView ordersList;
    private DatabaseReference orderRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_new_order);

        orderRef= FirebaseDatabase.getInstance().getReference().child("Orders");

        ordersList=findViewById(R.id.order_list);
        ordersList.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();


        FirebaseRecyclerOptions<AdminOrders> options=
                new FirebaseRecyclerOptions.Builder<AdminOrders>()
                .setQuery(orderRef,AdminOrders.class)
                .build();
        FirebaseRecyclerAdapter<AdminOrders, AdminOrdersViewHolder> adapter=
               new FirebaseRecyclerAdapter<AdminOrders, AdminOrdersViewHolder>(options) {
                   @Override
                   protected void onBindViewHolder(@NonNull AdminOrdersViewHolder adminOrdersViewHolder, int i, @NonNull AdminOrders adminOrders) {

                      adminOrdersViewHolder.userName.setText("Name:"+adminOrders.getName());
                       adminOrdersViewHolder.userPhoneNumber.setText("phone:"+adminOrders.getPhone());
                       adminOrdersViewHolder.userTotalPrice.setText("total amount:"+adminOrders.getTotalAmount());
                       adminOrdersViewHolder.userDataTime.setText("date:"+adminOrders.getDate());
                       adminOrdersViewHolder.userShippingAddress.setText("address:"+adminOrders.getAddress());

                   }

                   @NonNull
                   @Override
                   public AdminOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                       View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.order_layout,parent,false);
                       return new AdminOrdersViewHolder(view);
                   }
               };

        ordersList.setAdapter(adapter);
        adapter.startListening();





    }
    public static class AdminOrdersViewHolder extends RecyclerView.ViewHolder
    {

        public TextView userName,userPhoneNumber,userTotalPrice,userDataTime,userShippingAddress;
        public Button showOrderBtn;


        public AdminOrdersViewHolder(@NonNull View itemView)
        {
            super(itemView);
            userName=itemView.findViewById(R.id.user_name);
            userPhoneNumber=itemView.findViewById(R.id.order_phone_number);
            userTotalPrice=itemView.findViewById(R.id.order_total_price);
            userDataTime=itemView.findViewById(R.id.order_date_time);
            userShippingAddress=itemView.findViewById(R.id.order_address_city);
            showOrderBtn=itemView.findViewById(R.id.show_all_products_btn);
        }
    }









}