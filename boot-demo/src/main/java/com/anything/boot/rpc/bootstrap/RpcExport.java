package com.anything.boot.rpc.bootstrap;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2018/11/28 10:20
 */
public class RpcExport {
    static Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void exportService(String host,int port) throws IOException {

        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(host,port));

        while(true){
            executor.execute(new ExportHandle(server.accept()));
        }
    }
}

class ExportHandle implements Runnable{

    private Socket client;

    public ExportHandle(Socket client){
        this.client = client;
    }

    @Override
    public void run() {
        ObjectInputStream input = null;
        ObjectOutputStream output = null;
        try {
            input = new ObjectInputStream(client.getInputStream());
            String interfaceName = input.readUTF();
            Class<?> interfaceClass = Class.forName(interfaceName);
            String methodName = input.readUTF();
            Class<?>[] params = (Class<?>[])input.readObject();
            Object[] arguments = (Object[])input.readObject();
            Method method = interfaceClass.getMethod(methodName, params);
            Object result = method.invoke(interfaceClass.newInstance(),arguments);

            output = new ObjectOutputStream(client.getOutputStream());
            output.writeObject(result);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
