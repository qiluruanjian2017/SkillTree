package zhu.skilltree.UI.MyActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import zhu.skilltree.Adapter.LoadingView;
import zhu.skilltree.R;
import zhu.skilltree.bean.group;

public class xiaozuxinxi_wode extends AppCompatActivity {
    TextView group_title, group_type, group_info, member_num;
    Button confirm;
    ImageButton pull_off;
    group group_this;
    ListView member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiaozuxinxi_wode);
        init();
        LoadingView.createLoadingDialog(this);
        final String ObjectID = this.getIntent().getStringExtra("groupdID");
        BmobQuery<group> query = new BmobQuery<>();
        query.getObject(ObjectID, new QueryListener<group>() {
            @Override
            public void done(group group, BmobException e) {
                if (e == null) {
                    group_this = group;
                    group_title.setText(group.getTitle());
                    group_type.setText(group.getType());
                    group_info.setText(group.getInfo());
                    BmobQuery<BmobUser> query1 = new BmobQuery();
                    query1.addWhereRelatedTo("members", new BmobPointer(group_this));
                    query1.findObjects(new FindListener<BmobUser>() {
                        @Override
                        public void done(List<BmobUser> list, BmobException e) {
                            if (e == null) {
                                List<String> name = new ArrayList<String>();
                                member_num.setText("小组成员：" + list.size() + "人");
//                                name.add("这");name.add("里");name.add("没");name.add("弄");name.add("好");
                                for (int i = 0; i < list.size(); i++) {
                                    name.add(list.get(i).getUsername());
                                }
                                member.setAdapter(new ArrayAdapter<String>(xiaozuxinxi_wode.this, android.R.layout.simple_expandable_list_item_1, android.R.id.text1, name));
                                LoadingView.closeDialog();
                            } else {
                                Toast toast = Toast.makeText(xiaozuxinxi_wode.this, "false_member", Toast.LENGTH_LONG);
                                toast.show();
                                LoadingView.closeDialog();
                            }
                        }
                    });

                } else {
                    Toast toast = Toast.makeText(xiaozuxinxi_wode.this, "false", Toast.LENGTH_LONG);
                    toast.show();
                    LoadingView.closeDialog();
                }
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(xiaozuxinxi_wode.this, historyActivity.class);
                intent.putExtra("history_ID", group_this.getObjectId());
                startActivity(intent);
            }
        });
        pull_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void init() {
        group_title = (TextView) findViewById(R.id.xinix_wode_name);
        group_type = (TextView) findViewById(R.id.xinix_wode_title);
        group_info = (TextView) findViewById(R.id.xinix_wode_info);
        confirm = (Button) findViewById(R.id.button_his);
        pull_off = (ImageButton) findViewById(R.id.xinix_wode_close);
        member = (ListView) findViewById(R.id.list_wode_chengyuan);
        member_num = (TextView) findViewById(R.id.xinix_wode_number);
    }
}
