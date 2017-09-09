package zhu.skilltree.UI.MyFragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.bmob.v3.BmobUser;
import zhu.skilltree.R;


/**
 * Created by zd on 2017/7/19.
 */

public class Frag_My extends Fragment implements View.OnClickListener {

    private View contentView;
    private LinearLayout ll_my_class, ll_my_activity, ll_my_group, ll_my_settings, ll_my_about;
    private TextView name;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_my, container, false);
        bindViews(contentView);
        initListener();
        return contentView;
    }


    private void bindViews(View view) {
        ll_my_class = (LinearLayout) view.findViewById(R.id.ll_my_class);
        ll_my_activity = (LinearLayout) view.findViewById(R.id.ll_my_activity);
        ll_my_group = (LinearLayout) view.findViewById(R.id.ll_my_group);
        ll_my_settings = (LinearLayout) view.findViewById(R.id.ll_my_settings);
        ll_my_about = (LinearLayout) view.findViewById(R.id.ll_my_about);
        name = (TextView) view.findViewById(R.id.name);
        name.setText(BmobUser.getCurrentUser().getUsername());
    }

    private void initListener() {
        ll_my_class.setOnClickListener(this);
        ll_my_activity.setOnClickListener(this);
        ll_my_group.setOnClickListener(this);
        ll_my_settings.setOnClickListener(this);
        ll_my_about.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_my_class:
                break;
            case R.id.ll_my_activity:
                break;
            case R.id.ll_my_group:
                break;
            case R.id.ll_my_settings:
                break;
            case R.id.ll_my_about:
                break;


        }
    }
}
