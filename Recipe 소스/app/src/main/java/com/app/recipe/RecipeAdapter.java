package com.app.recipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private ArrayList<RecipeData> items;
    RecipeData fInfo;
    int count;
    Context mConText;
    int layout;
    LayoutInflater li;
    String tag = "";
    ItemClickListener itemClickListener;

    public interface ItemClickListener {
        void itemClick(RecipeData data);
    }

    public void setClick(ItemClickListener lis){
        itemClickListener = lis;
    }

    public RecipeAdapter(Context context, int textViewResourseId, ArrayList<RecipeData> items, String tag) {
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

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_recipe ,viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        fInfo = items.get(position);

        if(fInfo.getTitle().equals("")){
            viewHolder.title.setText(fInfo.getTitle_re());
        }else{
            viewHolder.title.setText(fInfo.getTitle());
        }

        viewHolder.contents.setText(fInfo.getDescription());
        viewHolder.jae.setText(fInfo.getC_material());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.itemClick(items.get(viewHolder.getAdapterPosition()));
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
        TextView contents;
        TextView jae;


        public ViewHolder(View convertView) {
            super(convertView);

            com_rowid = (LinearLayout) convertView.findViewById(R.id.com_rowid);

            title = (TextView)
                    convertView.findViewById(R.id.title);

            contents = (TextView)
                    convertView.findViewById(R.id.contents);

            jae = (TextView)
                    convertView.findViewById(R.id.jae);


        }

    }
}
