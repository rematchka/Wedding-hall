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

import java.util.ArrayList;

/**
 * Created by extra on 26/12/2016.
 */
public class reservationdateAdapter extends ArrayAdapter<reservationdate> {
    Context context;
    ArrayList<reservationdate> items;
    public reservationdateAdapter(Activity context, ArrayList<reservationdate> date) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, date);
        this.context=context;
        this.items=date;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.listvieworders, parent, false);

        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        final reservationdate currentAndroidFlavor = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.timeanddate);

        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        nameTextView.setText("Date "+currentAndroidFlavor.getDate());
        Log.e("hhhh", nameTextView.getText().toString());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.slot);

        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        numberTextView.setText("Slot "+currentAndroidFlavor.getSlot());
        Log.e("jjjj", numberTextView.getText().toString());








        return listItemView;
    }

    @Override
    public int getCount() {
        if(items == null)
            return 0;
        return items.size();
    }

    @Override
    public reservationdate getItem(int i) {
        return items.get(i);
    }
}
