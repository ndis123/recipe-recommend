package com.app.recipe;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private ArrayList<SearchData> items;
    SearchData fInfo;
    int count;
    Context mConText;
    int layout;
    LayoutInflater li;
    String tag = "";
    ItemClickListener itemClickListener;

    public interface ItemClickListener {
        void itemClick(SearchData data);
    }

    public void setClick(ItemClickListener lis){
        itemClickListener = lis;
    }

    public VideoAdapter(Context context, int textViewResourseId, ArrayList<SearchData> items, String tag) {
        this.items = items;
        this.count = items.size();
        this.mConText = context;
        layout = textViewResourseId;
        this.tag = tag;
        li = ((LayoutInflater) mConText.getSystemService(Context.LAYOUT_INFLATER_SERVICE));

    }

    @Override
    public long getItemId(int position) {
        return Integer.toString(position).hashCode();
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_video ,viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        fInfo = items.get(position);

        viewHolder.title.setText(Html.fromHtml(fInfo.getTitle()));
        Glide.with(mConText).load(fInfo.getUrl()).into(viewHolder.image);


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse( "http://youtu.be/" + (items.get(viewHolder.getAdapterPosition()).getVideoId())));

                mConText.startActivity( intent );

                //itemClickListener.itemClick(items.get(viewHolder.getAdapterPosition()));
            }
        });


    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout com_rowid;

        TextView title;
        ImageView image;



        public ViewHolder(View convertView) {
            super(convertView);

            com_rowid = (LinearLayout) convertView.findViewById(R.id.com_rowid);

            title = (TextView)
                    convertView.findViewById(R.id.title);

            image = (ImageView)
                    convertView.findViewById(R.id.image);



        }

    }
}
