package com.example.grumpytimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.os.CountDownTimer;

public class MainActivity extends AppCompatActivity {

    // Declare fields for the start button, timer count, and TextView
    Button startButton;
    long timerCount;
    private long totalCount;

    TextView timerTextView;
    ImageView bearImageView; // Add a field for the ImageView
    ImageView leftEyebrowImageView, rightEyebrowImageView;
    Drawable tiltedEyebrowsDrawable; // Add fields for the Drawable objects
    CountDownTimer timer; // Add a field for the timer object
    private Drawable eyebrowDrawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the TextView and Button in the layout and assign them to fields
        timerTextView = findViewById(R.id.timer_text_view);
        bearImageView = findViewById(R.id.bear_image_view); // Find the ImageView
        // Retrieve the ImageView objects from the layout
        leftEyebrowImageView = (ImageView) findViewById(R.id.left_eyebrow_image_view);
        rightEyebrowImageView = (ImageView) findViewById(R.id.right_eyebrow_image_view);
        startButton = findViewById(R.id.start_button);

        eyebrowDrawable = getResources().getDrawable(R.drawable.eyebrow, null);

        leftEyebrowImageView.setImageDrawable(eyebrowDrawable);
        rightEyebrowImageView.setImageDrawable(eyebrowDrawable);

        // Set the value of the timer count field

        totalCount = 10; // Set the total duration of the timer
        timerCount = totalCount * 1000; // duration in milliseconds

        // Set an OnClickListener on the start button
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timer == null) {
                    // Create a new CountDownTimer object and start it
                    timer = new CountDownTimer(timerCount, 1000) { // timerCount is the duration of the timer in milliseconds, 1000 is the tick interval
                        public void onTick(long millisUntilFinished) {
                            // Update the TextView with the current timer count
                            long count = millisUntilFinished / 1000;
                            timerTextView.setText(String.valueOf(count));

                            // Calculate the rotation angle for the eyebrows
                            float rotation = 45 * (totalCount - count) / totalCount; // 45 degrees

                            // Set the rotation of the eyebrow ImageViews
                            leftEyebrowImageView.setRotation(-rotation);
                            rightEyebrowImageView.setRotation(rotation);
                        }

                        public void onFinish() {
                            // Perform some action when the timer finishes
                            timerTextView.setText("Done!");
                            timer = null; // Set the timer field to null
                        }
                    }.start();
                }
            }
        });
    }
}