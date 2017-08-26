package zhu.skilltree.UI;

/**
 * Created by zd on 2017/7/19.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import zhu.skilltree.R;


/**
 * Created by janiszhang on 2016/6/6.
 */

public class Frag_class extends Fragment {

    private View viewContent;






    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //布局文件中只有一个居中的TextView
        viewContent = inflater.inflate(R.layout.frag_class,container,false);
        TextView textView = (TextView) viewContent.findViewById(R.id.tv_content);
        textView.setText("111");

        return viewContent;
    }

}
