package com.cp.im.config;

import com.cp.im.utils.aliyun.AliYunAESEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName AliYunConf
 * @Description: TODO
 **/
@Component
public class AliYunConf {

    public static String endpoint;

    @Value("${aliyun.endpoint}")
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    private static String accessKeyId;
    @Value("${aliyun.accessKeyId}")
    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }
    public static String getAccessKeyId() {
        return AliYunAESEncoder.decrypt(signature, accessKeyId);
    }

    private static String accessKeySecret;
    @Value("${aliyun.accessKeySecret}")
    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }
    public static String getAccessKeySecret() {
        return AliYunAESEncoder.decrypt(signature, accessKeySecret);
    }

    public static String bucketName;

    @Value("${aliyun.bucketName}")
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public static String fileUrl;

    @Value("${aliyun.fileUrl}")
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public static Long signature;
    @Value("${aliyun.signature}")
    public void setSignature(Long signature) {
        this.signature = signature;
    }

    public static void main(String[] args) {
        Long signature =8520213081L;
        // 加密
        String encrypt = AliYunAESEncoder.encrypt(signature, "c0e1a1ee206f4438862164ff9575b2e2");
        System.out.println(encrypt);
        // 解密
        String decrypt = AliYunAESEncoder.decrypt(signature, "YUCq/ILkgwMF/CgvYzGgJQQnF/QQZOsiLHGiWZBjLes=");
        System.out.println(decrypt);
    }

}
