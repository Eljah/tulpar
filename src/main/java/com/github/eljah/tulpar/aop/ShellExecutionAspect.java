package com.github.eljah.tulpar.aop;

import com.github.eljah.tulpar.annotation.RemoteShell;
import com.jcraft.jsch.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.aspectj.lang.ProceedingJoinPoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ilya on 29.02.16.
 */
public class ShellExecutionAspect {

    private String user;

    private String host;

    private String password;


    public void setUser(String user) {
        this.user = user;
    }


    public void setHost(String host) {
        this.host = host;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void before() {
        System.out.println("Shell started!!!");
    }

    public void after() {
        System.out.println("Shell ended!!!");
    }


    public Object callRemoteShell(ProceedingJoinPoint joinPoint) throws Throwable {
        Class clazz = joinPoint.getTarget().getClass();
        //Method method = clazz.getMethod(joinPoint.getSignature().getName());
        //RemoteShell remote = method.getAnnotation(RemoteShell.class);
        String action = (String) joinPoint.proceed();
        System.out.println("Command for execution obtained, will be passed to ssh remotely");

        JSch jsch = new JSch();
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");


        String lastInputString = "";

        try {
            Session session = jsch.getSession(user, host, 22);
            session.setPassword(password);
            session.setConfig(config);
            //todo user info
            session.connect(30000);
            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(action);

            // X Forwarding
            // channel.setXForwarding(true);

            //channel.setInputStream(System.in);
            channel.setInputStream(null);

            //channel.setOutputStream(System.out);

            //FileOutputStream fos=new FileOutputStream("/tmp/stderr");
            //((ChannelExec)channel).setErrStream(fos);
            ((ChannelExec) channel).setErrStream(System.err);

            InputStream in = channel.getInputStream();

            channel.connect();


            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) break;
                    String buff = new String(tmp, 0, i);
                    System.out.print(buff);
                    lastInputString = lastInputString + buff;
                }
                if (channel.isClosed()) {
                    if (in.available() > 0) continue;
                    System.out.println("exit-status: " + channel.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                }
            }
            channel.disconnect();
            session.disconnect();

        } catch (JSchException e) {
            e.printStackTrace();
            throw new RuntimeException("SchException e");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("IOException e");
        }


        return lastInputString;
    }

    public Object callLocalShell(ProceedingJoinPoint joinPoint) throws Throwable {
        Class clazz = joinPoint.getTarget().getClass();
        //Method method = clazz.getMethod(joinPoint.getSignature().getName());
        //RemoteShell jsch = method.getAnnotation(RemoteShell.class);
        String action = (String) joinPoint.proceed();
        System.out.println("Command for execution obtained, will be passed to local shell");


        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(action);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();
    }


    public Object callHttpRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        Class clazz = joinPoint.getTarget().getClass();
        //Method method = clazz.getMethod(joinPoint.getSignature().getName());
        //RemoteShell jsch = method.getAnnotation(RemoteShell.class);
        List<String> actions = (List<String>) joinPoint.proceed();
        List<String> toRespond=new LinkedList<String >(){};
        for (String action: actions) {
            System.out.println("Command for execution obtained, will be passed to local shell");

            String url = "http://localhost:8888/data/" + action + "/"+Math.random()*100;
            System.out.println(url);
            StringBuffer result = new StringBuffer();
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);

            // add request header
            HttpResponse response = client.execute(request);

            System.out.println("Response Code : "
                    + response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            toRespond.add(result.toString());
        }
        return toRespond;
    }


}
