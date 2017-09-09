package zhu.skilltree.db_service;

import cn.bmob.v3.BmobObject;

/**
 * Created by Zhu on 2017.7.23.
 */
public interface dbServiceI {
    Boolean save(BmobObject bmobObject);

    Boolean update(BmobObject bmobObject, String property, String newValue);

    Boolean delete(BmobObject bmobObject);

    void setFlag(Boolean b);
}
