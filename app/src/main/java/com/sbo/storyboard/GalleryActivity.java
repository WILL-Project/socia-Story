package com.sbo.storyboard;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class GalleryActivity extends AppCompatActivity {
    private static final String TAG = "GalleryActivity";
    ImageView image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Log.d(TAG, "onCreate: started.");

        image = findViewById(R.id.image_r);
        Toast.makeText(this, "It works", Toast.LENGTH_SHORT).show();
        getIncommingIntent();
    }

    private void getIncommingIntent(){
        if(getIntent().hasExtra("image_url")){
            Toast.makeText(this, "Found Intent", Toast.LENGTH_SHORT).show();

            String imageUrl = getIntent().getStringExtra("image_url");
            setImage(imageUrl);
        }else {
            Toast.makeText(this, "Did not find Intent", Toast.LENGTH_SHORT).show();
        }
    }
    private void setImage(String imageURL){
        Log.d(TAG, "setImage: setting to image to widgets.");
        Glide.with(this)
                .asBitmap()
                .load(imageURL)
                .into(image);
    }
}
