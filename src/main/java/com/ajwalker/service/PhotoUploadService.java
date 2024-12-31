package com.ajwalker.service;

import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.HRAppException;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PhotoUploadService {
    private final Cloudinary cloudinary;

    public String uploadPhoto(MultipartFile file) throws IOException {
        final long MAX_FILE_SIZE = 1024 * 1024 * 5;
        if (!file.isEmpty()) {
            return null;
        }
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new HRAppException(ErrorType.PHOTO_SIZE_ERROR);
        }
        if (!Objects.requireNonNull(file.getOriginalFilename()).endsWith(".jpg") && !file.getOriginalFilename().endsWith(".png")) {
            throw new HRAppException(ErrorType.INVALID_PHOTO_TYPE);
        }
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        return uploadResult.get("url").toString();
    }
}
