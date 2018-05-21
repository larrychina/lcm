package org.larrychina.lcm.client;

import org.larrychina.lcm.exception.LcmException;

/**
 * Created by suning on 2018/5/16.
 */
public interface LcmClient {

    void destory() ;

    void createPathData(String path,String data) throws Exception;

    void setPathData(String path ,String data) throws Exception ;
 }


