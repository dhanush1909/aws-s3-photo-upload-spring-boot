package com.practice.gallery.firestore;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Map;

@Service
public class FireStore {

    public static final String bucketName = "gallery-project";
    @Autowired
    AmazonS3 s3;

    public void upload(String path, String fileName, InputStream inputStream, Map<String, String> metadata) {
        try {
            ObjectMetadata objMetadata = new ObjectMetadata();
            metadata.forEach(objMetadata::addUserMetadata);
            s3.putObject(path,fileName,inputStream,objMetadata);
        } catch (AmazonServiceException e) {
            throw new IllegalStateException("failed to upload to s3", e);
        }

    }
}
