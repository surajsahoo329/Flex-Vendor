package com.example.flexvendor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class CustomListViewAdapter extends ArrayAdapter<Users> {

    private Context context;
    String getImageUrl, getEmail;

    CustomListViewAdapter(Context context, int resourceId,
                          List<Users> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    @SuppressLint({"InflateParams", "SetTextI18n"})
    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        Users users=getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        final ViewHolder holder;
        if (convertView == null) {
            assert mInflater != null;
            convertView = mInflater.inflate(R.layout.list_slot, null);
            holder=new ViewHolder();
            holder.txtName=convertView.findViewById(R.id.tvUserName);
            holder.txtPhone=convertView.findViewById(R.id.tvUserPhone);
            holder.txtTimings=convertView.findViewById(R.id.tvTimings);
            holder.txtEmail=convertView.findViewById(R.id.invisibleEmail);
            holder.imageView=convertView.findViewById(R.id.ivImage);
            convertView.setTag(holder);
        } else
            holder=(ViewHolder) convertView.getTag();

        assert users != null;
        holder.txtName.setText(users.getName());
        holder.txtPhone.setText("+91-" + users.getPhone());
        holder.txtTimings.setText(users.getTimings());
        holder.txtEmail.setText(users.getEmail());
        getEmail=holder.txtEmail.getText().toString().trim();

        final StorageReference mStorageRef=FirebaseStorage.getInstance().getReference();
        final StorageReference imgRef=mStorageRef.child(getEmail + "/photo.jpg");
        final long TEN_MEGABYTES=10024 * 10024;

        Toast.makeText(context, getEmail, Toast.LENGTH_LONG).show();

        imgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                getImageUrl=uri.toString();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {


            }
        });

        imgRef.getBytes(TEN_MEGABYTES).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {

                Glide.with(context)
                        .load(getImageUrl)
                        .apply(RequestOptions.circleCropTransform())
                        .into(holder.imageView);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {


            }
        });

        return convertView;
    }

    /*private view holder class*/
    static class ViewHolder {
        ImageView imageView;
        TextView txtEmail;
        TextView txtName;
        TextView txtPhone;
        TextView txtTimings;
    }


}
