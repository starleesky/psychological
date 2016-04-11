package com.qiniu;

/**
 * Created by Administrator on 2016/4/11.
 */
import com.qiniu.util.Auth;
import java.io.IOException;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;

public class UploadDemo {
    //设置好账号的ACCESS_KEY和SECRET_KEY
    String ACCESS_KEY = "XGoncZxNJgVzvdM3UooJBZ5FJ9m1-MdolFc-ha-g";
    String SECRET_KEY = "UlIcBfp2DYKxOpO43gwE4h9mYvv6Q7ls5u7zCDZj";
    //要上传的空间
    String bucketname = "tangsons";
    //上传到七牛后保存的文件名
    String key = "/images/information/1460393223588.jpg";
    //上传文件的路径
    String FilePath = "D://workspace//tsjx//WebRoot/images/information/1460393223587.jpg";

    //密钥配置
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    //创建上传对象
    UploadManager uploadManager = new UploadManager();

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public String getUpToken(){
        return auth.uploadToken(bucketname);
    }

    public void upload() throws IOException{
        try {
            //调用put方法上传
            Response res = uploadManager.put(FilePath, key, getUpToken());
            //打印返回的信息
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
    }

    /**
     * 上传图片到七牛
     * @param filePath
     * @param key
     * @throws IOException
     */
    public void uploadImgs(String filePath,String key) throws IOException{
        try {
            //调用put方法上传
            Response res = uploadManager.put(filePath, key, getUpToken());
            //打印返回的信息
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
    }
    public static void main(String args[]) throws IOException{
        new UploadDemo().upload();
    }

}