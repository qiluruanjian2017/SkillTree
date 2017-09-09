package zhu.skilltree.UI.MyActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;
import zhu.skilltree.Adapter.LoadingView;
import zhu.skilltree.R;
import zhu.skilltree.bean.UserInfo;
import zhu.skilltree.bean.group;

public class chuangjianActivity extends AppCompatActivity {
    EditText name, label, info;


    String path;
    group newgroup = new group();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuangjian);

        name = (EditText) findViewById(R.id.editText3);
        label = (EditText) findViewById(R.id.editText);
        info = (EditText) findViewById(R.id.editText4);


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.chuanjianbar, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.dui:
                LoadingView.createLoadingDialog(this);
                final UserInfo leader = BmobUser.getCurrentUser(UserInfo.class);
                newgroup.setLeader(leader);
                final BmobRelation relation = new BmobRelation();
                relation.add(leader);
                newgroup.setMembers(relation);
                newgroup.setTitle(name.getText().toString());
                newgroup.setType(label.getText().toString());
                newgroup.setInfo(info.getText().toString());
                final BmobFile icon = new BmobFile(new File(path));
                icon.upload(new UploadFileListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            newgroup.setIcon(icon);
                            newgroup.save(new SaveListener<String>() {
                                @Override
                                public void done(String s, BmobException e) {
                                    if (e == null) {
                                        BmobQuery query = new BmobQuery();
                                        query.getObject(s, new QueryListener<group>() {

                                            @Override
                                            public void done(group object, BmobException e) {
                                                if (e == null) {
                                                    BmobRelation relation1 = new BmobRelation();
                                                    relation1.add(object);
                                                    leader.setGroup(relation1);
                                                    leader.update(new UpdateListener() {
                                                        @Override
                                                        public void done(BmobException e) {
                                                            if (e == null) {
                                                                LoadingView.closeDialog();
                                                                Intent intent = new Intent(chuangjianActivity.this, wodeActivity.class);

                                                                startActivity(intent);

                                                                finish();
                                                            } else {
                                                                LoadingView.closeDialog();
                                                                Toast toast = Toast.makeText(chuangjianActivity.this, "false_3", Toast.LENGTH_LONG);
                                                                toast.show();
                                                            }
                                                        }
                                                    });
                                                } else {
                                                    LoadingView.closeDialog();
                                                    Toast toast = Toast.makeText(chuangjianActivity.this, "false_2", Toast.LENGTH_LONG);
                                                    toast.show();
                                                }
                                            }

                                        });

                                    } else {
                                        LoadingView.closeDialog();
                                        Toast toast = Toast.makeText(chuangjianActivity.this, "false_1", Toast.LENGTH_LONG);
                                        toast.show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(chuangjianActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                break;
            case R.id.cuo:
                finish();
                break;
        }
        return true;
    }

}
