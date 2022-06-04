package com.example.hand_bag.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hand_bag.Fragment.Pop_up_detailaf;
import com.example.hand_bag.HelperClass.BagsHelperClass;
import com.example.hand_bag.Pop_up_detaila;
import com.example.hand_bag.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BagsAdapter extends RecyclerView.Adapter<BagsAdapter.ViewHolder>{
    private Context context;
    private List<BagsHelperClass> list;
    public SharedPreferences.Editor myEditor;
    public  SharedPreferences sharedPreferences;

    public BagsAdapter(Context context, List<BagsHelperClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public BagsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.bags_item_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BagsAdapter.ViewHolder holder, int position) {
        final BagsHelperClass coupon = list.get(position);
        holder.txt_title.setText(list.get(position).getTitle());
        holder.txt_author.setText(list.get(position).getAuthor());
        Glide.with(context).load(list.get(position).getImage()).into(holder.img_bag);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                sharedPreferences = view.getContext().getSharedPreferences("login", Context.MODE_PRIVATE);
                myEditor = sharedPreferences.edit();
                String title = String.valueOf(list.get(position).getTitle());
                String url = String.valueOf(list.get(position).getImage());
                String author = String.valueOf(list.get(position).getAuthor());
                String des = String.valueOf(list.get(position).getDescription());

                Log.e("@@title" , title);
                myEditor.putString("title", title);
                myEditor.putString("url", url);
                myEditor.putString("author", author);
                myEditor.putString("des", des);
                myEditor.commit();
                List<Object> objectArrayList = new ArrayList<>();
                objectArrayList.addAll(list);
                Bundle b = new Bundle();
                b.putSerializable("COUPON", (Serializable) coupon);
                b.putString("Slide_Coupon", "1");
                b.putInt("coupon_pos", position);
                b.putString("type", "0");
                b.putSerializable("Coupons_List", (Serializable) objectArrayList);
     /*           final Pop_up_detaila popUpClass = new Pop_up_detaila(context);
                popUpClass.showDialog(context,"");*/
                Pop_up_detailaf detailFragment = new Pop_up_detailaf();
                detailFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.AppDialogTheme);
                detailFragment.setArguments(b);
                FragmentManager fm = ((FragmentActivity) context).getSupportFragmentManager();
                detailFragment.show(fm, "DialogFragment");

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_title,txt_author;
        ImageView img_bag;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_title = itemView.findViewById(R.id.title_bag);
            txt_author = itemView.findViewById(R.id.author_bag);
            img_bag = itemView.findViewById(R.id.img_bag);

        }
    }
}
