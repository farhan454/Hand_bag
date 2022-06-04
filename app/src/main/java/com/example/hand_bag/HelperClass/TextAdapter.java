package com.example.hand_bag.HelperClass;

import android.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hand_bag.DashBoardActivity;
import com.example.hand_bag.Fragment.FragmentBags;
import com.example.hand_bag.Fragment.FragmentCloth;
import com.example.hand_bag.Fragment.FragmentShoes;
import com.example.hand_bag.R;

import java.util.List;

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.ViewHolder> {
    Context context;
    List<TxtRecycleHelperClass> list;
    Fragment fragment=null;
    FrameLayout frame_container;
    int index=-1;

    public TextAdapter(Context context, List<TxtRecycleHelperClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TextAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.txt_layout, parent, false);
        return new TextAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TextAdapter.ViewHolder holder, int position) {
        holder.textView.setText(list.get(position).getTxt());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = position;
                notifyDataSetChanged();
                String txt=list.get(position).getTxt();
                if (txt.equalsIgnoreCase("Clothes")){
                    FragmentCloth newFragment = new FragmentCloth();


                    DashBoardActivity mainActivity = (DashBoardActivity) view.getContext();
                    mainActivity.getFragmentManager().beginTransaction()
                            .replace(R.id.frame_container, newFragment)
                            .addToBackStack(null)
                            .commit();
                }else if (txt.equalsIgnoreCase("Bags")){
                    FragmentBags fragmentBags=new FragmentBags();
                    DashBoardActivity mainActivity = (DashBoardActivity) view.getContext();
                    mainActivity.getFragmentManager().beginTransaction()
                            .replace(R.id.frame_container, fragmentBags)
                            .addToBackStack(null)
                            .commit();
                }else {
                    if (txt.equalsIgnoreCase("Shoes")){
                        FragmentShoes fragmentBags=new FragmentShoes();
                        DashBoardActivity mainActivity = (DashBoardActivity) view.getContext();
                        mainActivity.getFragmentManager().beginTransaction()
                                .replace(R.id.frame_container, fragmentBags)
                                .addToBackStack(null)
                                .commit();
                    }
                }

            }
        });
        if (index == position){
            holder.textView.setTextColor(context.getResources().getColor(R.color.white));
        } else {
            holder.textView.setTextColor(context.getResources().getColor(R.color.black));
        }
        if (index == position){
            holder.relativeLayout.setBackground(context.getDrawable(R.drawable.item_view));
        } else {
            holder.relativeLayout.setBackground(context.getDrawable(R.drawable.unselect_item));
        }
    }




    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txt);
            frame_container=itemView.findViewById(R.id.frame_container);
            relativeLayout=itemView.findViewById(R.id.relative_Layout);

        }
    }
}
