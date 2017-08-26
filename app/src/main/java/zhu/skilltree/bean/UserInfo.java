package zhu.skilltree.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by Zhu on 2017.7.23.
 */
public class UserInfo extends BmobObject{
    private String studentId="";//学号
    private String schoolName="";//学校名称
    private String officePassword="";//教务处密码
    private String name="";//姓名
    private BmobRelation hasSkills;
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOfficePassword() {
        return officePassword;
    }

    public void setOfficePassword(String officePassword) {
        this.officePassword = officePassword;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }



    public BmobRelation getHasSkills() {
        return hasSkills;
    }

    public void setHasSkills(BmobRelation hasSkills) {
        this.hasSkills = hasSkills;
    }


}
