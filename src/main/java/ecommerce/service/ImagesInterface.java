package ecommerce.service;

import ecommerce.dto.ImagesDto;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ImagesInterface {

    ResponseEntity<String> addImage(ImagesDto image);
    ResponseEntity<String> deleteImage(Integer id);
    ResponseEntity<String> updateImage(ImagesDto imagesDto);
    ResponseEntity<ImagesDto> getImage(Integer imageId);
    ResponseEntity<List<ImagesDto>> getAllImages();
}
