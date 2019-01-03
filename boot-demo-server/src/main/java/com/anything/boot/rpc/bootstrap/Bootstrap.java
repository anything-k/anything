package com.anything.boot.rpc.bootstrap;

import com.anything.boot.rpc.service.EchoService;
import com.anything.boot.rpc.service.EchoServiceImpl;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2018/11/28 14:21
 */
public class Bootstrap {
    public static void main(String[] args){
        new Thread(()->{
            try {
                RpcExport.exportService("localhost",8001);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        RpcImport<EchoService> rpcImport = new RpcImport<>();
        EchoService echoService = rpcImport.importer(EchoServiceImpl.class, new InetSocketAddress("localhost", 8001));
        System.out.println(echoService.echo("king"));
    }
}
