package org.alexpask.controllers;

import org.alexpask.model.DockerImage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexpask on 24/06/2017.
 */
@RestController
@RequestMapping("/api")
public class StatusController {
    @GetMapping("/images")
    public List<DockerImage> listImages() {
        DockerImage image = new DockerImage();
        DockerImage image2 = new DockerImage();
        image2.setName("mysql");
        List<DockerImage> list = new ArrayList<DockerImage>();
        list.add(image);
        list.add(image2);
        image.setName("hello-world");
        return list;
    }
}
