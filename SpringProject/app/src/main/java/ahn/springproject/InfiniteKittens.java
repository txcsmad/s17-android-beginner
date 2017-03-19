package ahn.springproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class InfiniteKittens extends AppCompatActivity {

    Context context;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infinite_kittens);
        context = this;
        setupRecyclerView();
    }

    public void setupRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.kitten_recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        KittenAdapter kittenAdapter = new KittenAdapter(5000);
        recyclerView.setAdapter(kittenAdapter);
    }


    public class KittenAdapter extends RecyclerView.Adapter<ViewHolder> {

        int size;

        public KittenAdapter(int size) {
            this.size = size;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.kitten_image, null);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            int random = (int) (Math.random() * 2);
            String cat = "";
            if(random == 1)
                cat = "http://theoldreader.com/kittens/600/400";
            else
                cat = "http://thecatapi.com/api/images/get?format=src&type=png";
            Picasso.with(context).load(cat).into(holder.image);
        }

        @Override
        public int getItemCount() {
            return size;
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;

        public ViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.kitten_image);
        }
    }
}
