package org.alexpask.controllers;

import org.alexpask.model.DockerCount;
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
        return images.stream()
                .map(image -> {
                    String[] repoTag = image.repoTags().get(0).split(":");
                    DockerImage dockerImage = new DockerImage();
                    dockerImage.setName((repoTag[0].equals("<none>")) ? "&ltnone&gt" : repoTag[0]);
                    dockerImage.setTag((repoTag[1].equals("<none>")) ? "&ltnone&gt" : repoTag[1]);
                    dockerImage.setImageId(image.id().split(":")[1]);
                    dockerImage.setCreated(Instant.ofEpochSecond(Long.parseLong(image.created())).toString());
                    dockerImage.setSize(images.size());
                    return dockerImage;
                }).collect(Collectors.toList());
    }

    @GetMapping("/images/{id}")
    public ImageInfo imageDetail(@PathVariable("id") String id)
            throws DockerException, InterruptedException {
        ImageInfo imageInfo = docker.inspectImage("sha256:" + id);
        return imageInfo;
    }

    @GetMapping("/images/info/count")
    public DockerCount dockerCount()
            throws DockerException, InterruptedException {
        final List<Image> images = docker.listImages();
        return new DockerCount(images.size());
    }
}
