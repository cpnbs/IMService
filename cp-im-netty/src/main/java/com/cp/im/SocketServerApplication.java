package com.cp.im;

import com.cp.im.application.socket.NettySocketServer;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Socket服务.
 */

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
@EnableAsync
@ComponentScan(basePackages = {"com.cp"})
public class SocketServerApplication implements CommandLineRunner {

  @Autowired
  private NettySocketServer socketServer;

  public static void main(String[] args) {
    SpringApplication.run(SocketServerApplication.class, args);
  }

  @Override
  public void run(String... args) {
    CompletableFuture.runAsync(() -> this.socketServer.startServer());
  }

}