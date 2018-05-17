package org.larrychina.lcm.conf;

import org.larrychina.lcm.exception.LcmException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by suning on 2018/5/4.
 * load properties
 */
public class LcmConfigration {

    public static String CONFIG_FILE = "/lcm.properties";
    public static final String PATH_SEPARATOR = "/";
    public static ConcurrentHashMap<String, LcmConfigration> configs = new ConcurrentHashMap();
    private String appCode = "";
    private String secretKey = "";
    private String lcmServer = "";
    private String zkServer = "";

    // 设置配置文件路径
    public static void setConfigFile(String configFile){
        CONFIG_FILE = configFile ;
    }

    public static LcmConfigration getInstance(String configFile) {
        if(configs.containsKey(configFile)) {
            return (LcmConfigration)configs.get(configFile);
        } else {
            InputStream inputStream = LcmConfigration.class.getResourceAsStream(configFile);
            if(inputStream == null) {
                throw new LcmException("Can not load file " + configFile);
            } else {
                LcmConfigration var5;
                try {
                    Properties properties = new Properties();
                    properties.load(inputStream);
                    LcmConfigration config = new LcmConfigration(properties);
                    LcmConfigration existed = (LcmConfigration)configs.putIfAbsent(configFile, config);
                    if(existed != null) {
                        config = existed;
                    }

                    var5 = config;
                } catch (Exception var14) {
                    throw new LcmException("load scm config error", var14);
                } finally {
                    try {
                        inputStream.close();
                    } catch (IOException var13) {
                       // logger.error("close stream error", var13);
                    }

                }

                return var5;
            }
        }
    }

    public static LcmConfigration getInstance() {
        return getInstance(CONFIG_FILE);
    }

    private LcmConfigration(Properties properties) {
        this.appCode = (String)properties.get("appCode");
        this.secretKey = (String)properties.get("secretKey");
        this.zkServer = (String)properties.get("zkServer");
        Properties localCached = new Properties();
    }

    static {
        getInstance();
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getLcmServer() {
        return lcmServer;
    }

    public void setLcmServer(String lcmServer) {
        this.lcmServer = lcmServer;
    }

    public String getZkServer() {
        return zkServer;
    }

    public void setZkServer(String zkServer) {
        this.zkServer = zkServer;
    }
}
