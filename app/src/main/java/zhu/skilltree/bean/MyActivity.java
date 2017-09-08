package zhu.skilltree.bean;

import cn.bmob.v3.BmobObject;

/**
 * This is SkillTree
 * Created by qidi on 2017/8/18.
 * Tel:18340018130
 * E-mail:sevenddddddd@gmail.com
 */

public class MyActivity extends BmobObject{

    private String name;
    private String organizer;
    private String status;//用于记录该活动的状态，未选择：unselected;选择了但还未完成：doing;已完成：done

    public MyActivity(String name,String organizer) {
        this.name = name;
        this.organizer = organizer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
