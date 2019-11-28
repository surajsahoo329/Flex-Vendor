package com.example.flexvendor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class CustomListViewAdapter extends ArrayAdapter<Users> {

    private Context context;

    CustomListViewAdapter(Context context, int resourceId,
                          List<Users> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtEmail;
        TextView txtName;
        TextView txtPhone;
        TextView txtTimings;
    }

    @SuppressLint("InflateParams")
    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        Users users=  getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            assert mInflater != null;
            convertView = mInflater.inflate(R.layout.list_slot, null);
            holder = new ViewHolder();
            holder.txtName=convertView.findViewById(R.id.tvCompany);
            holder.txtTimings=convertView.findViewById(R.id.tvTimings);
            holder.txtPhone=convertView.findViewById(R.id.tvAddress);
            holder.imageView=convertView.findViewById(R.id.ivImage);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        assert users != null;
        holder.txtName.setText(users.getName());
        holder.txtTimings.setText(users.getTimings());
        holder.txtPhone.setText(users.getPhone());
        //holder.imageView.setImageResource(users.getImageId());

        return convertView;
    }
}
