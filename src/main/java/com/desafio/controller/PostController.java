package com.desafio.controller;

import com.desafio.dtos.ListPostDTO;
import com.desafio.dtos.PostDTO;
import com.desafio.entities.Posters;
import com.desafio.exceptions.NotSeller;
import com.desafio.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/products")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) { this.postService = postService; }

    @PostMapping("/newpost")
    public ResponseEntity<Posters> post(@RequestBody PostDTO postDTO) throws NotSeller {
        return ResponseEntity.ok(postService.post(postDTO));
    }

    @GetMapping("/followed/{idUser}/list")
    private ResponseEntity<ListPostDTO> listPost(@PathVariable Long idUser, @RequestParam(required = false) String order){
        return ResponseEntity.ok(postService.listPosters(idUser, order));
    }
}
