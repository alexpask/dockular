package org.alexpask.controllers;

import org.alexpask.model.DockerImage;

import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.Image;
import com.spotify.docker.client.messages.ImageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Rest controller wrapping Docker api.
 *
 * Created by alexpask on 24/06/2017.
 */
@RestController
@RequestMapping("/api")
public class StatusController {
    @Autowired
    private DockerClient docker;

    @GetMapping("/images")
    public List<DockerImage> images()
            throws DockerException, InterruptedException {
        final List<Image> images = docker.listImages();
        final List<DockerImage> dockerImages;
        dockerImages = images.stream()
                .map(image -> {
                    DockerImage dockerImage = new DockerImage();
                    dockerImage.setName(image.repoTags().get(0).split(":")[0]);
                    dockerImage.setTag(image.repoTags().get(0).split(":")[1]);
                    dockerImage.setImageId(image.id().split(":")[1]);
                    dockerImage.setCreated(Instant.ofEpochSecond(Long.parseLong(image.created())).toString());
                    dockerImage.setSize(images.size());
                    return dockerImage;
                }).collect(Collectors.toList());
        return dockerImages;
    }

    @GetMapping("/images/{id}")
    public ImageInfo imageDetail(@PathVariable("id") String id)
            throws DockerException, InterruptedException {
        ImageInfo imageInfo = docker.inspectImage("sha256:" + id);
        return imageInfo;
    }
}
