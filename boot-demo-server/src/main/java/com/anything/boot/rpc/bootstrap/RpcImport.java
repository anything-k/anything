package com.anything.boot.rpc.bootstrap;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2018/11/28 13:53
 */
public class RpcImport<S> {

    public S importer(final Class<?> serviceClass, InetSocketAddress addr){
        return (S) Proxy.newProxyInstance(serviceClass.getClassLoader(),
                new Class<?>[]{serviceClass.getInterfaces()[0]},
                    (proxy,method,args)->{
                        Socket socket = null;
                        ObjectOutputStream output = null;
                        ObjectInputStream input = null;

                        try {
                            socket = new Socket();
                            socket.connect(addr);
                            output = new ObjectOutputStream(socket.getOutputStream());
                            output.writeUTF(serviceClass.getName());
                            output.writeUTF(method.getName());
                            output.writeObject(method.getParameterTypes());
                            output.writeObject(args);

                            input = new ObjectInputStream(socket.getInputStream());
                            return input.readObject();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            if(socket != null){
                                socket.close();
                            }

                            if(output != null){
                                output.close();
                            }

                            if(input != null){
                                input.close();
                            }
                        }

                        return null;
                    });
    }
}
