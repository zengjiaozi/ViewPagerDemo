package cn.a10086.www.viewpagerdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @time 2017/3/7  16:30
 * @desc ${TODD}
 */
public class Fragment1 extends Fragment {

    private TabLayout main_tab;
    private ViewPager main_viewpager;
    private List<Fragment> fragments;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment1, container, false);
        return inflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        main_tab = (TabLayout) view.findViewById(R.id.main_tab);
        main_viewpager = (ViewPager) view.findViewById(R.id.main_viewpager);
        initdatas();
        setAdapter();

    }


    //  初始化fragment
    private void initdatas() {
        fragments = new ArrayList<>();
        fragments.add(new TabFragment1());
        fragments.add(new TabFragment2());
        fragments.add(new TabFragment3());
    }

    private void setAdapter() {
        TabOneAdapter adapter = new TabOneAdapter(getFragmentManager(), fragments);
        main_viewpager.setAdapter(adapter);
        main_tab.setupWithViewPager(main_viewpager);


    }

    private class TabOneAdapter extends FragmentPagerAdapter {

        List<Fragment> fragments;
        String[] strs = new String[]{"推荐", "热门", "收藏"};

        public TabOneAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return strs[position];
        }
    }
}
