package zhu.skilltree.UI.MyFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import zhu.skilltree.Adapter.MyActivityAdapter;
import zhu.skilltree.R;
import zhu.skilltree.bean.MyActivity;

/**
 * Created by zd on 2017/7/19.
 */

public class Frag_activity extends Fragment {
    private RecyclerView recyclerView;
    private SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //初始化布局
        View v = inflater.inflate(R.layout.frag_classandactivity, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.my_classAndActivity_list);
        searchView = (SearchView) v.findViewById(R.id.my_classAndActivity_search);

        //RecyclerView相关
        MyActivityAdapter adapter = new MyActivityAdapter(load());//自定义Adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return v;
    }

    private List load() {
        MyActivity myActivity;
        List<MyActivity> mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            myActivity = new MyActivity("乒乓球" + i);
            mList.add(myActivity);
        }
        return mList;
    }

    private List search(String keyWords) {
        List<MyActivity> mList = new ArrayList<>();

        return mList;
    }
}
