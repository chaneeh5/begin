package jwei.apps.dataforandroid.ch9;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * 
 * @author Jason Wei
 * 
 */
public class HTMLNavigator {

    // STEP 1 - GET THE URL'S SOURCE CODE
    public static CharSequence navigateAndGetContents(String url_str) throws IOException {
        URL url = new URL(url_str);

        // ESTABLISH CONNECTION TO URL
        URLConnection conn = url.openConnection();
        conn.setConnectTimeout(30000);
        String encoding = conn.getContentEncoding();
        if (encoding == null) {
            encoding = "ISO-8859-1";
        }

        // WRAP BUFFERED READER AROUND INPUT STREAM
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding));
        StringBuilder sb = new StringBuilder();
        try {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append('\n');
            }
        } finally {
            br.close();
        }
        return sb;
    }

    public static StringBuilder navigateAndGetContents2(String url_str) throws IOException {
        URL url = new URL(url_str);
        URLConnection conn = url.openConnection();
        // set the time out to longer
        conn.setConnectTimeout(30000);
        System.out.println("Going");
        String encoding = conn.getContentEncoding();
        // String encoding = "ISO-8859-1";
        System.out.println(encoding);
        if (encoding == null) {
            encoding = "ISO-8859-1";
        }
        System.out.println("1");
        BufferedReader br = new BufferedReader(new InputStreamReader(new BufferedInputStream(conn.getInputStream()),
                encoding));
        System.out.println("2");
        StringBuilder sb = new StringBuilder();
        try {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                sb.append(line);
                sb.append('\n');
            }
        } finally {
            br.close();
        }
        // int ln = conn.getContentLength();
        // System.out.println(ln);
        // char[] chars = new char[ln];
        // InputStreamReader is = new InputStreamReader(conn.getInputStream(),
        // encoding);
        // System.out.println("2");
        // is.read(chars, 0, ln);
        // String sb = new String(chars);
        // System.out.println(sb.substring(0, 50000));
        // System.out.println(sb.length());
        return sb;
    }

}
