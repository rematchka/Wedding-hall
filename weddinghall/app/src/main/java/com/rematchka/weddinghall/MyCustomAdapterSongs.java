package com.rematchka.weddinghall;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by extra on 24/12/2016.
 */
public class MyCustomAdapterSongs extends ArrayAdapter<songsarray> {

    Context context;
    ArrayList<songsarray> items;
    public MyCustomAdapterSongs(Activity context, ArrayList<songsarray> songsarrays) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, songsarrays);
        this.context=context;
        this.items=songsarrays;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.itemlist, parent, false);

        }

    // Get the {@link AndroidFlavor} object located at this position in the list
        final songsarray currentAndroidFlavor = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.textView1);

        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        nameTextView.setText(currentAndroidFlavor.getSingername());
        Log.e("hhhh", nameTextView.getText().toString());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.textView2);

        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        numberTextView.setText(currentAndroidFlavor.getSongname());
        Log.e("jjjj", numberTextView.getText().toString());



        final  CheckBox ch=(CheckBox)listItemView.findViewById(R.id.checkBox1);



        ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                final boolean isChecked = ch.isChecked();
                Log.i("position",""+position);
                Log.i("on click listerner",""+isChecked);
                currentAndroidFlavor.setSelected(isChecked);
                ch.setSelected(isChecked);
                // Do something here.
            }
        });
        ch.setSelected(currentAndroidFlavor.isSelected());
        Log.i("array",""+currentAndroidFlavor.isSelected());
        Log.e("check box", ""+ch.isSelected());
        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

    @Override
    public int getCount() {
        if(items == null)
            return 0;
        return items.size();
    }

    @Override
    public songsarray getItem(int i) {
        return items.get(i);
    }

}
