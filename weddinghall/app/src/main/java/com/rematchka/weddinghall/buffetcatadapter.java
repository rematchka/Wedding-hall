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
 * Created by extra on 25/12/2016.
 */
public class buffetcatadapter  extends ArrayAdapter<buffetcategories> {
    Context context;
    ArrayList<buffetcategories> items;
    public buffetcatadapter(Activity context, ArrayList<buffetcategories> buffitems) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0,buffitems);
        this.context=context;
        this.items=buffitems;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.listviewbuffetcat, parent, false);

        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        final buffetcategories currentAndroidFlavor = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.categouries);

        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        nameTextView.setText(currentAndroidFlavor.getCategouries());
        Log.e("hhhh", nameTextView.getText().toString());

        // Find the TextView in the list_item.xml layout with the ID version_number
        ;
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
    public buffetcategories getItem(int i) {
        return items.get(i);
    }
}
