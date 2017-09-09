package zhu.skilltree.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import cn.bmob.v3.BmobUser;
import zhu.skilltree.R;
import zhu.skilltree.bean.Message;

/**
 * Created by dell on 2017/8/27.
 */

public class ChatAdapt extends BaseAdapter {
    Context context;
    List<Message> data;
    LayoutInflater inflater;
    public ProgressBar a;

    public ChatAdapt(List<Message> data, Context context) {
        this.data = data;
        this.context = context;
        this.inflater = LayoutInflater.from(context);

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
        BmobUser user_this = BmobUser.getCurrentUser();
        if (data.get(position).getUser().getObjectId().equals(user_this.getObjectId())) {
            convertView = inflater.inflate(R.layout.chat_me, null);
            TextView word = (TextView) convertView.findViewById(R.id.chat_me);
            TextView name = (TextView) convertView.findViewById(R.id.chat_me_name);
//            a=(ProgressBar)convertView.findViewById(R.id.Chat_me_pbar);
//            a.setVisibility(View.GONE);
            word.setText(data.get(position).getInfo());
            name.setText(BmobUser.getCurrentUser().getUsername());
        } else {
            convertView = inflater.inflate(R.layout.chat_other, null);
            TextView word = (TextView) convertView.findViewById(R.id.chat_other);
            TextView name = (TextView) convertView.findViewById(R.id.chat_other_name);
            word.setText(data.get(position).getInfo());
            name.setText(data.get(position).getUser().getUsername());
        }
        return convertView;
    }

}
