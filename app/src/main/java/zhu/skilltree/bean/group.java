package zhu.skilltree.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by Zhu on 2017.7.23.
 */
public class group extends BmobObject {
    public String title;
    public String info;
    public BmobFile icon;
    public String type;
    public BmobUser leader;
    public BmobRelation members;

    public group() {

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setIcon(BmobFile icon) {
        this.icon = icon;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLeader(BmobUser leader) {
        this.leader = leader;
    }

    public void setMembers(BmobRelation members) {
        this.members = members;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

    public BmobFile getIcon() {
        return icon;
    }

    public String getType() {
        return type;
    }

    public BmobUser getLeader() {
        return leader;
    }

    public BmobRelation getMembers() {
        return members;
    }

}
