package com.fhtw.tpserver;

import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class DockerShutdown {

    @PreDestroy
    public void onExit() {
        try {
            ProcessBuilder pb = new ProcessBuilder("docker-compose", "down");
            pb.directory(new File("tpserver"));
            pb.inheritIO();
            pb.start().waitFor();
            System.out.println("Docker containers shut down.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}