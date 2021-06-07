package com.desafio.service;

import com.desafio.dtos.ListPostDTO;
import com.desafio.dtos.PostDTO;
import com.desafio.entities.Posters;
import com.desafio.entities.User;
import com.desafio.exceptions.IdNotFound;
import com.desafio.exceptions.NotSeller;
import com.desafio.repository.PostRepository;
import com.desafio.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final SellerService sellerService;
    private final UserRepository userRepository;
    private static final Integer WEEKS =  2;

    public PostService(PostRepository postRepository, SellerService sellerService, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.sellerService = sellerService;
        this.userRepository = userRepository;
    }

    public Posters post(PostDTO postDTO) throws NotSeller {

        sellerService.findByIdSeller(postDTO.getIdSeller()).orElseThrow(NotSeller::new);

        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataPost = LocalDate.parse(postDTO.getDate(), dtf);

        Posters post = Posters.builder()
                .idSeller(postDTO.getIdSeller())
                .date(dataPost)
                .detail(postDTO.getDetail())
                .category(postDTO.getCategory())
                .price(postDTO.getPrice())
                .build();

        postRepository.save(post);
        return post;
    }


    public ListPostDTO listPosters(Long idUser, String order) throws IdNotFound {
        User user = userRepository.findById(idUser).orElseThrow(() -> new IdNotFound(idUser));
        Map<Long, String> mapFollowing = user.getFollowing();
        List<Posters> listPost = new ArrayList<>();

        //pego o idSeller que o user segue
        Set<Long> setSeller = mapFollowing.keySet();
        //orderno por data
        if(order.equals("date_asc")){
            listPost = postRepository.findAllByIdSellerInOrderByDateAsc(setSeller);
        } else if (order.equals("date_desc")){
            listPost = postRepository.findAllByIdSellerInOrderByDateDesc(setSeller);
        }

        //filtro pelas duas ultimas semanas
        return ListPostDTO.builder()
                .name(user.getUserName())
                .idUser(user.getIdUser())
                .posters(listPost.stream().filter(post -> post.getDate().isAfter(LocalDate.now().minusWeeks(WEEKS))).collect(Collectors.toList()))
                .build();

    }
}
