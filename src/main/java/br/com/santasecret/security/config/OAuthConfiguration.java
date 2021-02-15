package br.com.santasecret.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
public class OAuthConfiguration {

    public static final String RESOURCE_ID = "santaSecret";

    @EnableAuthorizationServer
    public static class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

        @Autowired
        private ClientDetailsService clientDetailsService;

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private DataSource dataSource;

        //responsavel por salvar os tokens gerados
        @Bean
        public TokenStore tokenStore() {
            return new JdbcTokenStore(this.dataSource);
        }

        //onde é alocado as aprovações do resource onwer
        @Bean
        public ApprovalStore approvalStore() {
            return new JdbcApprovalStore(this.dataSource);
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
            oauthServer.tokenKeyAccess("permitAll()")
                    .checkTokenAccess("isAuthenticated()");
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            // adicionando configuração de scope e authorities.. sempre antes de qualquer requisicao os scopes são verificados
            //comparando com as authorities
            DefaultOAuth2RequestFactory requestFactory = new DefaultOAuth2RequestFactory(this.clientDetailsService);
            requestFactory.setCheckUserScopes(Boolean.TRUE);

            endpoints
                    .authenticationManager(this.authenticationManager)
                    .requestFactory(requestFactory)
                    .approvalStore(this.approvalStore())
                    .tokenStore(this.tokenStore());
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

            clients.jdbc(this.dataSource);

        }
    }

}
