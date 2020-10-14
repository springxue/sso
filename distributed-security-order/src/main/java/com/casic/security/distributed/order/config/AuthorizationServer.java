package com.casic.security.distributed.order.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;

public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {
    //配置客户端详细信息服务
    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
            throws Exception {
// clients.withClientDetails(clientDetailsService);
        clients.inMemory()// 使用in‐memory存储
                .withClient("c1")// client_id
                .secret(new BCryptPasswordEncoder().encode("secret"))
                .resourceIds("res1")//客户端可以访问的资源列表
                .authorizedGrantTypes("authorization_code",
                        "password","client_credentials","implicit","refresh_token")// 该client允许的授权类型authorization_code,password,refresh_token,implicit,client_credentials
                .scopes("all")// 允许的授权范围
                .autoApprove(false)//false跳转授权的页面
//加上验证回调地址
                .redirectUris("http://www.baidu.com");
    }
}
