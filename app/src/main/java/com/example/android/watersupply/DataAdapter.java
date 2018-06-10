package com.example.android.watersupply;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Anonymous on 4/8/2018.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyviewHolder> {
    private List<Modal> dataList;
    private Context mCtx;

    public class MyviewHolder extends RecyclerView.ViewHolder{
        public TextView username,topic,detail;



        public MyviewHolder(View itemView) {
            super( itemView );

            username= (TextView) itemView.findViewById(R.id.username);
            topic = (TextView) itemView.findViewById(R.id.topic);
            detail=(TextView)itemView.findViewById( R.id.detail);

        }
    }

    public DataAdapter(Context mCtx, List<Modal> dataList) {
        this.mCtx= mCtx;
        this.dataList = dataList;
    }


    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView=LayoutInflater.from(mCtx).inflate( R.layout.single_list_item,parent,false);
         return new MyviewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyviewHolder holder, int position) {
        Modal modal = dataList.get(position);
        holder.username.setText(String.valueOf(modal.getUsername()));
        holder.topic.setText(String.valueOf( modal.getTopic()));
        holder.detail.setText(String.valueOf( modal.getDetail()));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}
