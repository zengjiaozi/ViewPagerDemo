package cn.a10086.www.viewpagerdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

/**
 * @author
 * @time 2017/3/8  10:21
 * @desc ${TODD}
 */
public class TabFragment1 extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<String> mDatas;
    private ArrayList<ImageView> imageList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.tab_fragment1, container, false);
        recyclerView = (RecyclerView) inflate.findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        initDatas();   //初始化数据
        recyclerView.setAdapter(new MyAdapter(mDatas));
        return inflate;
    }


    private void initDatas() {
//        加载数据的操作
        mDatas = new ArrayList<String>();
        for (int i = 0; i < 45; i++) {
            mDatas.add("第" + i + "项");
        }
        //初始化viewpager的数据
        int[] imageResIDs = {R.drawable.a, R.drawable.b, R.drawable.c, R.mipmap.ic_launcher};
        imageList = new ArrayList<ImageView>();
        for (int i = 0; i < imageResIDs.length; i++) {
            ImageView image = new ImageView(getActivity());
            image.setBackgroundResource(imageResIDs[i]);
            imageList.add(image);
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private ArrayList<String> mDatas;
        private static final int HEAD_VIEW = 0;//头布局
        private static final int BODY_VIEW = 1;//内容布局
        private MyPagerAdapter mPagerAdapter = new MyPagerAdapter();

        public MyAdapter(ArrayList<String> mDatas) {
            this.mDatas = mDatas;
        }

        //    通过判断返回不同的布局
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == HEAD_VIEW) {
                View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_fragment1_head, parent, false);
                MyHeadViewHolder headViewHolder = new MyHeadViewHolder(inflate);
                return headViewHolder;
            }
            if (viewType == BODY_VIEW) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_fragment1_body, parent, false);

                MyBodyViewholder bodyholder = new MyBodyViewholder(view);
                return bodyholder;
            }

            return null;
        }

        //        绑定数据的操作
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            //将数据填充到具体的view中
            if (holder instanceof MyHeadViewHolder) {
                ((MyHeadViewHolder) holder).viewpager.setAdapter(mPagerAdapter);
                ((MyHeadViewHolder) holder).indicator.onPageSelected(0);
                ((MyHeadViewHolder) holder).indicator.setViewPager(((MyHeadViewHolder) holder).viewpager);
                ((MyHeadViewHolder) holder).indicator.setSnap(true);
            }
            if (holder instanceof MyBodyViewholder) {
                ((MyBodyViewholder) holder).tv.setText(mDatas.get(position - 1));
            }

        }

        //  有一个
        @Override
        public int getItemCount() {
            return mDatas.size() + 1;
        }

        @Override
        public int getItemViewType(int position) {

            if (position == HEAD_VIEW) {
                return HEAD_VIEW;
            } else {
                return BODY_VIEW;
            }
        }

        //   头部的viewpager
        private class MyHeadViewHolder extends RecyclerView.ViewHolder {

            CirclePageIndicator indicator;
            ViewPager viewpager;

            public MyHeadViewHolder(View itemView) {
                super(itemView);
//                在这里查找控件的操作
                viewpager = (ViewPager) itemView.findViewById(R.id.vp_tab_headview);
                indicator = (CirclePageIndicator) itemView.findViewById(R.id.indicator);
            }
        }

        private class MyBodyViewholder extends RecyclerView.ViewHolder {

            TextView tv;

            public MyBodyViewholder(View view) {
                super(view);
                tv = (TextView) view.findViewById(R.id.recycle_tv);
            }
        }


    }


    //    viewpager包含图片的操作
    class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return imageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageList.get(position));
            return imageList.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return Integer.toString(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
