package zhu.skilltree.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Zhu on 2017.7.23.
 */
public class SkillsInfo extends BmobObject {
    private String skillName="";
    private String skillStatus="";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type="";
    public String getSkillStatus() {
        return skillStatus;
    }

    public void setSkillStatus(String skillStatus) {
        this.skillStatus = skillStatus;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }


}
