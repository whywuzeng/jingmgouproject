// INTERNAL ERROR //

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.andbase.util.AbAppConfig
 * JD-Core Version:    0.6.2
 */
package com.ismartgo.app.andbase.util;

public class AbAppConfig
{
    public static String CACHE_DIR;
    public static String CLIENTP_ROTOCOL_EXCEPTION;
    public static String CONNECT_EXCEPTION;
    public static String DB_DIR;
    public static String DOWNLOAD_FILE_DIR;
    public static String DOWNLOAD_IMAGE_DIR;
    public static String DOWNLOAD_ROOT_DIR;
    public static int MAX_CACHE_SIZE_INBYTES;
    public static String MISSING_PARAMETERS;
    public static String NOT_FOUND_EXCEPTION = "页面未找到";
    public static String NULL_MESSAGE_EXCEPTION;
    public static String NULL_POINTER_EXCEPTION;
    public static String REMOTE_SERVICE_EXCEPTION;
    public static String SHARED_PATH;
    public static String SOCKET_EXCEPTION;
    public static String SOCKET_TIMEOUT_EXCEPTION;
    public static int UI_HEIGHT;
    public static int UI_WIDTH = 720;
    public static String UNKNOWN_HOST_EXCEPTION;
    public static String UNTREATED_EXCEPTION = "未处理的异常";

    static
    {
        UI_HEIGHT = 1080;
        SHARED_PATH = "app_share";
        DOWNLOAD_ROOT_DIR = "download";
        DOWNLOAD_IMAGE_DIR = "images";
        DOWNLOAD_FILE_DIR = "files";
        CACHE_DIR = "cache";
        DB_DIR = "db";
        MAX_CACHE_SIZE_INBYTES = 10485760;
        CONNECT_EXCEPTION = "无法连接到网络";
        UNKNOWN_HOST_EXCEPTION = "连接远程地址失败";
        SOCKET_EXCEPTION = "网络连接出错，请重试";
        SOCKET_TIMEOUT_EXCEPTION = "连接超时，请重试";
        NULL_POINTER_EXCEPTION = "抱歉，远程服务出错了";
        NULL_MESSAGE_EXCEPTION = "抱歉，程序出错了";
        CLIENTP_ROTOCOL_EXCEPTION = "Http请求参数错误";
        MISSING_PARAMETERS = "参数没有包含足够的值";
        REMOTE_SERVICE_EXCEPTION = "抱歉，远程服务出错了";
    }
}
