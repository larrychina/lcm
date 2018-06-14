package org.larrychina.lcm.client;

/**
 * Created by suning on 2018/5/16.
 */
public interface LcmClient {

    void destory() ;

    void createPathData(String path,String data) throws Exception;

    void setPathData(String path ,String data) throws Exception ;

    public void deletePathData(String path,String data) throws Exception;

    public String getPathData(String path) throws Exception;
 }


