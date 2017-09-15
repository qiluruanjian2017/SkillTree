package zhu.skilltree.UI.MyFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import zhu.skilltree.Adapter.MyActivityAdapter;
import zhu.skilltree.R;
import zhu.skilltree.bean.MyActivity;

/**
 * Created by zd on 2017/7/19.
 */

public class Frag_activity extends Fragment{
    private RecyclerView recyclerView ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //初始化布局
        View v = inflater.inflate(R.layout.frag_classandactivity,container,false);
        recyclerView = (RecyclerView)v.findViewById(R.id.my_classAndActivity_list);
        load();
        return v;
    }

    private void load(){
        BmobQuery<MyActivity> query = new BmobQuery<MyActivity>();
        query.setLimit(10);
        query.addWhereEqualTo("status","unselected");
        query.findObjects(new FindListener<MyActivity>() {
            @Override
            public void done(List<MyActivity> list, BmobException e) {
                MyActivityAdapter adapter = new MyActivityAdapter(list);//自定义Adapter
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(adapter);
            }
        });
    }

    private List search(String keyWords){
        List<MyActivity> mList = new ArrayList<>();

        return mList;
    }
}