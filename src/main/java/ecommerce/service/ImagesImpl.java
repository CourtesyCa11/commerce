package ecommerce.service;

import ecommerce.domain.entities.Images;
import ecommerce.domain.entities.Product;
import ecommerce.domain.repository.ImagesRepository;
import ecommerce.domain.repository.ProductRepository;

import ecommerce.dto.ImagesDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagesImpl implements ImagesInterface{


    ImagesRepository imagesRepository;
    ProductRepository productRepository;

    @Autowired
    public ImagesImpl(ImagesRepository imagesRepository, ProductRepository productRepository) {
        this.imagesRepository = imagesRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<String> addImage(ImagesDto image) {
        Product product = productRepository.findById(image.getProductId()).orElseThrow(()-> new EntityNotFoundException("Product is not found"));
        Images images = new Images(image.getImageId(), image.getImage(),image.getColorImage(),product);
        imagesRepository.save(images);
        return new ResponseEntity<>("Image successfully is added ", HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<String> deleteImage(Integer id) {
        Images images = imagesRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Image is not found"));
        imagesRepository.delete(images);
        return new ResponseEntity<>("Image successfully is deleted", HttpStatus.ACCEPTED);
    }

    @Override
    @Transactional
    public ResponseEntity<String> updateImage(ImagesDto image) {
        Product product = productRepository.findById(image.getProductId()).orElseThrow(()-> new EntityNotFoundException("Product is not found"));
        Images images = imagesRepository.findById(image.getImageId()).orElseThrow(()-> new EntityNotFoundException("Image is not found"));
        images.setImage(images.getImage());
        images.setProduct(product);
        return new ResponseEntity<String>("Image successfully is updated", HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<ImagesDto> getImage(Integer imageId) {
        Images images = imagesRepository.findById(imageId).orElseThrow(()-> new EntityNotFoundException("Image is not found"));
        ImagesDto dto = new ImagesDto(images.getId(), images.getProduct().getProductId(),images.getImage(), images.getColorImage());
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ImagesDto>> getAllImages() {
        List<Images> imagesEntity = imagesRepository.findAll();
        List<ImagesDto> dtos = imagesEntity.stream().map(images -> new ImagesDto(images.getProduct().getProductId(),images.getId(),images.getImage(),images.getColorImage())).toList();
        return new ResponseEntity<>(dtos,HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<List<ImagesDto>> getImagesByProduct(Integer productId) {
        Product product = productRepository.findById(productId).orElseThrow(()-> new EntityNotFoundException("Product is not found"));
        List<Images> imageEntity = imagesRepository.findByproduct(product);
        List<ImagesDto> dtos = imageEntity.stream().map(images -> new ImagesDto(images.getProduct().getProductId(),images.getId(),images.getImage(),images.getColorImage())).toList();
        return new ResponseEntity<>(dtos,HttpStatus.ACCEPTED);
    }
}
