package cn.edu.dgut.integration.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"cn.edu.dgut.integration"})
public class AccountApplication {
    public static void main(String[] args) { SpringApplication.run(AccountApplication.class, args); }
}