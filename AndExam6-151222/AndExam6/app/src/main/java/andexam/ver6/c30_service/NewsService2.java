package andexam.ver6.c30_service;

import andexam.ver6.*;
import java.util.*;

import android.app.*;
import android.content.*;
import android.os.*;
import android.widget.*;

public class NewsService2 extends IntentService {
    public NewsService2() {
        super("NewsThread");
    }

    protected void onHandleIntent(Intent intent) {
        String[] arNews = {
                "4T SSD 10만원대 진입",
                "갤럭시 S8 판매 호조",
                "핵융합 발전소 건설 완료",
        };
        for (int idx = 0;idx < arNews.length;idx++) {
            Message msg = new Message();
            msg.what = 0;
            msg.obj = arNews[idx % arNews.length];
            mHandler.sendMessage(msg);
            try { Thread.sleep(5000);} catch (Exception e) { ; }
        }
    }

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                String news = (String)msg.obj;
                Toast.makeText(NewsService2.this, news, Toast.LENGTH_SHORT).show();
            }
        }
    };
}