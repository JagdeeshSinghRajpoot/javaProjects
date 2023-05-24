
package com.simplilearn.storageservice.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.simplilearn.storageservice.entity.ImageData;
import com.simplilearn.storageservice.repository.StorageRepository;
import com.simplilearn.storageservice.util.ImageUtils;

@Service
public class StorageService {
	
	@Autowired
	private StorageRepository repository;
	
	public String uploadImage(MultipartFile file) throws IOException{
		//press ctrl + 2 then press l
		
		ImageData imageData = repository.save(ImageData.builder()
				.name(file.getOriginalFilename())
				.type(file.getContentType())
				.imageData(ImageUtils.compressImage(file.getBytes())).build());
		if(imageData != null) {
			return "File with the name : "+file.getOriginalFilename()+" is successfully uploaded";
		}
		return null;
	}
	
	public byte[] downloadImage(String fileName) {
		Optional<ImageData> dbImageData = repository.findByName(fileName);
		byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
		return images;
	}
}