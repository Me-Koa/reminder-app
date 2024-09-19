package com.example.reminder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddReminderActivity extends AppCompatActivity {


    private TimePicker timePicker;
    private EditText editTextReminder;
    private Button buttonSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_reminder);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            timePicker = findViewById(R.id.timePicker);
            editTextReminder = findViewById(R.id.editTextReminder);
            buttonSave = findViewById(R.id.buttonSaveReminder);

            ImageView imageView=findViewById(R.id.imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Navigate to AddReminderActivity to add a new reminder
                    Intent intent = new Intent(AddReminderActivity.this, MainActivity.class);
                    startActivityForResult(intent,1);
                }
            });

            // Set up Save button to return reminder data
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get the time from the TimePicker
                    int hour = timePicker.getHour();
                    int minute = timePicker.getMinute();
                    String time = String.format("%02d:%02d", hour, minute);

                    // Get the reminder message
                    String message = editTextReminder.getText().toString();

                    // Show a toast message confirming the save
                    Toast.makeText(AddReminderActivity.this, "Reminder Saved", Toast.LENGTH_SHORT).show();

                    // Return the data to MainActivity
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("time", time);
                    resultIntent.putExtra("message", message);
                    setResult(RESULT_OK, resultIntent);
                    finish();  // Close this activity and go back to MainActivity
                }


            });

            return insets;
        });
    }
}