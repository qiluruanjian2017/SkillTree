package zhu.skilltree.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * Created by dell on 2017/8/24.
 */

public class Message extends BmobObject {
    public String mesType;
    public String info;
    public BmobUser user;
    public zhu.skilltree.bean.group group;

    public void setMesType(String mesType) {
        this.mesType = mesType;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setUser(BmobUser user) {
        this.user = user;
    }

    public void setGroup(group group) {
        this.group = group;
    }

    public String getMesType() {
        return mesType;
    }

    public String getInfo() {
        return info;
    }

    public BmobUser getUser() {
        return user;
    }

    public group getGroup() {
        return group;
    }
}
