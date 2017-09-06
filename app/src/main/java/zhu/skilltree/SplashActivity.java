package zhu.skilltree;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import cn.bmob.v3.Bmob;

/**
 * Created by Zhu on 2017.7.23.
 */
public class SplashActivity extends Activity {
    private Handler mHandler;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 初始化 Bmob SDK
        // 使用时请将第二个参数Application ID替换成你在Bmob服务器端创建的Application ID
        Bmob.initialize(this, "8bf886b39cdf84e9956a46b548ae5424");

        //使用LayoutInflater来加载activity_splash.xml视图
        View rootView = LayoutInflater.from(this).inflate(R.layout.activity_splash, null);
        /**
         * 这里不能使用findViewById(R.layout.acitivyt_spash)方法来加载
         * 因为还没有开始调用setContentView()方法，也就是说还没给当前的Activity
         * 设置视图，当前Activity Root View为null，findViewById()方法是从当前
         * Activity的Root View中获取子视图，所以这时候会报NullPointerException异常
         *
         * View rootView = findViewById(R.layout.activity_splash);
         *
         */
        setContentView(rootView);
        mHandler = new Handler();

        //初始化渐变动画
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        //设置动画监听器
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //当监听到动画结束时，开始跳转到MainActivity中去
                mHandler.post(new Runnable() {
                    public void run() {
                        Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(i);
                        SplashActivity.this.finish();
                    }
                });
            }
        });

        //开始播放动画
        rootView.startAnimation(animation);
    }


}
