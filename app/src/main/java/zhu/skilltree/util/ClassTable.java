package zhu.skilltree.util;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Zhu on 2017.8.8.
 */
public class ClassTable {

    //获得响应头
    static Thread getHeader = new Thread(new Runnable() {
        @Override
        public void run() {
            Map<String, List<String>> headers = null;
            try {
                String loginpage = "http://bkjws.sdu.edu.cn/f/login";
                URL urlLoginPage = new URL(loginpage);
                HttpURLConnection urlConnection = (HttpURLConnection) urlLoginPage.openConnection();
                urlConnection.setRequestMethod("GET");
                headers = urlConnection.getHeaderFields();
                Log.e("headers", headers.toString());

                URL urlLoginAjax = new URL("http://bkjws.sdu.edu.cn/b/ajaxLogin");
                HttpURLConnection urlConnection2 = (HttpURLConnection) urlLoginAjax.openConnection();
                urlConnection2.setRequestMethod("POST");
                urlConnection2.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                urlConnection2.setRequestProperty("Origin", "http://bkjws.sdu.edu.cn");
                urlConnection2.setRequestProperty("Referer", "http://bkjws.sdu.edu.cn/f/login");
                urlConnection2.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36");
                urlConnection2.setRequestProperty("Cookie", headers.get("Set-Cookie").get(0));
                urlConnection2.setDoOutput(true);
                urlConnection2.setDoInput(true);
                StringBuilder content = new StringBuilder();
                content.append("j_username=").append(URLEncoder.encode("201500301238", "UTF-8"))
                        .append("&j_password=").append(URLEncoder.encode("199672", "UTF-8"));
                DataOutputStream dataOutputStream = new DataOutputStream(urlConnection2.getOutputStream());
                System.out.println(content.toString());
                dataOutputStream.writeBytes(content.toString());
                dataOutputStream.flush();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection2.getInputStream(), "UTF-8"));
                String line = null;
                StringBuilder sb = new StringBuilder();

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                String s2=sb.substring(1,8);
                if (s2.equals("success")) {
                    StringBuilder sb1 = new StringBuilder("index=1;");
                    sb1.append(headers.get("Set-Cookie").get(0)).append(";").append("j_username=").append(URLEncoder.encode("201500301238", "UTF-8"))
                            .append(";j_password=").append(URLEncoder.encode("199672", "UTF-8"));
                    String s="index=1;";
                    s+=headers.get("Set-Cookie").get(0);
                    s+=";j_username="+ReqInfo.id+";j_password="+ReqInfo.password;
                    URL url = new URL("http://bkjws.sdu.edu.cn/f/xk/xs/bxqkb");
                    HttpURLConnection urlConnection3 = (HttpURLConnection) url.openConnection();
                    urlConnection3.setRequestMethod("POST");
                    urlConnection3.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                    urlConnection3.setRequestProperty("Origin", "http://bkjws.sdu.edu.cn");
                    urlConnection3.setRequestProperty("Referer", "http://bkjws.sdu.edu.cn/f/login");
                    urlConnection3.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36");
                    urlConnection3.setRequestProperty("Cookie", sb1.toString());
                    urlConnection3.setDoOutput(true);
                    urlConnection3.setDoInput(true);
                    Log.e("aaa", urlConnection3.getResponseCode()+"");
                    if (200 == urlConnection3.getResponseCode()) {
                        InputStream in = urlConnection3.getInputStream();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        int len = 0;
                        while (-1 != (len = in.read(buffer))) {
                            baos.write(buffer, 0, len);
                            baos.flush();
                        }
                      ReqInfo.resultHtml=baos.toString();
                    }

                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    });


    public static ArrayList<String> getClassTable(String id,String password) throws UnsupportedEncodingException, InterruptedException {
        ReqInfo.id=id;
        ReqInfo.password=password;

        getHeader.start();
        Thread.sleep(2000);
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 200) {
                    ReqInfo.resultHtml = (String) msg.obj;
                }
            }
        };
        ArrayList<String> classList = new ArrayList<>();

        Document doc = Jsoup.parse(ReqInfo.resultHtml);
        Element tempTable=doc.getElementById("ysjddDataTableId");
        Elements tempNodes=tempTable.getElementsByTag("tr");
        if(tempNodes.size()>0){
            for(int i=1;i<tempNodes.size();i++){
                Element e=tempNodes.get(i);
                Elements classInfo=e.getAllElements();
                if(classInfo.size()>0){
                    Element className=classInfo.get(3);
                    classList.add(className.text());
                }
            }
        }
        Log.e("aaa",classList.toString());
        return classList;
    }

}

class ReqInfo {
    public static String id;
    public static String password;
    public static String resultHtml;
}
