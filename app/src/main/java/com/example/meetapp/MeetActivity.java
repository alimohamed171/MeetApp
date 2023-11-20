package com.example.meetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.meetapp.databinding.ActivityMainBinding;
import com.example.meetapp.databinding.ActivityMeetBinding;
import com.zegocloud.uikit.prebuilt.videoconference.ZegoUIKitPrebuiltVideoConferenceConfig;
import com.zegocloud.uikit.prebuilt.videoconference.ZegoUIKitPrebuiltVideoConferenceFragment;

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


        binding.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "join meeting in mo7anken meeting app\n Meetin ID : "+meetingId);
                startActivity(Intent.createChooser(intent,"Share via"));
            }
        });



        addFragment();
    }






    public void addFragment() {
        long appID = Const.AppID;
        String appSign = Const.AppSign;



        ZegoUIKitPrebuiltVideoConferenceConfig config = new ZegoUIKitPrebuiltVideoConferenceConfig();
        ZegoUIKitPrebuiltVideoConferenceFragment fragment = ZegoUIKitPrebuiltVideoConferenceFragment.newInstance(appID, appSign, userID, name,meetingId,config);

        config.turnOnCameraWhenJoining = false;
        config.turnOnMicrophoneWhenJoining = false;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commitNow();
    }



}