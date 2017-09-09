package zhu.skilltree.UI.MyActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import zhu.skilltree.Adapter.ChatAdapt;
import zhu.skilltree.Adapter.LoadingView;
import zhu.skilltree.R;
import zhu.skilltree.bean.Message;
import zhu.skilltree.bean.group;

public class historyActivity extends AppCompatActivity implements AbsListView.OnScrollListener {
    ListView history;
    ChatAdapt adapt;
    ImageButton close;
    group group_this;
    List<Message> data;
    int last_index, total_index;
    boolean isLoading;
    View loadingmore;
    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        LoadingView.createLoadingDialog(this);
        String id = this.getIntent().getStringExtra("history_ID");
        final BmobQuery<group> query_group = new BmobQuery<>();
        query_group.getObject(id, new QueryListener<group>() {
            @Override
            public void done(group group, BmobException e) {
                if (e == null) {
                    group_this = group;
                    BmobQuery<Message> query_message = new BmobQuery<Message>();
                    query_message.setLimit(10);
                    query_message.addWhereEqualTo("group", new BmobPointer(group_this));
                    query_message.include("user");
                    query_message.order("createdAt");
                    query_message.findObjects(new FindListener<Message>() {
                        @Override
                        public void done(List<Message> list, BmobException e) {
                            if (e == null) {
                                data.addAll(list);
                                adapt.notifyDataSetChanged();
                                LoadingView.closeDialog();
                            } else {
                                Toast.makeText(historyActivity.this, "读取message失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(historyActivity.this, "读取group失败", Toast.LENGTH_SHORT).show();

                }
            }
        });
        init();
    }

    private void init() {
        history = (ListView) findViewById(R.id.history_list);
        data = new ArrayList<>();
        adapt = new ChatAdapt(data, this);
        inflater = LayoutInflater.from(this);
        loadingmore = inflater.inflate(R.layout.loadingmore, null);
        loadingmore.setVisibility(View.GONE);
        history.addFooterView(loadingmore, null, false);
        history.setOnScrollListener(this);
        history.setAdapter(adapt);
        close = (ImageButton) findViewById(R.id.history_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (last_index == total_index && (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE)) {
            if (!isLoading) {
                isLoading = true;

                loadingmore.setVisibility(View.VISIBLE);
                onLoad();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        last_index = firstVisibleItem + visibleItemCount;
        total_index = totalItemCount;
    }

    private void onLoad() {
        BmobQuery<Message> query = new BmobQuery<>();
        query.setSkip(data.size());
        query.setLimit(10);
        query.findObjects(new FindListener<Message>() {
            @Override
            public void done(List<Message> list, BmobException e) {
                if (e == null) {
                    if (list.size() == 0) {
                        Toast toast = Toast.makeText(historyActivity.this, "no more", Toast.LENGTH_LONG);
                        toast.show();
                        loadingmore.setVisibility(View.GONE);
                        isLoading = false;
                    } else {
                        data.addAll(list);
                        adapt.notifyDataSetChanged();
                        loadingmore.setVisibility(View.GONE);
                        isLoading = false;
                    }
                } else {
                    loadingmore.setVisibility(View.GONE);
                    isLoading = false;
                    Toast toast = Toast.makeText(historyActivity.this, "false", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }
}
