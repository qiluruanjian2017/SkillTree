package zhu.skilltree.Adapter;

/**
 * Created by zd on 2017/7/19.
 */

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import zhu.skilltree.UI.MyFragment.Frag_activity;
import zhu.skilltree.UI.MyFragment.Frag_class;
import zhu.skilltree.UI.MyFragment.Frag_group;

/**
 * Created by janiszhang on 2016/6/10.
 */
//继承FragmentStatePagerAdapter
public class FragmentAdapter extends FragmentStatePagerAdapter {

    public static final String TAB_TAG = "@dream@";

    private List<String> mTitles;
    private Frag_class myClass;
    private Frag_activity myActivity;
    private Frag_group myGroup;

    public FragmentAdapter(FragmentManager fm, List<String> titles) {
        super(fm);
        mTitles = titles;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        //初始化Fragment数据
        switch (position) {
            case 0:
                if (myClass == null) {
                    return new Frag_class();
                } else {
                    return myClass;
                }
            case 1:
                if (myActivity == null) {
                    return new Frag_activity();
                } else {
                    return myActivity;
                }
            case 2:
                if (myGroup == null) {
                    return new Frag_group();
                } else {
                    return myGroup;
                }
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position).split(TAB_TAG)[0];
    }
}

