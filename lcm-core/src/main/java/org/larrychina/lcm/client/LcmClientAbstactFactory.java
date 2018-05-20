package org.larrychina.lcm.client;

import org.larrychina.lcm.conf.LcmConfigration;
import org.larrychina.lcm.exception.LcmException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by suning on 2018/5/17.
 */
public class LcmClientAbstactFactory {

    private final static ConcurrentMap<String, LcmClient> clientMap = new ConcurrentHashMap<>() ;

    private static ReentrantLock lock = new ReentrantLock(true) ;

    // 设置配置信息
    public static void setConfig(String configPath){
        LcmConfigration.CONFIG_FILE = configPath ;
    }

    public LcmClientAbstactFactory() {
    }

    public static LcmClient getLcmClient() throws LcmException{
        return getLcmClient(LcmConfigration.CONFIG_FILE) ;
    }

    public static LcmClient getLcmClient(String fileConfigPath) throws LcmException{
        LcmClient lcmClient = clientMap.get(fileConfigPath);
        if(lcmClient != null){
            return lcmClient ;
        }
        try {
            lock.tryLock(1000, TimeUnit.MILLISECONDS) ;
            lcmClient = clientMap.get(fileConfigPath);
            if(lcmClient == null){
                LcmConfigration instance = LcmConfigration.getInstance(fileConfigPath);
                lcmClient = new LcmClentImpl(instance);
                clientMap.putIfAbsent(fileConfigPath,lcmClient) ;
            }
            return lcmClient ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        if(lcmClient == null){
            throw new LcmException("lcmClient is null....") ;
        }
        return lcmClient ;
    }


}
