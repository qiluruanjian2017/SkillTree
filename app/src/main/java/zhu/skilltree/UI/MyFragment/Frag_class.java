package zhu.skilltree.UI;

/**
 * Created by zd on 2017/7/19.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import zhu.skilltree.Adapter.MyClassAdapter;
import zhu.skilltree.R;
import zhu.skilltree.bean.MyClass;


/**
 * Created by janiszhang on 2016/6/6.
 */

public class Frag_class extends Fragment {
    private static final String TAG = "Frag_class";

    private RecyclerView recyclerView ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //初始化布局
        View v = inflater.inflate(R.layout.frag_classandactivity,container,false);
        recyclerView = (RecyclerView)v.findViewById(R.id.my_classAndActivity_list);

        //RecyclerView相关
//        MyClassAdapter adapter = new MyClassAdapter(load());//自定义Adapter
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(adapter);
//        uploadItems();
        load();
        return v;

    }
//    private void uploadItems(){
//        Log.d(TAG, "uploadItems: ");
//        MyClass c1,c2,c3,c4,c5;
//        c1 = new MyClass("高等数学","严谨");
//        c2 = new MyClass("java程序设计教程","卢旭东");
//        c3 = new MyClass("数据库","哈哈哈");
//        c4 = new MyClass("数据结构","嘿嘿嘿");
//        c5 = new MyClass("离散数学","略略略");
//        SaveListener<String> listener = new SaveListener<String>() {
//            @Override
//            public void done(String s, BmobException e) {
//                if(e == null){
//                    Log.d(TAG, "done: succeed:"+s);
//                } else
//                Log.d(TAG, "done: "+e.getMessage());
//            }
//        };
//        c1.save(listener);
//        c2.save(listener);
//        c3.save(listener);
//        c4.save(listener);
//        c5.save(listener);
//    }

    private void load(){

        BmobQuery<MyClass> query = new BmobQuery<MyClass>();
        query.setLimit(10);
        query.addWhereEqualTo("status","unselected");
        query.findObjects(new FindListener<MyClass>() {
            @Override
            public void done(List<MyClass> list, BmobException e) {
                MyClassAdapter adapter = new MyClassAdapter(list);//自定义Adapter
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(adapter);
            }
        });

//        MyClass myClass;
//        List<MyClass> mList = new ArrayList<>();
//        for(int i = 0;i<10;i++){
//            myClass = new MyClass("高等数学"+i,"颜谨","123");
//            mList.add(myClass);
//        }
//        return  mList;
    }

//    private List search(String keyWords){
//        List<MyClass> mList = new ArrayList<>();
//
//        return mList;
//    }

}