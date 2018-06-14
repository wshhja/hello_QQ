package com.example.hhj.hello.utils;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hhj.hello.Activity.MessageFormActivity;
import com.example.hhj.hello.DB.UserDAO;
import com.example.hhj.hello.Model.Message;
import com.example.hhj.hello.R;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private List<Message> mList;
    private int user_id;

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        TextView mTextView_Name;
        TextView mTextView_Message;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView=itemView.findViewById(R.id.iv_list_item_head);
            mTextView_Name=itemView.findViewById(R.id.tv_list_item_name);
            mTextView_Message=itemView.findViewById(R.id.tv_list_item_content);
        }
    }

    public MessageAdapter(List<Message> list){
        user_id=SharePUtil.Get();
        mList=list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_left_fragment,parent,false);
        final ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Message message=mList.get(position);
        if(user_id==message.getUserSend()){
            holder.mTextView_Name.setText(UserDAO.GetById(message.getUserRec()).getUserName());
        }
        else{
            holder.mTextView_Name.setText(UserDAO.GetById(message.getUserSend()).getUserName());
        }
        holder.mTextView_Message.setText(message.getContent());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity currentActivity = (Activity) v .getContext();
                Intent intent = new Intent(currentActivity.getApplication(),MessageFormActivity.class);
                intent.putExtra("id1",message.getUserRec());
                intent.putExtra("id2",message.getUserSend());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(MyApplication.getContext(),intent,null);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }
}
