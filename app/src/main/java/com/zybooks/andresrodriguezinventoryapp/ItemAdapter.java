package com.zybooks.andresrodriguezinventoryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private String[] numberWord;

    public ItemAdapter(Context context, String[] numberWord) {
        this.context = context;
        this.numberWord = numberWord;
    }

    @Override
    public int getCount() {
        return this.numberWord.length;
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
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.grid_view_item, null);
        }

        TextView textView = convertView.findViewById(R.id.text_view);

        textView.setText(numberWord[position]);

        return convertView;
    }
}
