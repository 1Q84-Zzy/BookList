package com.example.zzy;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.casper.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class WebViewFragment extends Fragment {


    public WebViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_web_view, container, false);
        //获得控件
        WebView webView=(WebView)view.findViewById(R.id.web_view);
        //设置其客户为在本视图内打开
        webView.setWebViewClient(new WebViewClient());
        //允许js执行
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://news.sina.cn/");
        return view;
    }

}
