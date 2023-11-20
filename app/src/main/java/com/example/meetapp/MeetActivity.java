package com.example.meetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.meetapp.databinding.ActivityMainBinding;
import com.example.meetapp.databinding.ActivityMeetBinding;

public class MeetActivity extends AppCompatActivity {

    ActivityMeetBinding binding;

    String meetingId,name,userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMeetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        meetingId = getIntent().getStringExtra("meet_id");
        name = getIntent().getStringExtra("name");
        userID = getIntent().getStringExtra("user_id");

        binding.meetId.setText("Meeting id: "+meetingId);

    }
}