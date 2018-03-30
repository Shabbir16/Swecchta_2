package com.example.shabbir.swecchta_2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class LeaderboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LeaderBoardAdapter mAdapter;
    private ProgressBar progressBar;
    private BottomNavigation bottomNavigation;
    private FragmentActivity myContext;
    private RecyclerView.LayoutManager layoutManager;
    private List<LeaderBoard> arr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        progressBar=(ProgressBar)findViewById(R.id.leaderProgress);
        arr=new ArrayList<>();

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
//                Toast.makeText(getApplicationContext(),"This is a click",Toast.LENGTH_LONG).show();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "I am happy to share my contribution, till now I have Scored "+arr.get(position).getScore()
                +" And I identified "+arr.get(position).getDustbin()
                +" And helped government solve "+ arr.get(position).getPost()+" dump problem");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

        loadData();
    }

    void loadData(){
        SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(this);
//        database = FirebaseDatabase.getInstance();
//        mStorage = FirebaseStorage.getInstance();
        String userID = shared.getString("uid","");

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("leaderBoard");
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot
                        collectPhoneNumbers((Map<String,Object>) dataSnapshot.getValue());
                        if(!arr.isEmpty())
                            mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });
    }

    private void collectPhoneNumbers(Map<String,Object> users) {


        //ArrayList<String> phoneNumbers = new ArrayList<>();

        if(users == null){
//            arr.add(new MyPost("qwerty","Unkknown",0,"qazxswedcvfrtb","Null","Null","Null",0,"Null","Null",
//                    "Null"));
            return;
        }
        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : users.entrySet()){

            //Get user map
            Map singleUser = (Map) entry.getValue();
            //Get phone field and append to list
            int dustbin = Integer.parseInt(singleUser.get("dustbin")+"");
            String image = (String) singleUser.get("image");
            String name = (String) singleUser.get("name");
            int post = Integer.parseInt(singleUser.get("post")+"");
            int score = Integer.parseInt(singleUser.get("score")+"");


            arr.add(new LeaderBoard(image,name,score,post,dustbin));

        }

        Collections.sort(arr, new Comparator<LeaderBoard>() {
            @Override
            public int compare(LeaderBoard leaderBoard, LeaderBoard t1) {
                if(leaderBoard.getScore() < t1.getScore())
                    return 1;
                else
                    return -1;
//                return leaderBoard.getScore().compareTo(t1.getScore());
            }


        });

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new LeaderBoardAdapter(arr);
        recyclerView.setAdapter(mAdapter);
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
//        System.out.println(phoneNumbers.toString());
        // Toast.makeText(getContext(),phoneNumbers.toString(),Toast.LENGTH_LONG).show();
    }


}
