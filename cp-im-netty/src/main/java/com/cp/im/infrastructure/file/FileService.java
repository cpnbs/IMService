package com.cp.im.infrastructure.file;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.cp.im.constants.Constants;
import com.cp.im.error.ErrorCode;
import com.cp.im.infrastructure.mapper.PicInfoMapper;
import com.cp.im.domain.entity.PicInfo;
import com.cp.im.result.Result;
import com.cp.im.utils.aliyun.AliYunOssClient;
import com.cp.im.utils.StringUtils;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName FileService
 * @Description: TODO
 **/
@Service
@Slf4j
public class FileService {

    private static final String URL_PIC = "http://192.168.0.50:4869/";

    private String filePath = "uploadFile";

    private String httpPath = "192.168.0.114:7100/files/downloadFile/";

    private String fastDFSHttpSecretKey = "HandFastDFSToken";

    private String fileServerAddr = "http://192.168.0.50";

    @Resource
    private PicInfoMapper picInfoMapper;

    @Value("${aliyun.ossRate}")
    private float rate;

    @Value("${aliyun.ossPorn}")
    private float ossPorn;

    @Value("${aliyun.ossTerrorism}")
    private float ossTerrorism;

    @Resource(name = "taskFxbDrawExecutor")
    ExecutorService executorService;

    /**
     * @MethodName: delFile
     * @Description: TODO 删除Ali云文件
     * @Param: [objectName]
     * @Return: com.cp.im.result.Result
     * @Author: xubin
     * @Date: 2020/5/20
     **/
    public Result delFile(String objectName) {

        AliYunOssClient.delFile(objectName);
        picInfoMapper.deleteByPrimaryKey(objectName);

        return Result.success();
    }

    public Result getFile(Long id) {

        PicInfo record = picInfoMapper.selectByPrimaryKey(id);

        return Result.success(record);
    }

