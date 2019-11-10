package com.example.zzy.data;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class BookFragmentAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> datas;  //存放要显示的子视图
    ArrayList<String> titles;   //存放要显示的标题

    public BookFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setData(ArrayList<Fragment> datas) {
        this.datas = datas;
    }

    public void setTitles(ArrayList<String> titles) {
        this.titles = titles;
    }
    @Override
    public Fragment getItem(int position) {//获得某个子视图
        return datas == null ? null : datas.get(position);
    }
    @Override
    public int getCount() { //获取子视图fragment的数目
        return datas == null ? 0 : datas.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {//获得某个标题
        return titles == null ? null : titles.get(position);
    }
}
