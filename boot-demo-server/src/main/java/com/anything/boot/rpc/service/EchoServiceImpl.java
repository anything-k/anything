package com.anything.boot.rpc.service;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2018/11/28 10:17
 */
public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String ping) {
        return "hello," + ping;
    }
}
