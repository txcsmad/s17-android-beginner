package ahn.springproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FeedActivity extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("data");

    ArrayList<String> urls, captions, users, timestamps;
    FeedAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        urls = new ArrayList<>();
        captions = new ArrayList<>();
        timestamps = new ArrayList<>();
        users = new ArrayList<>();

        setUpRecyclerView();
    }

    public void setUpRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.feed_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new FeedAdapter(this, captions, timestamps, urls, users);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.compose) {
            Intent intent = new Intent(this, ComposeActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public class FeedAdapter extends RecyclerView.Adapter<ViewHolder> {

        private ArrayList<String> myUrls, myCaptions, myTimestamps, myUsers;
        private Context context;

        public FeedAdapter(Context context, ArrayList<String> myCaptions, ArrayList<String> myTimestamps,
                             ArrayList<String> myUrls, ArrayList<String> myUsers) {

            this.context = context;
            this.myCaptions = myCaptions;
            this.myTimestamps = timestamps;
            this.myUsers = myUsers;
            this.myUrls = myUrls;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_item, null);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.timeTextView.setText(myTimestamps.get(position));
            holder.captionTextView.setText(myCaptions.get(position));
            holder.usernameTextView.setText(myUsers.get(position));
            Picasso.with(context).load(myUrls.get(position)).rotate(90).into(holder.image);

            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(FeedActivity.this, ViewImageActivity.class);
                    intent.putExtra("url", myUrls.get(position));
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return myCaptions.size();
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView usernameTextView;
        TextView captionTextView;
        TextView timeTextView;

        public ViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.image_preview);
            usernameTextView = (TextView) v.findViewById(R.id.username_textview);
            captionTextView = (TextView) v.findViewById(R.id.caption_textview);
            timeTextView = (TextView) v.findViewById(R.id.time_stamp);
        }
    }
}
