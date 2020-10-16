package com.zybooks.andresrodriguezinventoryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter {

    private Context context;
    private LayoutInflater inflater;

    ArrayList<Item> arrayItemList = new ArrayList<>();

    public ItemAdapter(Context context, int textViewResourceId, ArrayList arrayList) {
        super(context, textViewResourceId, arrayList);
        this.context = context;
        this.arrayItemList = arrayList;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.grid_view_item, null);
        }

        TextView textViewItemName = convertView.findViewById(R.id.textViewItemName);
        TextView textViewItemCount = convertView.findViewById(R.id.textViewItemCount);

        textViewItemName.setText(arrayItemList.get(position).itemName);
        textViewItemCount.setText((Integer.toString(arrayItemList.get(position).itemCount)));

        return convertView;
    }
}
