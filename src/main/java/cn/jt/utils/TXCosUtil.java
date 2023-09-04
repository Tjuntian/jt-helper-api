package cn.jt.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.Objects;

/**
 * 腾讯云对象存储工具类
 */
@Slf4j
public class TXCosUtil {

    //存储服务域名
    private static String Domain = "https://juntian-1317497766.cos.ap-chongqing.myqcloud.com";
    //存储文件前缀
    private static String Prefix = "jt-helper";

    //账号appId
    private static String AppId = "1317497766";

    //SecretId
    private static String SecretId = "AKIDWr33mHzYw76sQlspbBXfl2CdojPwIPL3";

    //SecretKey
    private static String SecretKey = "YqsicJkARyJODqUt382kEVchcP2SjngM";

    //存储桶名称
    private static String BucketName = "juntian-1317497766";

    //区域名
    private static String Region = "ap-chongqing";

    private static COSClient cosClient;

    //静态初始化块
    static {
        COSCredentials cred = new BasicCOSCredentials(SecretId, SecretKey);
        ClientConfig clientConfig = new ClientConfig();
        com.qcloud.cos.region.Region region = new Region(Region);
        clientConfig.setRegion(region);
        clientConfig.setHttpProtocol(HttpProtocol.https);
        clientConfig.setSocketTimeout(30 * 1000);  // 设置 socket 读取超时，默认 30s
        clientConfig.setConnectionTimeout(30 * 1000); // 设置建立连接超时，默认 30s
        cosClient = new COSClient(cred, clientConfig);// 生成 cos 客户端。
    }

    /**
     * 上传文件MultipartFile
     *
     * @param file
     * @param key  "/coupon/**.png"
     * @return
     */
    public static String uploadFile(MultipartFile file, String key) {
        try {
            File putFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
            ObjectMetadata objectMetadata = new ObjectMetadata();
            PutObjectRequest putObjectRequest = new PutObjectRequest(BucketName, key, file.getInputStream(), objectMetadata);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            if (putObjectResult.getETag() != null) {
                return Domain+key;
            }
        } catch (Exception e) {
            String message = e.getMessage();
            log.error("图片上传异常:" + message);
            return null;
        }
        return null;
    }

    /**
     * 上传文件MultipartFile
     *
     * @param file
     * @param key  "/coupon/**.png"
     * @return 图片访问url
     */
    public static String uploadFile(File file, String key) {
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            PutObjectRequest putObjectRequest = new PutObjectRequest(BucketName, key, file);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            if (putObjectResult.getETag() != null) {
                return Domain+key;
            }
        } catch (Exception e) {
            String message = e.getMessage();
            log.error("图片上传异常:" + message);
            return null;
        }
        return null;
    }

    /**
     * 下载文件获取输入流
     *
     * @param key "/coupon/**.png"
     * @return
     */
    public static COSObjectInputStream downloadImage(String key) {
        try {
            GetObjectRequest getObjectRequest = new GetObjectRequest(BucketName, key);
            COSObject cosObject = cosClient.getObject(getObjectRequest);
            COSObjectInputStream cosObjectInput = cosObject.getObjectContent();
            return cosObjectInput;
        } catch (Exception e) {
            String message = e.getMessage();
            log.error("图片下载异常:" + message);
            return null;
        }
    }

    /**
     * 获取Url
     *
     * @param key
     * @return
     */
    public static URL getUrl(String key) {
        Date expirationDate = new Date(System.currentTimeMillis() + 60 * 60 * 1000);//过期时间60分钟
        return cosClient.generatePresignedUrl(BucketName, key, expirationDate, HttpMethodName.GET);
    }

}
