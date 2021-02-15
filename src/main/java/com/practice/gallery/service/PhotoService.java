package com.practice.gallery.service;

import com.practice.gallery.dao.PhotoDao;
import com.practice.gallery.firestore.FireStore;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;

@Service
public class PhotoService {

    @Autowired
    PhotoDao photoDao;
    @Autowired
    FireStore fireStore;

    public String addPhoto(MultipartFile file) {
        if(file.isEmpty())
            throw new IllegalStateException("no file provided");

        if(!Arrays.asList(ContentType.IMAGE_JPEG.getMimeType(),
                ContentType.IMAGE_PNG.getMimeType(),
                ContentType.IMAGE_GIF.getMimeType()).contains(file.getContentType()))
            throw new IllegalStateException("Not an image");

        HashMap<String,String> metadata = new HashMap<>();
        metadata.put("Content-Type",file.getContentType());
        metadata.put("Content-Length",String.valueOf(file.getSize()));

        String path = FireStore.bucketName;
        fireStore.upload(path,file.getOriginalFilename(), InputStream.nullInputStream(), metadata);
        return file.getName();
    }
}
