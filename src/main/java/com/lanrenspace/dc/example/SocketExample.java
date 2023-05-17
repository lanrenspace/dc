package com.lanrenspace.dc.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.util.Objects;

/**
 * @ClassName: SocketExample
 * @Author: LanRenSpace
 * @Description: TODO
 **/
public class SocketExample {

    public static void main(String[] args) throws IOException {
        // 目标网站
        String targetUrl = "https://dev.kdlapi.com/testproxy";

        // 用户名密码认证(私密代理/独享代理)
        final String username = "t18430639867429";
        final String password = "iyaalgkn";

        String ip = "n203.kdltps.com";   // 代理服务器IP
        int port = 20818;

        Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(ip, port));
        java.net.Authenticator.setDefault(new java.net.Authenticator() {
            private final PasswordAuthentication authentication = new PasswordAuthentication(username, password.toCharArray());

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return authentication;
            }
        });

        OkHttpClient client = new OkHttpClient.Builder()
                .proxy(proxy)
                .build();

        Request request = new Request.Builder()
                .url(targetUrl)
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3100.0 Safari/537.36")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(Objects.requireNonNull(response.body()).string());
    }
}
