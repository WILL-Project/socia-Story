package com.sbo.storyboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.Intent;
import android.icu.util.ULocale;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Document;

import java.util.Locale;

public class DisplayImages extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference myRef;
    FirebaseRecyclerOptions<ImagesData> options;
    FirebaseRecyclerAdapter<ImagesData, ViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_images);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        myRef = FirebaseDatabase.getInstance().getReference().child("ImageUrlData");

        options = new FirebaseRecyclerOptions.Builder<ImagesData>()
                .setQuery(myRef, ImagesData.class).build();

        adapter = new FirebaseRecyclerAdapter<ImagesData, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i, @NonNull final ImagesData imagesData) {
                Picasso.get().load(imagesData.getImageURL()).into(viewHolder.I1, new Callback() {
                    @Override
                    public void onSuccess() {
                        //For every Images thats loaded a this will show up
                        Toast.makeText(DisplayImages.this, "Image Loaded", Toast.LENGTH_SHORT).show();
                        //Makes the Images Clickable
                        viewHolder.I1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(DisplayImages.this, "I Clicked it", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), GalleryActivity.class);
                                intent.putExtra("image_url", imagesData.getImageURL());
                                startActivity(intent);
                            }
                        });
                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(DisplayImages.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.showimages, parent, false);
                return new ViewHolder(view);
            }
        };

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
}