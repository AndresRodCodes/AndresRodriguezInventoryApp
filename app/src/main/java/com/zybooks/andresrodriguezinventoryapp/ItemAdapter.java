package com.zybooks.andresrodriguezinventoryapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.grid_view_item, null);
        }

        TextView textViewItemName = convertView.findViewById(R.id.textViewItemName);
        TextView textViewItemCount = convertView.findViewById(R.id.textViewItemCount);
        Button buttonEditItem = convertView.findViewById(R.id.buttonEditItem);
        Button buttonDeleteItem = convertView.findViewById(R.id.buttonDeleteItem);

        textViewItemName.setText(arrayItemList.get(position).itemName);
        textViewItemCount.setText((Integer.toString(arrayItemList.get(position).itemCount)));
        buttonEditItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                long itemId = arrayItemList.get(position).itemId;
                String itemName = arrayItemList.get(position).itemName;
                int itemCount = arrayItemList.get(position).itemCount;

                System.out.println("Button clicked with id: " +  itemId);
//                ItemDatabase itemDatabase = new ItemDatabase(getContext());
//                boolean itemUpdated = itemDatabase.updateItem(itemId, itemName, itemCount + 1);
//                System.out.println("Item Updated: " + itemUpdated);
                Intent goToEditItemActivity = new Intent(getContext(), EditItemActivity.class);
                goToEditItemActivity.putExtra("EXTRA_ITEM_ID", itemId);
                goToEditItemActivity.putExtra("EXTRA_ITEM_NAME", itemName);
                goToEditItemActivity.putExtra("EXTRA_ITEM_COUNT", itemCount);
                context.startActivity(goToEditItemActivity);
            }
        });

        buttonDeleteItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                long itemId = arrayItemList.get(position).itemId;
                System.out.println("Delete button with id: " + arrayItemList.get(position).itemId);
                ItemDatabase itemDatabase = new ItemDatabase(getContext());
                itemDatabase.deleteItem(itemId);

                Intent refreshItemGrid = new Intent(context, ItemGridActivity.class);
                context.startActivity(refreshItemGrid);
            }
        });

        return convertView;
    }
}
