package zhu.skilltree.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import zhu.skilltree.R;
import zhu.skilltree.bean.MyActivity;

/**
 * This is SkillTree
 * Created by qidi on 2017/8/16.
 * Tel:18340018130
 * E-mail:sevenddddddd@gmail.com
 */

public class MyActivityAdapter extends RecyclerView.Adapter {
    List<MyActivity> mList;

    public MyActivityAdapter(List list) {
        super();
        mList = list;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;


        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.my_activity_name);

        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_activity, null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder mholder = (ViewHolder) holder;
        MyActivity myActivity = mList.get(position);
        mholder.name.setText(myActivity.getName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

}
