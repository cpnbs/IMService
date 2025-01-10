package com.cp.im.constants;

import java.util.Arrays;
import java.util.List;

/**
 * @MethodName:
 * @Description: TODO 常量
**/
public interface Constants {

    /**
     * 加密方式
     */
    String MD5 = "MD5";
    /**
     * 散列次数
     */
    int HASHITERATIONS = 1024;

    String KEY_ORDER_CATEGORY_LIST = "KEY_ORDER:CATEGORY_LIST"; // 订单中心：商品类别

    String KEY_MANAGE_CAUSELIST = "KEY_MANAGE:CAUSELIST"; // 管理中心：举报理由字典


    /**
     * 保存聊天文件前缀
     */
    String ROOM_CHAT = "room/chatFile/";


    // 图片格式
    List<String> picExtList = Arrays.asList(
            ".jpeg", ".bmp", ".jpg", ".png", ".tif", ".gif", ".pcx", ".tga", ".exif", ".fpx", ".svg", ".psd", ".cdr", ".pcd", ".dxf", ".ufo", ".eps", ".ai", ".raw", ".wmf", ".webp");
    // 音频格式
    List<String> audioExtList = Arrays.asList(
            ".cd", ".wave", ".aiff", ".mpeg", ".mp3", ".mpeg-4", ".midi", ".wma", ".realaudio", ".vqf", ".oggvorbis", ".amr", ".ape", ".flac", ".acc");
    // 视频格式
    List<String> videoExtList = Arrays.asList(
            ".wmv", ".avi", ".dat", ".asf", ".mpeg", ".mpg", ".rm", ".rmvb", ".ram", ".flv", ".mp4", ".3gp", ".mov", ".divx",
            ".dv", ".vob", ".mkv", ".qt", ".cpk", ".fli", ".flc", ".f4v", ".m4v", ".mod", ".m2t", ".swf", ".webm", ".mts", ".m2ts", ".3g2", ".mpe", ".ts", ".div", ".lavf", ".dirac"
    );
}
