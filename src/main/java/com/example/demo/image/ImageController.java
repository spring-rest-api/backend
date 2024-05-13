package com.example.demo.image;

 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "api/v1/image")
@CrossOrigin(origins = "http://localhost:5173")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public String getNewImageUrl() {
        return imageService.getNewImageUrl();
    }



}
