package zhu.skilltree.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import zhu.skilltree.R;
import zhu.skilltree.bean.MyActivity;
import zhu.skilltree.bean.MyClass;

/**
 * This is SkillTree
 * Created by qidi on 2017/8/16.
 * Tel:18340018130
 * E-mail:sevenddddddd@gmail.com
 */

public class MyActivityAdapter extends RecyclerView.Adapter {
    List<MyActivity> mList;

    public MyActivityAdapter(List list){
        super();
        mList = list;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView organizer;
        Button select;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.my_activity_name);
            organizer = (TextView)itemView.findViewById(R.id.my_activity_organizer);
            select = (Button)itemView.findViewById(R.id.my_activity_select);
        }
    }
    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_activity,null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder mholder = (ViewHolder)holder;
        MyActivity myActivity = mList.get(position);
        mholder.name.setText(myActivity.getName());
        mholder.organizer.setText(myActivity.getOrganizer());
        mholder.select.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final MyActivity activity = mList.get(position);
                Log.d("bmob", "act:"+activity.getName()+activity.getOrganizer()+activity.getStatus());
                activity.setStatus("doing");
                activity.update(activity.getObjectId(), new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            Log.i("bmob","更新成功");
                            mholder.select.setText("已选择");
                        }else{
                            Log.i("bmob","更新失败："+e.getMessage()+","+e.getErrorCode()+" "+activity.getObjectId());
                        }
                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

}
