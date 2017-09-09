package zhu.skilltree.bean;

import cn.bmob.v3.BmobObject;

/**
 * This is SkillTree
 * Created by qidi on 2017/8/16.
 * Tel:18340018130
 * E-mail:sevenddddddd@gmail.com
 */

public class MyClass extends BmobObject {
    private String name;//课程名
    private String teacher;//任课老师
    private String credit;//学分
    private String score;//成绩
    private String level;//等级，取值abcd

    public MyClass(String name, String teacher, String credit) {
        this.name = name;
        this.teacher = teacher;
        this.credit = credit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
