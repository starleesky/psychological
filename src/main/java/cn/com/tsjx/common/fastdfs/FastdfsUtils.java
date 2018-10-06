package cn.com.tsjx.common.fastdfs;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.tsjx.common.fastdfs.common.MyException;
import cn.com.tsjx.common.fastdfs.common.NameValuePair;
import cn.com.tsjx.common.util.Utility;

/**
 * fastdfs的工具类，用于文件上传、删除
 * 
 * @Type FastdfsUtils
 * @Desc
 * @author tianzhonghong
 * @date 2015年6月27日
 * @Version V1.0
 */
public class FastdfsUtils {

    private static Logger log = LoggerFactory.getLogger(FastdfsUtils.class);

    private static TrackerClient trackerClient;

    private static TrackerServer trackerServer;

    private static StorageServer storageServer;

    private static StorageClient1 storageClient;

    static {

        try {
//            String classPath = new File(FastdfsUtils.class.getResource("/").getFile()).getCanonicalPath();
//        	String classPath = Utility.getFilePath();
//            String fastdfsClientConf = classPath + File.separator + "fdfs_client.conf";
        	String fastdfsClientConf = File.separator + "fdfs_client.conf";
            log.info("Fastdfs configuration file path:" + fastdfsClientConf);
            ClientGlobal.init(fastdfsClientConf);

            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();

            storageClient = new StorageClient1(trackerServer, storageServer);
        } catch (IOException e) {
            log.error("初始化fastdfs配置错误，无法加载classpath路径！", e);
        } catch (MyException e) {
            log.error("初始化fastdfs配置错误！", e);
        }
    }

    /**
     * 上传文件
     * 
     * @param fileName 文件全路径名称
     * @param fileExt 文件扩展名(可选)
     * @param fileAttribute 文件扩展属性(可选)
     * @return <pre>
     * 返回group + 两级目录 + fastdfs生成的文件名(例如：group1/M00/00/00/wKhkCVWORfmAW2zrAAC1_ECkr5I624.jpg)
     * </pre>
     * @throws IOException
     * @throws MyException 需要捕获异常来判断文件是否上传成功
     */
    public static String upload(String fileName, String fileExt, Map<String, String> fileAttribute) throws IOException,
            MyException {

        NameValuePair[] metaList = null;

        if (fileAttribute != null) {
            // 设置文件附加属性
            metaList = new NameValuePair[fileAttribute.size()];
            int i = 0;
            for (Map.Entry<String, String> entry : fileAttribute.entrySet()) {
                metaList[i++] = new NameValuePair(entry.getKey(), entry.getValue());
            }
        }

        return storageClient.upload_file1(fileName, fileExt, metaList);
    }

    /**
     * 上传文件(通过二进制流)
     * 
     * @param fileContent 文件内容
     * @param fileExt 文件扩展名(可选)
     * @param fileAttribute 文件扩展属性(可选)
     * @return <pre>
     * 返回group + 两级目录 + fastdfs生成的文件名(例如：group1/M00/00/00/wKhkCVWORfmAW2zrAAC1_ECkr5I624.jpg)
     * </pre>
     * @throws IOException
     * @throws MyException 需要捕获异常来判断文件是否上传成功
     */
    public static String upload(byte[] fileContent, String fileExt, Map<String, String> fileAttribute)
            throws IOException, MyException {

        NameValuePair[] metaList = null;

        if (fileAttribute != null) {
            // 设置文件附加属性
            metaList = new NameValuePair[fileAttribute.size()];
            int i = 0;
            for (Map.Entry<String, String> entry : fileAttribute.entrySet()) {
                metaList[i++] = new NameValuePair(entry.getKey(), entry.getValue());
            }
        }

        return storageClient.upload_file1(fileContent, fileExt, metaList);
    }

    /**
     * 上传文件（通过文件流)
     * 
     * @param inputStream 文件流
     * @param fileSize 文件大小
     * @param fileExt 文件扩展名
     * @param fileAttribute 文件扩展属性(可选)
     * @return <pre>
     * 返回group + 两级目录 + fastdfs生成的文件名(例如：group1/M00/00/00/wKhkCVWORfmAW2zrAAC1_ECkr5I624.jpg)
     * </pre>
     * @throws IOException
     * @throws MyException 需要捕获异常来判断文件是否上传成功
     */
    public static String upload(InputStream inputStream, long fileSize, String fileExt,
            Map<String, String> fileAttribute) throws IOException, MyException {

        if (StringUtils.isBlank(fileExt)) {
            throw new MyException("通过流方式上传文件必须指定文件扩展名！");
        }
        
        NameValuePair[] metaList = null;

        if (fileAttribute != null) {
            // 设置文件附加属性
            metaList = new NameValuePair[fileAttribute.size()];
            int i = 0;
            for (Map.Entry<String, String> entry : fileAttribute.entrySet()) {
                metaList[i++] = new NameValuePair(entry.getKey(), entry.getValue());
            }
        }

        UploadStream uploadStream = new UploadStream(inputStream, fileSize);
        return storageClient.upload_file1(null, fileSize, uploadStream, fileExt, metaList);
    }

    /**
     * 删除文件
     * 
     * @param fileId fastdfs返回的file_Id
     * @return
     * @throws IOException
     * @throws MyException 需要捕获异常来判断文件是否上传成功
     */
    public static boolean delete(String fileId) throws IOException, MyException {

        int errorNo = storageClient.delete_file1(fileId);
        if (errorNo != 0) {
            log.error("删除文件错误：错误代码(" + errorNo + ")");
        }
        return (errorNo == 0);
    }
}
