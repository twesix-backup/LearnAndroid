package com.example.twesix.learn.android;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerView extends AppCompatActivity {

    String data[] =
            {
                    "item 1 is very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very long",
                    "item 2",
                    "item 3",
                    "item 4",
                    "item 5",
                    "item 6",
                    "item 7",
                    "item 8",
                    "item 9",

            };
    RecyclerView recyclerView;
    List<TextAndImage> textAndImageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recycler_view);
        for (String aString : data)
        {
            TextAndImage textAndImage = new TextAndImage(aString, R.drawable.ic_launcher_foreground);
            textAndImageList.add(textAndImage);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        TextAndImageAdapter textAndImageAdapter = new TextAndImageAdapter(textAndImageList);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(textAndImageAdapter);
    }
}

class TextAndImage
{
    String text;
    int imageId;

    TextAndImage(String text, int imageId)
    {
        this.text = text;
        this.imageId = imageId;
    }
}

class TextAndImageAdapter extends RecyclerView.Adapter<TextAndImageAdapter.ViewHolder>
{
    private List<TextAndImage> textAndImageList;
    TextAndImageAdapter(List<TextAndImage> list)
    {
        textAndImageList = list;
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        View viewItem;
        ImageView imageView;
        TextView textView;

        ViewHolder(View view)
        {
            super(view);
            viewItem = view;
            imageView = view.findViewById(R.id.image_view);
            textView = view.findViewById(R.id.text_view);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_view_with_image, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "image view", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "text view", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.viewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "view item", Toast.LENGTH_SHORT).show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextAndImage textAndImage = textAndImageList.get(position);
        holder.textView.setText(textAndImage.text);
        holder.imageView.setImageResource(textAndImage.imageId);
    }

    @Override
    public int getItemCount() {
        return textAndImageList.size();
    }
}
