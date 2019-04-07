package com.example.twesix.learn.android;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyListView extends AppCompatActivity {

    String data[] =
            {
                    "item 1",
                    "item 2",
                    "item 3",
                    "item 4",
                    "item 5",
                    "item 6",
                    "item 7",
                    "item 8",
                    "item 9",

            };
    private List<ListViewWithImage> listViewWithImageList = new ArrayList<>();
    ListView listView;
    ListView listViewWithImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list_view);
        listView = findViewById(R.id.list_view);
        listViewWithImage = findViewById(R.id.list_view_with_image);

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(stringArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String string = data[position];
                Toast.makeText(MyListView.this, string, Toast.LENGTH_SHORT).show();
            }
        });

        ListViewWithImageAdapter listViewWithImageAdapter = new ListViewWithImageAdapter(this, R.layout.list_view_with_image, listViewWithImageList);
        for (String aData : data) {
            ListViewWithImage listViewWithImage = new ListViewWithImage(aData, R.drawable.ic_launcher_foreground);
            listViewWithImageList.add(listViewWithImage);
        }
        listViewWithImage.setAdapter(listViewWithImageAdapter);
    }
}

class ListViewWithImage
{
    private String name;
    private int imageId;

    public ListViewWithImage(String name, int imageId)
    {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName()
    {
        return this.name;
    }

    public int getImageId()
    {
        return this.imageId;
    }
}

class ListViewWithImageAdapter extends ArrayAdapter<ListViewWithImage>
{
    private int resourceId;

    public ListViewWithImageAdapter(Context context, int textViewResourceId, List<ListViewWithImage> objects)
    {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ListViewWithImage listViewWithImage = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null)
        {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = view.findViewById(R.id.image_view);
            viewHolder.textView = view.findViewById(R.id.text_view);
            view.setTag(viewHolder);
        }
        else
        {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.imageView.setImageResource(listViewWithImage.getImageId());
        viewHolder.textView.setText(listViewWithImage.getName());
        return view;
    }
}

class ViewHolder
{
    ImageView imageView;
    TextView textView;
}
