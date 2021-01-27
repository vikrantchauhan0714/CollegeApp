package com.example.collegeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.collegeapp.ebook.Ebook;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
private BottomNavigationView bottomNavigationView;
private NavController navController;

private DrawerLayout drawerLayout;
private ActionBarDrawerToggle toggle;
private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        navController= Navigation.findNavController(this,R.id.frame_layout);
        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.navigation_view);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.start,R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Abes Engineering");

        navigationView.setNavigationItemSelectedListener(this);


        NavigationUI.setupWithNavController(bottomNavigationView,navController);



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navigation_developer:
                startActivity(new Intent(this, Developer.class));

                break;
            case R.id.navigation_video:
                Toast.makeText(this, "Video Lecture", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_share:
                try {
                    Intent i=new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT,"College App");
                    i.putExtra(Intent.EXTRA_TEXT,"\"https://play.google.com/store/apps/details?id="+ getApplicationContext().getPackageName());
                    startActivity(Intent.createChooser(i,"Share With"));
                } catch (Exception e) {
                    Toast.makeText(this, "Unable to share this app." +
                            "", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                break;
            case R.id.navigation_rateus:
                Toast.makeText(this, "Rate Us", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_ebook:
                startActivity(new Intent(this, Ebook.class));
                break;
            case R.id.navigation_website:
              Intent   intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.abes.ac.in/"));
                startActivity(intent);
                break;
            case R.id.navigation_theme:
                Toast.makeText(this, "Theme", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override//drawer close firstly......
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);

        }
        else
        super.onBackPressed();
    }
}
