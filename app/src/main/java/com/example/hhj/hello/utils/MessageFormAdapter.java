package com.example.hhj.hello.utils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hhj.hello.Model.Message;
import com.example.hhj.hello.R;

import java.util.List;

public class MessageFormAdapter extends RecyclerView.Adapter<MessageFormAdapter.ViewHolder> {
    private List<Message> mList;
    private int user_id;

    public MessageFormAdapter(List<Message> messages){
        user_id=SharePUtil.Get();
        mList=messages;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_message_form,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message message=mList.get(position);
        int id_rec=message.getUserRec();
        if(id_rec==user_id){
            holder.mLinearLayout_right.setVisibility(View.GONE);
            holder.mTextView_left.setText(message.getContent());
        }
        else {
            holder.mLinearLayout_left.setVisibility(View.GONE);
            holder.mTextView_right.setText(message.getContent());
        }
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView_left;
        ImageView mImageView_right;
        TextView mTextView_left;
        TextView mTextView_right;
        LinearLayout mLinearLayout_left;
        RelativeLayout mLinearLayout_right;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView_left=itemView.findViewById(R.id.left_part_head);
            mImageView_right=itemView.findViewById(R.id.right_part_head);
            mTextView_left=itemView.findViewById(R.id.left_part_tv);
            mTextView_right=itemView.findViewById(R.id.right_part_tv);
            mLinearLayout_left=itemView.findViewById(R.id.left_part);
            mLinearLayout_right=itemView.findViewById(R.id.right_part);
        }
    }
}
