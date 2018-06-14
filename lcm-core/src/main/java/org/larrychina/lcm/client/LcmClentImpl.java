package org.larrychina.lcm.client;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.larrychina.lcm.conf.LcmConfigration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by suning on 2018/5/17.
 */
public class LcmClentImpl implements LcmClient {

    private Map<String,LcmClient> clientMap = new ConcurrentHashMap() ;

    private CuratorFramework client ;

    protected LcmClentImpl(LcmConfigration configration){
        // 重试策略，超时时间1000ms,重试次数为10次
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,10) ;
        this.client = CuratorFrameworkFactory.newClient(configration.getZkServer(), retryPolicy) ;

    }

    @Override
    public void destory() {
        if(this.client == null){
            this.client.close();
        }
    }

    @Override
    public void createPathData(String path, String data) throws Exception {
        client.start();
        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path,data.getBytes());
        client.close();
    }

    @Override
    public void setPathData(String path, String data) throws Exception {
        client.start();
        if(this.client.checkExists().forPath(path)== null){
            createPathData(path,data);
        }
        client.setData().forPath(path,data.getBytes()) ;
        client.close();
    }

    @Override
    public void deletePathData(String path,String data) throws Exception{
        try {
            client.start();
            client.delete().forPath(path) ;
        }finally {
            client.close();
        }
    }
    @Override
    public String getPathData(String path) throws Exception{
        client.start();
        try {
            return new String(client.getData().forPath(path)) ;
        }finally {
            client.close();
        }
    }

}
