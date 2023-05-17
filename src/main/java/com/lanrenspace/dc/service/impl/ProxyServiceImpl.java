package com.lanrenspace.dc.service.impl;

import com.lanrenspace.dc.service.IProxyService;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;

/**
 * @ClassName: ProxyServiceImpl
 * @Author: LanRenSpace
 * @Description: TODO
 **/
@Service
public class ProxyServiceImpl implements IProxyService {
    @Override
    public Proxy getProxy() {
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
        return proxy;
    }
}
