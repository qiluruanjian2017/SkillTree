package zhu.skilltree.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import zhu.skilltree.R;
import zhu.skilltree.bean.group;

/**
 * Created by dell on 2017/7/19.
 */

public class MyAdapt extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;
    List<group> data;

    public MyAdapt(Context c, List<group> da) {
        data = da;
        context = c;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Zujian zujian = null;
        if (convertView == null) {
            zujian = new Zujian();
            convertView = layoutInflater.inflate(R.layout.myview, null);
            zujian.t1 = (TextView) convertView.findViewById(R.id.textView);
            zujian.t2 = (TextView) convertView.findViewById(R.id.textView2);
            zujian.t3 = (TextView) convertView.findViewById(R.id.textView3);
            convertView.setTag(zujian);
        } else {
            zujian = (Zujian) convertView.getTag();
        }
        zujian.t1.setText(data.get(position).getTitle());
        zujian.t2.setText(data.get(position).getInfo());
        zujian.t3.setText(data.get(position).getType());

        return convertView;
    }

    public final class Zujian {
        public TextView t1;
        public TextView t2;
        public TextView t3;
    }
}
