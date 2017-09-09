package zhu.skilltree.UI.MyFragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import zhu.skilltree.Adapter.LoadingView;
import zhu.skilltree.Adapter.MyAdapt;
import zhu.skilltree.R;
import zhu.skilltree.UI.MyActivity.chuangjianActivity;
import zhu.skilltree.UI.MyActivity.findActivity;
import zhu.skilltree.UI.MyActivity.wodeActivity;
import zhu.skilltree.UI.MyActivity.xiaozuxinxi;
import zhu.skilltree.bean.group;

/**
 * Created by zd on 2017/7/19.
 */

public class Frag_group extends Fragment implements SearchView.OnQueryTextListener, View.OnClickListener, AdapterView.OnItemClickListener, AbsListView.OnScrollListener {
    List<group> data;
    MyAdapt adapt;
    SearchView searchView;
    ListView listView;
    int last_index, total_index;
    boolean isLoading;
    View loadingmore;
    LayoutInflater inflater;

    private View contentView;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentView = inflater.inflate(R.layout.im_activity_main, container, false);
        init();
        BmobQuery<group> query = new BmobQuery<group>();
        query.setLimit(5);
        query.findObjects(new FindListener<group>() {
            @Override
            public void done(List<group> list, BmobException e) {
                if (e == null) {
                    data.addAll(list);
                    adapt.notifyDataSetChanged();
                    LoadingView.closeDialog();
                } else {

                }
            }
        });
//        getData = new getData();

//        data = getData.getallData();
//        adapt = new MyAdapt(this, data);
//        listView.setAdapter(adapt);


        searchView = (SearchView) contentView.findViewById(R.id.sv);
//        searchView.setIconifiedByDefault(false);
//        searchView.setQueryHint("查找");
        searchView.setOnQueryTextListener(this);

        FloatingActionButton fab1 = (FloatingActionButton) contentView.findViewById(R.id.fab_1);
        fab1.setOnClickListener(this);
        FloatingActionButton fab2 = (FloatingActionButton) contentView.findViewById(R.id.fab_2);
        fab2.setOnClickListener(this);


        listView.setOnItemClickListener(this);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this.getContext()).addApi(AppIndex.API).build();
        return contentView;
    }

    private void init() {
        LoadingView.createLoadingDialog(this.getContext());
        data = new ArrayList<>();
        inflater = LayoutInflater.from(this.getContext());
        loadingmore = inflater.inflate(R.layout.loadingmore, null);
        loadingmore.setVisibility(View.GONE);
        listView = (ListView) contentView.findViewById(R.id.listview);
        listView.addFooterView(loadingmore, null, false);
        listView.setOnScrollListener(this);
        adapt = new MyAdapt(this.getContext(), data);
        listView.setAdapter(adapt);
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        InputMethodManager mInputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mInputMethodManager.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
        Intent intent = new Intent();
        intent.setClass(this.getContext(), findActivity.class);
        intent.putExtra("name", query);
        startActivity(intent);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_1:
                Intent intent = new Intent(this.getContext(), chuangjianActivity.class);
                startActivity(intent);
                break;
            case R.id.fab_2:
                Intent intent1 = new Intent(this.getContext(), wodeActivity.class);
                startActivity(intent1);


                break;
        }


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String ObjectID = data.get(position).getObjectId();
        Intent intent = new Intent(this.getContext(), xiaozuxinxi.class);
        intent.putExtra("ID", ObjectID);
        startActivity(intent);
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

    public void onLoad() {
        BmobQuery<group> query = new BmobQuery<>();
        query.setSkip(data.size());
        query.setLimit(5);
        query.findObjects(new FindListener<group>() {
            @Override
            public void done(List<group> list, BmobException e) {
                if (e == null) {
                    if (list.size() == 0) {
                        Toast toast = Toast.makeText(getActivity(), "no more", Toast.LENGTH_LONG);
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
                    Toast toast = Toast.makeText(getActivity(), "false", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

    }
}
