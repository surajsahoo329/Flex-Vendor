package com.example.flexvendor;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */

public class HistorySlotFragment extends Fragment {

    private String checkMail, vEmail, name, phone, email, date, stTime, hours;
    private ListView historyListViewSlot;
    private CustomListViewAdapter adapter;
    private int companyId;

    private List<Users> users;


    public HistorySlotFragment() {
        // Required empty public constructor
    }


    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api=Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final Activity refActivity=getActivity();
        final View parentHolder=inflater.inflate(R.layout.fragment_history_slot, container, false);

        //final ProgressDialog pd=ProgressDialog.show(refActivity, "Loading slots", "Please wait...", true);

        FirebaseUser vendUser=FirebaseAuth.getInstance().getCurrentUser();
        assert vendUser != null;
        checkMail=vendUser.getEmail();
        assert refActivity != null;

        historyListViewSlot=parentHolder.findViewById(R.id.historyListViewSlot);
        users=new ArrayList<>();


        /*final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref=database.getReference("History");
        final DatabaseReference vendRef=database.getReference("Vendor");
        final DatabaseReference userRef=database.getReference("User");

        vendRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    vEmail=ds.child("vendorMail").getValue(String.class);
                    assert vEmail != null;
                    if (vEmail.equals(checkMail)) {

                        companyId=ds.child("companyId").getValue(Integer.class);

                        ref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                                    int slotFlag=ds.child("slotFlag").getValue(Integer.class);

                                    if (slotFlag == companyId) {

                                        final String email=ds.child("userMail").getValue(String.class);
                                        final String date=ds.child("showDate").getValue(String.class);
                                        final String stTime=ds.child("showStartTime").getValue(String.class);
                                        final String hours=ds.child("showWorkHours").getValue(String.class);

                                        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                                                    String inLoopEmail=ds.child("userMail").getValue(String.class);

                                                    assert inLoopEmail != null;
                                                    if (inLoopEmail.equals(email)) {
                                                        char[] dateArr=date.toCharArray();
                                                        char[] modDateArr=new char[date.length()];
                                                        int count=0;

                                                        for (int i=0; i < dateArr.length; i++) {
                                                            if (dateArr[i] == '-')
                                                                count++;
                                                            if (count == 2)
                                                                break;

                                                            modDateArr[i]=dateArr[i];
                                                        }
                                                        name=ds.child("userName").getValue(String.class);
                                                        phone=ds.child("userPhone").getValue(String.class);
                                                        String modTime=String.valueOf(modDateArr) + ", " + stTime + " | " + hours;
                                                        Users item=new Users(email, name, phone, modTime);
                                                        users.add(item);
                                                        adapter=new CustomListViewAdapter(refActivity, R.layout.list_slot, users);
                                                        historyListViewSlot.setAdapter(adapter);
                                                        adapter.notifyDataSetChanged();
                                                    }


                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });


                                    }

                                }


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }

                        });
                    }
                }

                pd.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/


        return parentHolder;
    }

}