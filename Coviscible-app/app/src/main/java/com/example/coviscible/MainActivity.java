package com.example.coviscible;

import android.os.Bundle;

import com.example.coviscible.handler.NewReminder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    private Boolean isFabOpen = false;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab_open = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(this, R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);

        FloatingActionButton fab_add = findViewById(R.id.fab_create);
        FloatingActionButton fab_sanitizer = findViewById(R.id.fab_sanitizer);
        FloatingActionButton fab_exercise = findViewById(R.id.fab_exercise);
        FloatingActionButton fab_medicine = findViewById(R.id.fab_medicine);


        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFabOpen) {
                    fab_add.startAnimation(rotate_backward);
                    fab_sanitizer.startAnimation(fab_close);
                    fab_medicine.startAnimation(fab_close);
                    fab_exercise.startAnimation(fab_close);
                    fab_sanitizer.setClickable(false);
                    fab_medicine.setClickable(false);
                    fab_exercise.setClickable(false);
                    isFabOpen = false;
                } else {
                    fab_add.startAnimation(rotate_forward);
                    fab_sanitizer.startAnimation(fab_open);
                    fab_medicine.startAnimation(fab_open);
                    fab_exercise.startAnimation(fab_open);
                    fab_sanitizer.setClickable(true);
                    fab_medicine.setClickable(true);
                    fab_exercise.setClickable(true);
                    isFabOpen = true;
                }
            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addMedicine(View view) {
        NewReminder handler = new NewReminder(this);
        handler.init();
    }
}