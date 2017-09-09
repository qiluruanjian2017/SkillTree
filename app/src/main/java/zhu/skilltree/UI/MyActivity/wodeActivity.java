package zhu.skilltree.UI.MyActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

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

public class wodeActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, AdapterView.OnItemClickListener {
    SearchView searchView;
    ListView listView;
    MyAdapt adapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wode);
        listView = (ListView) findViewById(R.id.wodelist);
        UserInfo textuser = BmobUser.getCurrentUser(UserInfo.class);
        BmobQuery<group> query = new BmobQuery<>();
        query.addWhereRelatedTo("group", new BmobPointer(textuser));
        LoadingView.createLoadingDialog(this);
        query.findObjects(new FindListener<group>() {
            @Override
            public void done(List<group> list, BmobException e) {
                if (e == null) {
                    adapt = new MyAdapt(getApplicationContext(), list);
                    listView.setAdapter(adapt);
                    LoadingView.closeDialog();
                } else {
                    Toast toast = Toast.makeText(wodeActivity.this, "false", Toast.LENGTH_LONG);
                    toast.show();
                    LoadingView.closeDialog();
                }
            }
        });


        searchView = (SearchView) findViewById(R.id.wodeserch);
        searchView.setOnQueryTextListener(this);
        listView.setOnItemClickListener(this);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        Intent intent = new Intent(wodeActivity.this, findActivity.class);
        intent.putExtra("wode", query);
        startActivity(intent);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        group group_click = new group();
        group_click = (group) adapt.getItem(position);
        Intent intent = new Intent(wodeActivity.this, groupActivity.class);
        intent.putExtra("groupID", group_click.getObjectId());
        startActivity(intent);

    }
}
