package cn.a10086.www.viewpagerdemo;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.RadioGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private ViewPager viewpager;
    private ArrayList<Fragment> fragments;
    private RadioGroup rg_group;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE); //隐藏系统给的标题
        setContentView(R.layout.activity_main);
        rg_group = (RadioGroup) findViewById(R.id.rg_group);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        initFragments();
        setAdapter();
//        默认点击第一页
        rg_group.check(R.id.rb_home);
        rg_group.setOnCheckedChangeListener(this);

    }


    //    初始化fragment界面
    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        fragments.add(new Fragment4());
        fragments.add(new Fragment5());
    }

    private void setAdapter() {
        ZengAdapter adapter = new ZengAdapter(getSupportFragmentManager(), fragments);
        viewpager.setAdapter(adapter);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_home:
                viewpager.setCurrentItem(0, false);
                break;
            case R.id.rb_news:
                viewpager.setCurrentItem(1, false);
                break;
            case R.id.rb_service:
                viewpager.setCurrentItem(2, false);
                break;
            case R.id.rb_gov:
                viewpager.setCurrentItem(3, false);
                break;
            case R.id.rb_setting:
                viewpager.setCurrentItem(4, false);
                break;
        }
    }
}
