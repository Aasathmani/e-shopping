package com.aasath.aasath;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aasath.aasath.Model.products;
import com.aasath.aasath.Prevelent.Prevelent;
import com.aasath.aasath.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import androidx.core.view.GravityCompat;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private DatabaseReference ProductsRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ProductsRef= FirebaseDatabase.getInstance().getReference().child("products");
        Paper.init(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(HomeActivity.this,CartActivity.class);
                startActivity(intent);

            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView=navigationView.getHeaderView(0);
        TextView userNameTextView=headerView.findViewById(R.id.profilename);
        ImageView imageView=headerView.findViewById(R.id.profile);

        userNameTextView.setText(Prevelent.currentOnlineUser.getName());
        Picasso.get().load(Prevelent.currentOnlineUser.getImage()).placeholder(R.drawable.profile);


        recyclerView=findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<products> options=
                new FirebaseRecyclerOptions.Builder<products>()
                        .setQuery(ProductsRef, products.class)
                        .build();
        FirebaseRecyclerAdapter<products, ProductViewHolder>adapter=
                new FirebaseRecyclerAdapter<products, ProductViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ProductViewHolder holder , int position, @NonNull products model) {
                        holder.txtproductName.setText(model.getPName());
                        holder.txtProductDescription.setText(model.getDescription());
                        holder.txtProductPrice.setText("Price=" +model.getPrice()+"$");
                        Picasso.get().load(model.getImage()).into(holder.imageView);

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view)
                            {


                                Intent intent = new Intent(HomeActivity.this,ProductDetailsActivity.class);
                                intent.putExtra("pid",model.getPid());
                                startActivity(intent);

                            }
                        });





                    }

                    @NonNull
                    @Override
                    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_layout,parent,false);
                        ProductViewHolder holder=new ProductViewHolder(view);
                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();







    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    //@Override
    //public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    // int id = item.getItemId();
    //   if (id == R.id.action_settings) {
    //     return true;

    //}
    // return super.onOptionsItemSelected(item);

    // }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.nav_cart)
        {
            Intent intent=new Intent(HomeActivity.this,CartActivity.class);
            startActivity(intent);


        }
        else if (id == R.id.nav_orders)
        {

        }
        else if (id == R.id.nav_categories)
        {

        }
        else if (id == R.id.nav_settings)
        {
            Intent intent=new Intent(HomeActivity.this,SettingsActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_logout)
        {


        }
        DrawerLayout drawer=(DrawerLayout)findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }

}
