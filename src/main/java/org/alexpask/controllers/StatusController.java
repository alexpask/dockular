package org.alexpask.controllers;

import org.alexpask.model.DockerImage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexpask on 24/06/2017.
 */
@RestController
public class StatusController {
    @GetMapping("/listimages")
    public List<DockerImage> listImages() {
        DockerImage image = new DockerImage();
        List<DockerImage> list = new ArrayList<DockerImage>();
        list.add(image);
        image.setName("hello-world");
        return list;
    }
}
