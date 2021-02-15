package com.practice.gallery.controller;

import com.practice.gallery.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "photos")
public class PhotoController {

    @Autowired
    PhotoService photoService;

    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/upload"
    )
    public String uploadPhoto(@RequestParam("file")MultipartFile file) {
        System.out.println("upload");
        return photoService.addPhoto(file);
    }
}
