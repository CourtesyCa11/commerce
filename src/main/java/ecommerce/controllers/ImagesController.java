package ecommerce.controllers;

import ecommerce.dto.ImagesDto;

import ecommerce.service.ImagesInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ImagesController   {

    @Autowired
    ImagesInterface service;

    @PostMapping(value = {"/image/add"})
    public ResponseEntity<String> addImage(@RequestBody ImagesDto image) {
        return service.addImage(image);
    }

    @DeleteMapping(value = {"/image/delete/{id}"})
    public ResponseEntity<String> deleteImage(@PathVariable Integer id) {
        return service.deleteImage(id);
    }

    @PutMapping(value = {"/image/update"})
    public ResponseEntity<String> updateImage(@ RequestBody ImagesDto imagesDto) {
        return service.updateImage(imagesDto);
    }

    @GetMapping(value = {"/image/get/{imageId}"})
    public ResponseEntity<ImagesDto> getImage(@PathVariable Integer imageId) {
        return service.getImage(imageId);
    }

    @GetMapping(value = {"/image/get/all"})
    public ResponseEntity<List<ImagesDto>> getAllImages() {
        return service.getAllImages();
    }
}
