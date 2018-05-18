package org.larrychina.lcm.client;

import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.larrychina.lcm.conf.LcmConfigration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by suning on 2018/5/17.
 */
public class LcmClentImpl implements LcmClient {

    private Map<String,LcmClient> clientMap = new ConcurrentHashMap() ;

    protected LcmClentImpl(LcmConfigration configration){
        // 重试策略，超时时间1000ms,重试次数为10次
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,10) ;
        CuratorFramework client = CuratorFrameworkFactory.newClient(configration.getZkServer(), retryPolicy) ;
        client.start();

    }
    @Override
    public void destory() {

    }
}
