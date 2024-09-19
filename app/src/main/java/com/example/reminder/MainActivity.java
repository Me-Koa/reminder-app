package com.example.reminder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private ReminderAdapter adapter;
    private ArrayList<Reminder> reminderList;
    private Intent data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            // Initialize RecyclerView
            recyclerView = findViewById(R.id.recyclerViewReminders);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            // Initialize reminder list and adapter
            reminderList = new ArrayList<>();
            adapter = new ReminderAdapter(reminderList);
            recyclerView.setAdapter(adapter);

            // Set up the "Add" button to navigate to the AddReminderActivity
            FloatingActionButton fabAddReminder = findViewById(R.id.fabAddReminder);
            fabAddReminder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Navigate to AddReminderActivity to add a new reminder
                    Intent intent = new Intent(MainActivity.this, AddReminderActivity.class);
                    startActivityForResult(intent, 1);
                }
            });


            return insets;
        });

    }
    // Handle the result when a reminder is added
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Get the new reminder from the intent and add it to the list
            String time = data.getStringExtra("time");
            String message = data.getStringExtra("message");

            reminderList.add(new Reminder(time, message));
            adapter.notifyDataSetChanged();
        }
    }

}