package org.alexpask.config;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by alexpask on 25/06/2017.
 */
@Configuration
public class DockerConfig {
    @Bean
    public DockerClient docker() {
        return DefaultDockerClient
                .builder()
                .uri("unix:///var/run/docker.sock")
                .build();
    }
}
