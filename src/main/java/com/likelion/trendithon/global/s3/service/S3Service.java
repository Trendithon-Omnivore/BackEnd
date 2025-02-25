package com.likelion.trendithon.global.s3.service;

import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.likelion.trendithon.global.config.S3Config;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class S3Service {

  private final AmazonS3 amazonS3;
  private final S3Config s3Config;

  public String uploadFile(MultipartFile file) {

    validateFile(file);

    String keyName = createKeyName();

    ObjectMetadata metadata = new ObjectMetadata();
    metadata.setContentLength(file.getSize());
    metadata.setContentType(file.getContentType());

    try {
      amazonS3.putObject(
          new PutObjectRequest(s3Config.getBucket(), "review", file.getInputStream(), metadata));
      return amazonS3.getUrl(s3Config.getBucket(), "review").toString();
    } catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  public String base64UploadFile(String base64Url) {

    String base64Data = base64Url;
    String contentType = "image/png";

    if (base64Url.contains(",")) {
      String[] parts = base64Url.split(",");
      if (parts[0].contains("data:") && parts[0].contains(";base64")) {
        contentType = parts[0].substring(5, parts[0].indexOf(";"));
      }
      base64Data = parts[1];
    }

    byte[] decodedBytes = Base64.getDecoder().decode(base64Data);
    String keyName = createKeyName();

    ObjectMetadata metadata = new ObjectMetadata();
    metadata.setContentLength(decodedBytes.length);
    metadata.setContentType(contentType);

    try (ByteArrayInputStream inputStream = new ByteArrayInputStream(decodedBytes)) {
      amazonS3.putObject(
          new PutObjectRequest(s3Config.getBucket(), keyName, inputStream, metadata));
      return amazonS3.getUrl(s3Config.getBucket(), keyName).toString();
    } catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  public String createKeyName() {

    return "review/" + UUID.randomUUID().toString();
  }

  public String getFileUrl(String keyName) {
    existFile(keyName);

    try {
      String fileUrl = amazonS3.getUrl(s3Config.getBucket(), keyName).toString();
      return fileUrl;
    } catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  public void deleteFile(String keyName) {
    existFile(keyName);

    try {
      amazonS3.deleteObject(new DeleteObjectRequest(s3Config.getBucket(), keyName));
    } catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  private void existFile(String keyName) {
    if (!amazonS3.doesObjectExist(s3Config.getBucket(), keyName)) {
      throw new IllegalArgumentException();
    }
  }

  private void validateFile(MultipartFile file) {
    if (file.getSize() > 5 * 1024 * 1024) {
      throw new IllegalArgumentException();
    }

    String contentType = file.getContentType();
    if (contentType == null || !contentType.startsWith("image/")) {
      throw new IllegalArgumentException();
    }
  }
}
