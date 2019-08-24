package com.mainul35.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ImportResource
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.jdbc.datasource.init.DataSourceInitializer
import org.springframework.jdbc.datasource.init.DatabasePopulator
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore

import javax.annotation.Resource
import javax.inject.Qualifier
import javax.sql.DataSource

@Configuration
@EnableAuthorizationServer
@ImportResource("classpath:application.properties")
class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Value("classpath:schema.sql")
    private Resource schemaScript;

    @Value(${"spring.datasource.driver-class-name"})
    private String driverClassName;

    @Value(${"spring.datasource.url"})
    private String dataSourceUrl;

    @Value(${"spring.datasource.username"})
    private String dataSourceUsername;

    @Value(${"spring.datasource.password"})
    private String dataSourcePassword;

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager

    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }

    private DatabasePopulator databasePopulator() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(schemaScript);
        return populator;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName)
        dataSource.setUrl(dataSourceUrl)
        dataSource.setUsername(dataSourceUsername)
        dataSource.setPassword(dataSourcePassword);
        return dataSource;
    }

    @Override
    void configure(
            AuthorizationServerSecurityConfigurer oauthServer)
            throws Exception {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
    }

    @Override
    void configure(ClientDetailsServiceConfigurer clients)
            throws Exception {
        clients.jdbc(dataSource())
                .withClient("sampleClientId")
                .authorizedGrantTypes("implicit")
                .scopes("read")
                .autoApprove(true)
                .and()
                .withClient("clientIdPassword")
                .secret("secret")
                .authorizedGrantTypes(
                        "password", "authorization_code", "refresh_token")
                .scopes("read")
    }

    @Override
    void configure(
            AuthorizationServerEndpointsConfigurer endpoints)
            throws Exception {

        endpoints
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
    }

    @Bean
    TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource())
    }
}
