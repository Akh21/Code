package com.Sample.Context.contextstructure.Config;

import com.Sample.Context.contextstructure.Mapper.JsonToMapConverter;
import com.Sample.Context.contextstructure.Mapper.MapToJsonConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactoryProvider;
import io.r2dbc.proxy.ProxyConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;
import static io.r2dbc.spi.ConnectionFactoryOptions.DATABASE;

@Configuration
public class R2dbcConfig extends AbstractR2dbcConfiguration {
    @Value("${spring.r2dbc.host}")
    private String host;
    @Value("${spring.r2dbc.port}")
    private int port;
    @Value("${spring.r2dbc.database}")
    private String database;
    @Value("${spring.r2dbc.username}")
    private String username;
    @Value("${spring.r2dbc.password}")
    private String password;
    @Value("${spring.r2dbc.driver}")
    private String driver;
    @Value("${spring.r2dbc.pool.initialSize}")
    private int initialSize;
    @Value("${spring.r2dbc.pool.maxSize}")
    private int maxSize;
    @Value("${spring.r2dbc.pool.maxIdleTimeInMinutes}")
    private int maxIdleTimeInMinutes;
    @Value("${spring.r2dbc.slowQueryThreshold:500}")
    private String slowQueryThreshold;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Bean
    @Override
    public ConnectionFactory connectionFactory() {

        ConnectionFactory proxyConnectionFactory =
                ProxyConnectionFactory.builder(
                                new PostgresqlConnectionFactoryProvider().create(getConnectionFactoryOptions()))
                        .build();

        ConnectionPoolConfiguration configuration =
                ConnectionPoolConfiguration.builder(proxyConnectionFactory)
                        .initialSize(initialSize)
                        .maxSize(maxSize)
                        .maxIdleTime(Duration.ofMinutes(maxIdleTimeInMinutes))
                        .build();

        return new ConnectionPool(configuration);
    }

    private ConnectionFactoryOptions getConnectionFactoryOptions() {
        return ConnectionFactoryOptions.builder()
                .option(DRIVER, driver)
                .option(HOST, host)
                .option(PORT, port)
                .option(USER, username)
                .option(PASSWORD, password)
                .option(DATABASE, database)
                .build();
    }

    @Bean
    public ConnectionPool connectionPool(ConnectionFactory connectionFactory) {
        return ((ConnectionPool) connectionFactory);
    }

    @Bean
    public ReactiveTransactionManager reactiveTransactionManager(
            ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }

    /**
     * r2dbcCustomConversions method used to convert json object fields to map and vice versa.
     *
     * @return R2dbcCustomConversions.
     */
    @Override
    @Bean
    public R2dbcCustomConversions r2dbcCustomConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(new JsonToMapConverter(objectMapper));
        converters.add(new MapToJsonConverter(objectMapper));
        return new R2dbcCustomConversions(getStoreConversions(), converters);
    }
}
