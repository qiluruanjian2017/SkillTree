package zhu.skilltree.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import zhu.skilltree.R;
import zhu.skilltree.bean.MyClass;

/**
 * This is SkillTree
 * Created by qidi on 2017/8/16.
 * Tel:18340018130
 * E-mail:sevenddddddd@gmail.com
 */

public class MyClassAdapter extends RecyclerView.Adapter {
    List<MyClass> mList;

    public MyClassAdapter(List list){
        super();
        mList = list;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView teacher;
        TextView credit;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.my_class_name);
            teacher = (TextView)itemView.findViewById(R.id.my_class_teacher);
        }
    }
    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_class,null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder mholder = (ViewHolder)holder;
        MyClass myClass = mList.get(position);
        mholder.name.setText(myClass.getName());
        mholder.teacher.setText(myClass.getTeacher());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

}
