package com.sbo.storyboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SaveImagesToDatabase extends AppCompatActivity {
    ImagesData imagesData = new ImagesData();
    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("ImageUrlData");
    //Button
    Button save, next_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_images_to_database);

        save = findViewById(R.id.SaveBtn);
        next_page = findViewById(R.id.nextBtn);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WriteToDatabase();
            }
        });
        next_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DisplayImages.class);
                startActivity(intent);
            }
        });
    }
    private void WriteToDatabase(){
        Intent intent = getIntent();
        String TheUrl = intent.getStringExtra("URL");
        String TheKey = myRef.push().getKey();

        imagesData.setImageId(TheKey);
        imagesData.setImageURL(TheUrl);
        myRef.push().setValue(imagesData);
        Toast.makeText(this, "Image Saved To Database", Toast.LENGTH_SHORT).show();
    }
}