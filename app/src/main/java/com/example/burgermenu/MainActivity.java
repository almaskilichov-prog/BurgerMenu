package com.example.burgermenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.burgermenu.ui.rectperimeter.RectPerimeterFragment;
import com.example.burgermenu.ui.rectsquare.RectSquareFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    Auth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = new Auth(this);

        if (auth.getUsername() == null) {
            Intent intent = new Intent(getApplicationContext(),
                    SignInActivity.class);
            startActivity(intent);
        }

        // Установка тулбара
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        // ФАБ и клик по нему


        // Нужно чтоб бургер открывался
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,
                toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Шторка
        NavigationView navigationView = findViewById(R.id.nav_view);

        //Шапка шторки
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.textRectSquare);

        // Фрагменты
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment f = null;
                if (item.getItemId() == R.id.nav_rect_square) {
                    f = new RectSquareFragment();
                } else if (item.getItemId() == R.id.nav_rect_perimeter) {
                    f = new RectPerimeterFragment();
                }
                setTitle(item.getTitle());


                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, f).commit();

                drawer.close();
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isOpen()) {
            drawer.close();
        } else {
            super.onBackPressed();
        }
    }
}