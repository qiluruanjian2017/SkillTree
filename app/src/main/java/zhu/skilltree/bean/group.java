package zhu.skilltree.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by Zhu on 2017.7.23.
 */
public class group extends BmobObject {
    private Integer message_count;
    private String type;
    private String title;
    private BmobUser leader;
    private BmobRelation members;
    private String info;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getMessage_count() {
        return message_count;
    }

    public void setMessage_count(Integer message_count) {
        this.message_count = message_count;
    }

    public BmobRelation getMembers() {
        return members;
    }

    public void setMembers(BmobRelation members) {
        this.members = members;
    }

    public BmobUser getLeader() {
        return leader;
    }

    public void setLeader(BmobUser leader) {
        this.leader = leader;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
