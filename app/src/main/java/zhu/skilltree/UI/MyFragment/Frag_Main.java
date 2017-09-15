package zhu.skilltree.UI.MyFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mylhyl.superdialog.SuperDialog;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import zhu.skilltree.R;
import zhu.skilltree.UI.MyActivity.Lesson;
import zhu.skilltree.util.ClassTable;


/**
 * Created by zd on 2017/7/19.
 */

public class Frag_Main extends Fragment {
    private View contentView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.tree_activity_main, container, false);
        try {
            ClassTable.getClassTable("201500301238","199672");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ImageView tr = (ImageView)contentView. findViewById(R.id.tree);
        ImageView yun = (ImageView) contentView.findViewById(R.id.yunduo);
        tr.post(new Runnable() {
            @Override
            public void run() {
//                W=(findViewById(R.id.tree)).getWidth();
//                final int H=( findViewById(R.id.tree)).getHeight();
                Log.e("TAG", "mtextview width is : "+(contentView.findViewById(R.id.tree)).getWidth()+", height is : "+( contentView.findViewById(R.id.tree)).getHeight()  );
            }
        });
        tr.setOnTouchListener(new View.OnTouchListener() {
                                  @Override
                                  public boolean onTouch(View view, MotionEvent motionEvent) {
                                      int tx = (int) motionEvent.getX();
                                      int ty = (int) motionEvent.getY();
                                      int H=( contentView.findViewById(R.id.tree)).getHeight();
                                      int W=( contentView.findViewById(R.id.tree)).getWidth();
                                      float XW=100*tx /W;
                                      float YH=100*ty/H;
                                      float TH=73;
                                      float TW=84;
                                      Log.d("DEBUG", "getX=" + tx + "getY=" + ty +"H"+H+"W"+W+"/n");
                                      Log.d("DEBUG", "getXW=" + XW + "getYH=" + YH +"/n"+"(21TW)"+(84/21));
                                      //Grade1
                                      if ((XW>(2100/TW))&&(XW<(3500/TW))&&(YH>(3600/TH))&&(YH<(5000/TH))) {
                                          Intent intent = new Intent(getActivity(), Lesson.class);
                                          startActivity(intent);

                                      }
                                      //Grade2
                                      else  if ((XW>(5300/TW))&&(XW<(6600/TW))&&(YH>(3500/TH))&&(YH<(4900/TH))) {
                                          Intent intent = new Intent(getActivity(), Lesson.class);
                                          startActivity(intent);

                                      }
                                      //Grade3
                                      else  if ((XW>(4900/TW))&&(XW<(6400/TW))&&(YH>(1900/TH))&&(YH<(3400/TH))) {
                                          Intent intent = new Intent(getActivity(), Lesson.class);
                                          startActivity(intent);

                                      }
                                      //Grade4
                                      else  if ((XW>(3000/TW))&&(XW<(4400/TW))&&(YH>(1700/TH))&&(YH<(3100/TH))) {
                                          Intent intent = new Intent(getActivity(), Lesson.class);
                                          startActivity(intent);

                                      }
                                      //1.1
                                      else  if ((XW>(200/TW))&&(XW<(1500/TW))&&(YH>(3100/TH))&&(YH<(4300/TH))) {
                                          showClass("1","1");
                                          //Intent intent = new Intent(getActivity(), Lesson.class);
                                          //startActivity(intent);

                                      }
                                      //1.2
                                      else  if ((XW>(1600/TW))&&(XW<(2800/TW))&&(YH>(2300/TH))&&(YH<(3500/TH))) {
                                          Intent intent = new Intent(getActivity(), Lesson.class);
                                          startActivity(intent);

                                      }
                                      //2.1
                                      else  if ((XW>(6500/TW))&&(XW<(7600/TW))&&(YH>(4400/TH))&&(YH<(5500/TH))) {
                                          Intent intent = new Intent(getActivity(), Lesson.class);
                                          startActivity(intent);

                                      }
                                      //2.2
                                      else  if ((XW>(6900/TW))&&(XW<(8000/TW))&&(YH>(3400/TH))&&(YH<(4400/TH))) {
                                          Intent intent = new Intent(getActivity(), Lesson.class);
                                          startActivity(intent);

                                      }
                                      //3.1
                                      else  if ((XW>(7000/TW))&&(XW<(8000/TW))&&(YH>(1900/TH))&&(YH<(3000/TH))) {
                                          Intent intent = new Intent(getActivity(), Lesson.class);
                                          startActivity(intent);

                                      }
                                      //3.2
                                      else  if ((XW>(6200/TW))&&(XW<(7200/TW))&&(YH>(900/TH))&&(YH<(1900/TH))) {
                                          Intent intent = new Intent(getActivity(), Lesson.class);
                                          startActivity(intent);

                                      }
                                      //4.1
                                      else  if ((XW>(2000/TW))&&(XW<(3100/TW))&&(YH>(1000/TH))&&(YH<(2100/TH))) {
                                          Intent intent = new Intent(getActivity(), Lesson.class);
                                          startActivity(intent);

                                      }
                                      //4.2
                                      else  if ((XW>(3100/TW))&&(XW<(4200/TW))&&(YH>(300/TH))&&(YH<(1400/TH))) {
                                          Intent intent = new Intent(getActivity(), Lesson.class);
                                          startActivity(intent);

                                      }
                                      return false;

                                  }
                              }

        );

        return contentView;
    }
    public void showClass(String s1,String s2){
        List<String> list = new ArrayList<>();
        list.add("拍照");
        list.add("从相册选择");
        list.add("小视频");
        new SuperDialog.Builder(this.getActivity())
                //.setAlpha(0.5f)
                //.setGravity(Gravity.CENTER)
                //.setTitle("上传头像", ColorRes.negativeButton)
                .setCanceledOnTouchOutside(false)
                .setItems(list, new SuperDialog.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                    }
                })
                .setNegativeButton("取消", null)
                .build();
    }
}
