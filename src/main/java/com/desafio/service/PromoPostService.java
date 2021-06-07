package com.desafio.service;

import com.desafio.dtos.ListPromoPostDTO;
import com.desafio.dtos.PromoPostDTO;
import com.desafio.dtos.QuantityDTO;
import com.desafio.entities.Posters;
import com.desafio.entities.PromoPost;
import com.desafio.entities.Seller;
import com.desafio.exceptions.IdNotFound;
import com.desafio.exceptions.NotSeller;
import com.desafio.repository.PromoPostRepository;
import com.desafio.repository.SellerRepository;
import com.desafio.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class PromoPostService {
    private final PromoPostRepository promoPostRepository;
    private final SellerService sellerService;
    private final SellerRepository sellerRepository;


    public PromoPostService(PromoPostRepository promoPostRepository, SellerService sellerService, SellerRepository sellerRepository) {
        this.promoPostRepository = promoPostRepository;
        this.sellerService = sellerService;
        this.sellerRepository = sellerRepository;

    }

    public PromoPost promoPost(PromoPostDTO promopostDTO) throws NotSeller {

        sellerService.findByIdSeller(promopostDTO.getIdSeller()).orElseThrow(NotSeller::new);

        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataPost = LocalDate.parse(promopostDTO.getDate(), dtf);

        PromoPost post = PromoPost.builder()
                .idSeller(promopostDTO.getIdSeller())
                .date(dataPost)
                .detail(promopostDTO.getDetail())
                .category(promopostDTO.getCategory())
                .price(promopostDTO.getPrice())
                .hasPromo(promopostDTO.getHasPromo())
                .discount(promopostDTO.getDiscount())
                .build();

        promoPostRepository.save(post);
        return post;
    }

    public QuantityDTO promoPostresQuantitu (Long idSeller) throws IdNotFound {
        Seller seller = sellerRepository.findById(idSeller).orElseThrow(() -> new IdNotFound(idSeller));
        List<PromoPost> list = promoPostRepository.findAllByIdSeller(idSeller);

        return QuantityDTO.builder().name(seller.getNameSaler())
                .id(seller.getIdSaler())
                .quantity(list.size())
                .build();

    }

    public ListPromoPostDTO promoProductList (Long idSeller) throws IdNotFound{
        Seller seller = sellerRepository.findById(idSeller).orElseThrow(() -> new IdNotFound(idSeller));
        List<PromoPost> list = promoPostRepository.findAllByIdSeller(idSeller);

        return ListPromoPostDTO.builder().name(seller.getNameSaler())
                .idUser(seller.getIdSaler()).posters(list).build();
    }

}
