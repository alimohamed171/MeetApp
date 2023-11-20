package com.example.meetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.meetapp.databinding.ActivityMainBinding;

import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = binding.edtName.getText().toString();
                String id = "55555";
                if(name.isEmpty()){
                    binding.edtName.setError("required");
                    binding.edtName.requestFocus();
                }

                else if(isValid(name,id)){
                    Toast.makeText(MainActivity.this, "valid", Toast.LENGTH_SHORT).show();
                    startMeeting(getRandomId(),name);

                    }else{
                        Toast.makeText(MainActivity.this, "not valid", Toast.LENGTH_SHORT).show();
                    }

            }
        });

        binding.btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.edtName.getText().toString();
                String id = binding.edtMeetingId.getText().toString();
                if(name.isEmpty()){
                    binding.edtName.setError("required");
                    binding.edtName.requestFocus();
                }  else if (id.isEmpty()) {
                    binding.edtMeetingId.setError("required");
                    binding.edtMeetingId.requestFocus();
                }
                else if(isValid(name,id)){
                    Toast.makeText(MainActivity.this, "valid", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "valid", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


    public void startMeeting(String meetingID,String name){
        String userId= UUID.randomUUID().toString();
        Intent intent = new Intent(MainActivity.this, MeetActivity.class);
        intent.putExtra("meet_id",meetingID);
        intent.putExtra("name", name);
        intent.putExtra("user_id",userId);
        startActivity(intent);
    }
    public String getRandomId(){
        StringBuilder id = new StringBuilder();
        while (id.length()!=6){
            int random = new Random().nextInt(10);
            id.append(random);
        }

        return id.toString();
    }


    private boolean isValid(String name, String id) {
        String pattern = "^[\\w]+$";
        boolean match1 = Pattern.matches(pattern, name);
        boolean match2 = Pattern.matches(pattern, id);
        return match2 && match1;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}