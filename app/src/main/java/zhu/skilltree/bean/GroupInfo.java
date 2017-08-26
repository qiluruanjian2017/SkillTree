package zhu.skilltree.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by Zhu on 2017.7.23.
 */
public class GroupInfo extends BmobObject{
    private String skillName="";
    private UserInfo leader;
    private BmobRelation member;
    public UserInfo getLeader() {
        return leader;
    }

    public void setLeader(UserInfo leader) {
        this.leader = leader;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public BmobRelation getMember() {
        return member;
    }

    public void setMember(BmobRelation member) {
        this.member = member;
    }


}
