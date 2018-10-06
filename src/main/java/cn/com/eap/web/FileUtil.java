package cn.com.eap.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author lixin
 * @date 2018/8/8
 */
public class FileUtil {

//    public static String ReadFile(URL resource) {
//        BufferedReader reader = null;
//        String laststr = "";
//        try {
//            FileInputStream fileInputStream = new FileInputStream(resource);
//            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
//            reader = new BufferedReader(inputStreamReader);
//            String tempString = null;
//            while ((tempString = reader.readLine()) != null) {
//                laststr += tempString;
//            }
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return laststr;
//    }

//    public static String ReadFile(String path) {
//        BufferedReader reader = null;
//        String laststr = "";
//        try {
//            FileInputStream fileInputStream = new FileInputStream(path);
//            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
//            reader = new BufferedReader(inputStreamReader);
//            String tempString = null;
//            while ((tempString = reader.readLine()) != null) {
//                laststr += tempString;
//            }
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return laststr;
//    }

    public static String ReadFile(InputStream inputStream) {
        BufferedReader reader = null;
        String laststr = "";
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return laststr;
    }

    public static String ReadFile(URL url) {
        BufferedReader reader = null;
        String laststr = "";
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(url.openStream(), "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return laststr;
    }
}
