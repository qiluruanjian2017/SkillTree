package zhu.skilltree.UI.MyActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobRealTimeData;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.ValueEventListener;
import zhu.skilltree.Adapter.ChatAdapt;
import zhu.skilltree.Adapter.LoadingView;
import zhu.skilltree.R;
import zhu.skilltree.bean.Message;
import zhu.skilltree.bean.group;

public class groupActivity extends AppCompatActivity implements View.OnClickListener {
    ListView listView;
    EditText editText;
    TextView title;
    Button button;
    ChatAdapt adapter;
    List data;
    group group_text;
    BmobRealTimeData bmobRealTimeData;
    ImageButton close, chat_his;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        String ObjectID = this.getIntent().getStringExtra("groupID");
        LoadingView.createLoadingDialog(this);
        init();
        final BmobQuery<group> query_group = new BmobQuery<>();
        query_group.getObject(ObjectID, new QueryListener<group>() {
            @Override
            public void done(group group, BmobException e) {
                if (e == null) {
                    group_text = group;
                    getnewMessage();
                    title.setText(group_text.getTitle());
                    BmobQuery<Message> query_message = new BmobQuery<>();
                    query_message.addWhereEqualTo("group", new BmobPointer(group_text));
                    query_message.setLimit(10);
                    query_message.include("user");
                    query_message.order("-createdAt");
                    query_message.findObjects(new FindListener<Message>() {
                        @Override
                        public void done(List<Message> list, BmobException e) {
                            if (e == null) {
                                if (list.size() == 0) {
                                    LoadingView.closeDialog();
                                    return;
                                }
                                int size = list.size();
                                for (int i = 0; i < size; i++) {
                                    data.add(list.get(size - i - 1));
                                }
                                LoadingView.closeDialog();
                                adapter.notifyDataSetChanged();
                            } else {
                                Toast.makeText(groupActivity.this, "读取message失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(groupActivity.this, "读取group失败", Toast.LENGTH_SHORT).show();

                }
            }
        });
        listView.setAdapter(adapter);
        listView.setSelection(listView.getBottom());
        button.setOnClickListener(this);
    }

    public void init() {
        listView = (ListView) findViewById(R.id.chat_list);
        title = (TextView) findViewById(R.id.chat_title);
        editText = (EditText) findViewById(R.id.chat_edit);
        button = (Button) findViewById(R.id.chat_button);
        close = (ImageButton) findViewById(R.id.chat_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        chat_his = (ImageButton) findViewById(R.id.chat_his);
        chat_his.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(groupActivity.this, xiaozuxinxi_wode.class);
                intent.putExtra("groupdID", group_text.getObjectId());
                startActivity(intent);
            }
        });
        data = new ArrayList<Message>();
        adapter = new ChatAdapt(data, this);
    }

    @Override
    public void onClick(View v) {
        final Message message = new Message();
        message.setMesType("Text");
        message.setInfo(editText.getText().toString());
        message.setUser(BmobUser.getCurrentUser());
        message.setGroup(group_text);
        data.add(message);
        adapter.notifyDataSetChanged();
        listView.setSelection(listView.getBottom());

        message.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {

                    group_text.increment("message_count");
                    group_text.update(new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {

                                Toast.makeText(groupActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                            } else {
                                data.remove(data.size() - 1);
                                adapter.notifyDataSetChanged();
                                Toast.makeText(groupActivity.this, "发送失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    data.remove(data.size() - 1);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(groupActivity.this, "发送失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getnewMessage() {
        bmobRealTimeData = new BmobRealTimeData();
        bmobRealTimeData.start(new ValueEventListener() {
            @Override
            public void onConnectCompleted(Exception e) {
                if (bmobRealTimeData.isConnected()) {
                    bmobRealTimeData.subRowUpdate("group", group_text.getObjectId());

                } else {


                }
            }

            @Override
            public void onDataChange(JSONObject jsonObject) {
                BmobQuery<Message> query_newMessage = new BmobQuery<Message>();
                query_newMessage.addWhereEqualTo("group", new BmobPointer(group_text));
                query_newMessage.setLimit(1);
                query_newMessage.include("user");
                query_newMessage.order("-createdAt");
                query_newMessage.findObjects(new FindListener<Message>() {
                    @Override
                    public void done(List<Message> list, BmobException e) {
                        if (e == null) {
                            if (!list.get(0).getUser().getObjectId().equals(BmobUser.getCurrentUser().getObjectId())) {
                                data.addAll(list);
                                adapter.notifyDataSetChanged();
                            }

                        } else {

                        }

                    }
                });
            }
        });
    }
}
