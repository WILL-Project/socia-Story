package com.sbo.storyboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    EditText etUrl;
    Button btClear, btSubmit;
    ImageView ivResult;
    TextView test, test2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Intent intnt = new Intent(getApplicationContext(), DisplayImages.class);
        startActivity(intnt);






        etUrl = findViewById(R.id.et_urlEditText);
        btClear = findViewById(R.id.ClearBtn);
        btSubmit = findViewById(R.id.SubmitBtn);
        ivResult = findViewById(R.id.iv_resultImageView);
        btClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etUrl.setText("");
                ivResult.setImageBitmap(null);
            }
        });

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlLink = etUrl.getText().toString();
                if (urlLink.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Url ! ", Toast.LENGTH_SHORT).show();
                }else {
                    LoadImage loadImage = new LoadImage(ivResult);
                    loadImage.execute(urlLink);
                    GotToSaveImagesWithDelay();
                }
            }
        });
    }
    //
    private class LoadImage extends AsyncTask<String,Void, Bitmap> {
        ImageView imageView;
        public LoadImage (ImageView ivResult){
            this.imageView = ivResult;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String urlLink = strings[0];
            Bitmap bitmap = null;
            try (InputStream inputStream = new java.net.URL(urlLink).openStream()) {
                bitmap = BitmapFactory.decodeStream(inputStream);
            }catch (IOException e){
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            ivResult.setImageBitmap(bitmap);
        }
    }
    private void GotToSaveImages(){
        Intent intent = new Intent(this, SaveImagesToDatabase.class);
        intent.putExtra("URL", etUrl.getText().toString());
        startActivity(intent);
    }
    private void  GotToSaveImagesWithDelay(){
        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                GotToSaveImages();
            }
        }, 2000);
    }
}