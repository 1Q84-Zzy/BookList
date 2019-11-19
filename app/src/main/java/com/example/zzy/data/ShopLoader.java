package com.example.zzy.data;

import android.os.Handler;
import android.util.Log;

import com.example.zzy.data.model.Shop;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ShopLoader {
    public ArrayList<Shop> getShops() {
        return shops;
    }

    private ArrayList<Shop>shops=new ArrayList<>();
    public String download(String urlString)
    {
        try {
            // 调用URL对象的openConnection方法获取HttpURLConnection的实例
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // 设置连接超时、读取超时的时间，单位为毫秒（ms）
            conn.setConnectTimeout(5000);
            // 设置是否使用缓存  默认是true
            conn.setUseCaches(false);

            conn.connect();
            // 这里获取数据
            InputStream inputStream = conn.getInputStream();
            InputStreamReader input = new InputStreamReader(inputStream);
            BufferedReader buffer = new BufferedReader(input);
            if (conn.getResponseCode() == 200) {//200意味着返回“ok”
                String inputLine;
                StringBuffer resultData = new StringBuffer();
                //StringBuffer字符串拼接的很快
                while ((inputLine = buffer.readLine()) != null) {
                    resultData.append(inputLine);//读取返回的全部数据
                }
                String text = resultData.toString();
                Log.v("out-------->", text);
                return (text);//作为函数结果返回
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    public void parseJson(String text)
    {
        shops.clear();
        try {
            //这里的text就是上边获取到的数据，一个String.
            JSONObject jsonObject = new JSONObject(text);
            JSONArray jsonDatas = jsonObject.getJSONArray("shops");
            int length=jsonDatas.length();
            String test;
            for (int i = 0; i < length; i++) {
                JSONObject shopJson = jsonDatas.getJSONObject(i);
                Shop shop = new Shop();

                shop.setName(shopJson.getString("name"));
                shop.setLatitude(shopJson.getDouble("latitude"));
                shop.setLongitude(shopJson.getDouble("longitude"));
                shop.setMemo(shopJson.getString("memo"));
                shops.add(shop);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void load(final Handler handler, final String url)
    {//下载并解析数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                String content=download(url);
                parseJson(content);
                handler.sendEmptyMessage(1);
            }
        }).start();
    }
}