    public Result asyncUploadOssFile(MultipartFile multipartFile, String h, String w, String folder, HttpServletRequest request)
        throws ExecutionException {
        try {
            Future<Result> submit = executorService.submit(
                () -> uploadOssFile(multipartFile, h, w, folder, request)
            );
            return submit.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * @MethodName: uploadOssFile
     * @Description: TODO 文件上传到阿里云服务
     * @Param: [multipartFile]
     * @Return: com.cp.im.result.Result
     * @Author: xubin
     * @Date: 2020/5/20
     **/
    @Transactional
    public Result uploadOssFile(MultipartFile multipartFile, String h, String w, String folder, HttpServletRequest request) {
        log.info("文件大小Size=[{}]M", multipartFile.getSize() / 1048576);
        String userId = "";
        if (request != null){
            userId = request.getHeader("userId") == null ? "" : request.getHeader("userId");
        }
        String fileName = multipartFile.getOriginalFilename(); // 获取原文件名
        String originalFileName = fileName.substring(fileName.lastIndexOf("/") + 1);// 原文件名
        log.info("文件上传到阿里云服务, 文件名=[{}]", originalFileName);
        String ext = getExt(originalFileName);// 文件后缀名
        String newFileName = IdUtil.simpleUUID() + ext; // 生成新文件名
        // 文件夹处理
        String filePath = filePathHandle(folder, userId, ext);
        String finalFileName = filePath + newFileName;

        String fileUrl = AliYunOssClient.uploadFile(multipartFile, finalFileName, ext);// 上传
        PicInfo record = new PicInfo();
        record.setPicName(originalFileName);
        record.setPicUrl(fileUrl);
        record.setLittlePicUrl(urlSuffix(fileUrl, h, w));
        record.setPicNewName(finalFileName);
        record.setPicType(ext);
        picInfoMapper.insert(record);
        return Result.success(record);
    }

    /**
     * @MethodName: getExt
     * @Description: TODO 获取文件后缀名
     * @Param: [fileName]
     * @Return: java.lang.String
     * @Author: xubin
     * @Date: 17:01 2020/10/16
     **/
    public String getExt(String fileName) {
        String ext = null;
        if (fileName.contains(".9")) {
            ext = fileName.substring(fileName.indexOf(".9")).toLowerCase();
        } else {
            ext = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        }
        return ext;
    }

    public static void main(String[] args) {
        String originalFileName = "loding_0.6c45b4a158.png";
        String substring = originalFileName.substring(originalFileName.lastIndexOf("/") + 1);
        System.out.println(substring);
        String ext = null;
        if (substring.contains(".9")) {
            ext = substring.substring(substring.indexOf(".9")).toLowerCase();
        } else {
            ext = substring.substring(substring.lastIndexOf(".")).toLowerCase();
        }
        System.out.println(ext);
    }

    /**
     * @MethodName: uploadOssFile
     * @Description: TODO 上传网络流到阿里云服务
     * @Param: [fileUrl:文件URL, ext:文件格式, folder:目录]
     * @Return: com.cp.im.result.Result
     * @Author: xubin
     * @Date: 2020/5/20
     **/
    public Result uploadOssFile(String fileUrl, String ext, String folder, HttpServletRequest request) {
        String userId = request.getHeader("userId") == null ? "" : request.getHeader("userId");
        String originalFileName = fileUrl; // 获取原文件名为原URL
        log.info("文件上传到阿里云服务, 文件名=[{}]", originalFileName);
        if (StrUtil.isEmpty(ext)) {
            ext = ".jpeg";
        }
        String newFileName = IdUtil.simpleUUID() + ext; // 生成新文件名
        // 文件夹处理
        String filePath = filePathHandle(folder, userId, ext);

        String finalFileName = filePath + newFileName;

        String nweFileUrl = AliYunOssClient.uploadFile(fileUrl, finalFileName, ext);// 上传
        if (StringUtils.isEmpty(nweFileUrl)) {
            return Result.error(ErrorCode.UPLOAD_FIALED);
        }
        PicInfo record = new PicInfo();
        record.setPicName(originalFileName);
        record.setPicUrl(nweFileUrl);
        record.setPicNewName(finalFileName);
        record.setPicType(ext);
        picInfoMapper.insert(record);
        return Result.success(record);
    }

    // 文件夹处理
    private String filePathHandle(String folder, String userId, String ext) {

        String filePath = "";
        if (StrUtil.isNotEmpty(folder)) {
            if (!folder.endsWith("/")) {
                folder = folder + "/";
            }
            if (folder.startsWith("/")) {
                folder = folder.substring(1, folder.length());
            }
            if (StrUtil.isNotEmpty(userId)) {
                folder = folder + userId + "/";
            }
            filePath = AliYunOssClient.createFolder(folder);
        }
        // 如果folder为空则按照文件类型进行分类
        if (StrUtil.isEmpty(filePath)) {
            if (Constants.picExtList.contains(ext)) { // 图片
                filePath = "picture/";
            } else if (Constants.audioExtList.contains(ext)) { // 音频
                filePath = "audio/";
            } else if (Constants.videoExtList.contains(ext)) { // 视频
                filePath = "video/";
            } else {
                filePath = "file/";
            }
        }
        return filePath;
    }



    /**
     * @MethodName: gainFile
     * @Description: TODO 获取压缩图片文件
     * @Param: [fileName 文件名,width 缩放宽度, response, request]
     * @Return: void
     * @Author: xubin
     * @Date: 2020/5/29
     **/
    public void gainFileImg(String fileName, String width, String high, HttpServletResponse response, HttpServletRequest request) {
        String urlPath = "http://majlis.oss-me-east-1.aliyuncs.com/" + fileName + "?x-oss-process=image/resize,w_100";
        InputStream inputStream = AliYunOssClient.getInputStream(urlPath);
        AliYunOssClient.writeFile(response, inputStream);

    }

    private String urlSuffix(String fileUrl, String h, String w) {

        if (StrUtil.isNotEmpty(h) && StrUtil.isNotEmpty(w)) {
            return fileUrl + "?x-oss-process=image/resize,m_fixed,h_" + h + ",w_" + w;
        }
        if (StrUtil.isNotEmpty(h)) {
            return fileUrl + "?x-oss-process=image/resize,h_" + h;
        }
        if (StrUtil.isNotEmpty(w)) {
            return fileUrl + "?x-oss-process=image/resize,w_" + w;
        }
        return null;
    }
}