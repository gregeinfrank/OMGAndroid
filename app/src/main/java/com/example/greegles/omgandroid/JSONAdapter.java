package com.example.greegles.omgandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by greegles on 9/26/14.
 */
public class JSONAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater mInflater;
    JSONArray mJsonArray;

    public JSONAdapter(Context context, LayoutInflater inflater) {
        mContext = context;
        mInflater = inflater;
        mJsonArray = new JSONArray();
    }

    @Override
    public int getCount() {
        return mJsonArray.length();
    }

    @Override
    public Object getItem(int position) {
        return mJsonArray.optJSONObject(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        // check if the view already exists
        // if so, no need to inflate and findViewById again!
        if (convertView == null) {

            // Inflate the custom row layout from your XML.
            convertView = mInflater.inflate(R.layout.row_contact, null);

            // create a new "Holder" with subviews
            holder = new ViewHolder();
            holder.thumbnailImageView = (ImageView) convertView.findViewById(R.id.img_thumbnail);
            holder.nameTextView = (TextView) convertView.findViewById(R.id.text_name);
            holder.phoneTextView = (TextView) convertView.findViewById(R.id.text_phone);
            holder.emailTextView = (TextView) convertView.findViewById(R.id.text_email);


            // hang onto this holder for future recyclage
            convertView.setTag(holder);
        } else {

            // skip all the expensive inflation/findViewById
            // and just get the holder you already made
            holder = (ViewHolder) convertView.getTag();
        }

        JSONObject jsonObject = (JSONObject) getItem(position);

        String contactName = "";
        String contactPhone = "";
        String contactEmail = "";

        if (jsonObject.has("name")) {
            contactName = jsonObject.optString("name");
        }

        if (jsonObject.has("phone_number")) {
            contactPhone = jsonObject.optString("phone_number");
        }

        if (jsonObject.has("email")) {
            contactEmail = jsonObject.optString("email");
        }
        holder.thumbnailImageView.setImageResource(R.drawable.ic_books);
        // Send these Strings to the TextViews for display
        holder.nameTextView.setText(contactName);
        holder.phoneTextView.setText(contactPhone);
        holder.emailTextView.setText(contactEmail);
        return convertView;
    }

    public void updateData(JSONArray jsonArray) {
        // update the adapter's dataset
        mJsonArray = jsonArray;
        notifyDataSetChanged();
    }

    // this is used so you only ever have to do
    // inflation and finding by ID once ever per View
    private static class ViewHolder {
        public ImageView thumbnailImageView;
        public TextView nameTextView;
        public TextView phoneTextView;
        public TextView emailTextView;
    }
}