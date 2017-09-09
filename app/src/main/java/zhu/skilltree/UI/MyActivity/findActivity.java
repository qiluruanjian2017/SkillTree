package zhu.skilltree.UI.MyActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import zhu.skilltree.Adapter.LoadingView;
import zhu.skilltree.Adapter.MyAdapt;
import zhu.skilltree.R;
import zhu.skilltree.bean.UserInfo;
import zhu.skilltree.bean.group;


public class findActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, AdapterView.OnItemClickListener {
    ListView listview2;
    List<group> data;
    List<group> newdata;
    MyAdapt adapt;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        listview2 = (ListView) findViewById(R.id.listview2);
        LoadingView.createLoadingDialog(this);
        String name = this.getIntent().getStringExtra("name");
        BmobQuery<group> query1 = new BmobQuery<>();
        query1.order("-createdAt");
        BmobQuery<group> query2 = new BmobQuery<>();
        if (name == null) {
            name = this.getIntent().getStringExtra("wode");
            UserInfo textuser = BmobUser.getCurrentUser(UserInfo.class);
            query2.addWhereRelatedTo("group", new BmobPointer(textuser));
        }
        List<BmobQuery<group>> queries = new ArrayList<>();
        queries.add(query1);
        queries.add(query2);
        BmobQuery<group> mainquery = new BmobQuery<>();
        mainquery.and(queries);
        mainquery.addWhereEqualTo("title", name);
        mainquery.findObjects(new FindListener<group>() {
            @Override
            public void done(List<group> list, BmobException e) {
                if (e == null) {
                    adapt = new MyAdapt(findActivity.this, list);
                    listview2.setAdapter(adapt);
                    LoadingView.closeDialog();
                } else {
                    Toast toast = Toast.makeText(findActivity.this, "false", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
//        newdata=new ArrayList<>();

//        update(name);
//        adapt = new MyAdapt(this, newdata);
//        listview2.setAdapter(adapt);


        searchView = (SearchView) findViewById(R.id.searchview);
        searchView.setQuery(name, true);
        searchView.setOnQueryTextListener(this);
        listview2.setOnItemClickListener(this);
    }

    public void update(String na) {
        newdata.clear();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getInfo().contains(na)) {
                newdata.add(data.get(i));
            }
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        LoadingView.createLoadingDialog(this);
        BmobQuery<group> query1 = new BmobQuery<>();
        query1.addWhereEqualTo("title", query);
        query1.findObjects(new FindListener<group>() {
            @Override
            public void done(List<group> list, BmobException e) {
                adapt = new MyAdapt(findActivity.this, list);
                listview2.setAdapter(adapt);
                LoadingView.closeDialog();
            }
        });

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(findActivity.this, xiaozuxinxi.class);
        startActivity(intent);
    }
}
