package net.inpercima.serverconnector;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "app")
@Getter
@Setter
public class ServerProperties {

    private List<String> commands;

    private String host;

    private String password;

    private Integer port;

    private String user;
}
