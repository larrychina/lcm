package org.larrychina.lcm.client;

import org.larrychina.lcm.conf.LcmConfigration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by suning on 2018/5/17.
 */
public class LcmClentImpl implements LcmClient {

    private Map<String,LcmClient> clientMap = new ConcurrentHashMap() ;

    protected LcmClentImpl(LcmConfigration configration){

    }
    @Override
    public void destory() {

    }
}
