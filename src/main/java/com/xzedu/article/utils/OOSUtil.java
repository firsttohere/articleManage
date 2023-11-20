package com.xzedu.article.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @ClassName : oosUtil
 * @Description : oosUtil
 * @Author : Xxxxx
 * @Date: 2023-11-19 14:10
 */
public class OOSUtil {
    static String endpoint = "https://oss-cn-chengdu.aliyuncs.com";
    static String bucketName = "xiangzhoubucket1";

    public static String uploadFile(String fileName, InputStream io) {
        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        EnvironmentVariableCredentialsProvider credentialsProvider = null;
        try {
            credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        } catch (com.aliyuncs.exceptions.ClientException e) {
            return "";
        }
        // 填写Bucket名称，例如examplebucket。
        String objectName = "img/" + fileName;
        // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);

        try {
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, io);
            // 上传文件。
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            // 不出现异常，代表上传成功
            return "https://"+bucketName+"." + endpoint.substring(endpoint.lastIndexOf('/') + 1) + "/" + objectName;
            // 访问的url就是 https://xiangzhoubucket1.oss-cn-chengdu.aliyuncs.com/img/001.png
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
            return "";
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
            return "";
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
