package com.sbo.storyboard;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    //public TextView T1;
    public ImageView I1;

    public ViewHolder(View itemView) {
        super(itemView);

        //T1 = itemView.findViewById(R.id.nameId);
        I1 = itemView.findViewById(R.id.image);
    }
}
