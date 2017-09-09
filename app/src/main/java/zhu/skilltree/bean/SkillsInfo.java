package zhu.skilltree.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Zhu on 2017.7.23.
 */
public class SkillsInfo extends BmobObject {
    private String skillName = "";
    private String skillStatus = "";
    //skillStatus : unselected 未选; selected 已选择但未完成; done 已完成;
    private String skillType = "";
    //skillType : "class" or "activity"

    public String getSkillType() {
        return skillType;
    }

    public void setSkillType(String skillType) {
        this.skillType = skillType;
    }

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
