package com.example.shabbir.swecchta_2;



import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Notification extends Fragment {
    public static Notification newInstance() {
        Notification fragment = new Notification();
        return fragment;
    }

    String msg;
    TextView notify;
    RecyclerView notification,dont;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notification, container, false);
        notify = (TextView)view.findViewById(R.id.notify);
        notification = (RecyclerView)view.findViewById(R.id.notificationRecyclerView);
        dont = (RecyclerView)view.findViewById(R.id.recyclerView2);

        notification.setHasFixedSize(true);
        dont.setHasFixedSize(true);




       // loadData("notification");
        loadData2("does",notification);
        loadData2("dont",dont);
        return view;
    }

    void showData(RecyclerView recyclerView,List<String> myArr){
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        DoesDontAdapter mAdapter;
        mAdapter = new DoesDontAdapter(myArr);
        recyclerView.setAdapter(mAdapter);

    }


    void loadData2(String loc, final RecyclerView recyclerView){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("doesdont").child(loc);//.child("posts");
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot
                        List<String> arr=collectPhoneNumbers((Map<String, Object>) dataSnapshot.getValue());


                        showData(recyclerView,arr);

//                        notify.setText(msg);


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });
    }

//    void loadData(String loc){
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
//        String userId = sharedPreferences.getString("uid", "");
//
//
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(loc).child(userId);//.child("posts");
//        ref.addListenerForSingleValueEvent(
//                new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        //Get map of users in datasnapshot
////                        List<String> arr=collectPhoneNumbers((Map<String, Object>) dataSnapshot.getValue());
////                        showData(notification,arr);
//                        Map<String, Object> users = (Map<String, Object>) dataSnapshot.getValue();
//
//
//                        String noti="";
//
//                        for (Map.Entry<String, Object> entry : users.entrySet()) {
//
//                            Map singleUser = (Map) entry.getValue();
//                            noti=(String) singleUser.get("msg");
//                            //arr.add((String) singleUser.get("msg"));
//                        }
//                        notify.setText(noti);
//
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        //handle databaseError
//                    }
//                });sdsdsd
//    }

    private List<String> collectPhoneNumbers(Map<String, Object> users) {


        List<String> arr= new ArrayList<>();

        if (users == null) {
            return arr;
         //   Toast.makeText(getContext(),"I am here",Toast.LENGTH_LONG).show();
        }
        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : users.entrySet()) {

            Map singleUser = (Map) entry.getValue();
            arr.add((String) singleUser.get("msg"));
        }
        return arr;
    }
}