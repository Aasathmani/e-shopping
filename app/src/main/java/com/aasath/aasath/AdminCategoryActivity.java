package com.aasath.aasath;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AdminCategoryActivity extends AppCompatActivity {

    private ImageView tShirt,sportsTShirts,femaleDresses,sweather;
    private ImageView glasses,hatsCaps,walletBagPurses,shoes;
    private ImageView headPhoneHandFree,Laptops,watches,mobilePhones;

    private Button Logoutbtn,CheckOrdersBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);


        Logoutbtn=(Button)findViewById(R.id.admin_logout_btn);
        CheckOrdersBtn=(Button)findViewById(R.id.check_order_btn);

        Logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminCategoryActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        CheckOrdersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminNewOrderActivity.class);

                startActivity(intent);


            }
        });



        tShirt=(ImageView)findViewById(R.id.t_shirts);
        sportsTShirts=(ImageView)findViewById(R.id.sport_t_shirts);
        femaleDresses=(ImageView)findViewById(R.id.female_t_shirts);
        sweather=(ImageView)findViewById(R.id.sweather_t_shirts);

        glasses=(ImageView)findViewById(R.id.glasses);
        hatsCaps=(ImageView)findViewById(R.id.hats_caps);
        walletBagPurses=(ImageView)findViewById(R.id.purses_bags_wallet);
        shoes=(ImageView)findViewById(R.id.shoess);

        headPhoneHandFree=(ImageView)findViewById(R.id.headphones_handfree);
        Laptops=(ImageView)findViewById(R.id.laptop);
        watches=(ImageView)findViewById(R.id.watches);
        mobilePhones=(ImageView)findViewById(R.id.mobiles);





        tShirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","tShirts");
                startActivity(intent);
            }
        });
        sportsTShirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","sportsTShirts");
                startActivity(intent);
            }
        });


        femaleDresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","femaleDresses");
                startActivity(intent);
            }
        });
        sweather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","sweathers");
                startActivity(intent);
            }
        });

        glasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","glasses");
                startActivity(intent);
            }
        });
        hatsCaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","hatscaps");
                startActivity(intent);
            }
        });

        walletBagPurses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","walletBagspurses");
                startActivity(intent);
            }
        });

        shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","Shoes");
                startActivity(intent);
            }
        });
        headPhoneHandFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","headPhoneHandFree");
                startActivity(intent);
            }
        });

        Laptops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","Laptops");
                startActivity(intent);
            }
        });
        watches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","Watches");
                startActivity(intent);
            }
        });
        mobilePhones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","mobilePhones");
                startActivity(intent);
            }
        });
    }
}