package org.alexpask.controllers;

import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.Image;
import org.alexpask.model.DockerImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by alexpask on 24/06/2017.
 */
@RestController
@RequestMapping("/api")
public class StatusController {
    @Autowired
    private DockerClient docker;

    @GetMapping("/images")
    public List<DockerImage> listImages()
            throws DockerException, InterruptedException {
        final List<Image> images = docker.listImages();
        final List<DockerImage> dkimages;
        dkimages = images.stream()
                .map(image -> {
                    DockerImage dockerImage = new DockerImage();
                    dockerImage.setName(image.repoTags().get(0));
                    dockerImage.setImageId(image.id());
                    dockerImage.setCreated(image.created());
                    dockerImage.setSize(images.size());
                    return dockerImage;
                }).collect(Collectors.toList());
        return dkimages;
    }
}
