package zhu.skilltree.bean;

import cn.bmob.v3.BmobObject;

/**
 * This is SkillTree
 * Created by qidi on 2017/8/16.
 * Tel:18340018130
 * E-mail:sevenddddddd@gmail.com
 */

public class MyClass extends BmobObject{
    private String name;//课程名
    private String teacher;//任课老师
    private String status;//用于记录该课程的状态，未选择：unselected;选择了但还未完成：doing;已完成：done

    public MyClass(String name, String teacher){
        this.name = name;
        this.teacher = teacher;
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
}
