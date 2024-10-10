package com.example.jobfit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class SwipeAdapter extends BaseAdapter {

    private Context context;
    private List<String> dataList;  // This is for card titles/text
    private List<Integer> imageList;  // This is for the images

    public SwipeAdapter(Context context, List<String> dataList, List<Integer> imageList) {
        this.context = context;
        this.dataList = dataList;
        this.imageList = imageList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);
        }

        TextView cardText = convertView.findViewById(R.id.cardText);
        ImageView cardImage = convertView.findViewById(R.id.cardImage);

        // Set the card title/text
        cardText.setText(dataList.get(position));

        // Set the card image
        cardImage.setImageResource(imageList.get(position));

        return convertView;
    }
}
