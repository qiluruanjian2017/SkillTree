package zhu.skilltree.UI.MyFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import zhu.skilltree.R;


/**
 * Created by zd on 2017/7/19.
 */

public class Frag_Main extends Fragment {
    private View contentView;
    private TextView tv_main;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_main, container, false);
        bindViews(contentView);
        return contentView;
    }


    private void bindViews(View view) {
        tv_main = (TextView) view.findViewById(R.id.tv_main);
    }
}
