package com.challenge.service;

import com.challenge.entity.Product;
import com.challenge.entity.Publication;
import com.challenge.entity.Retailer;
import com.challenge.exception.PromotionalNotFound;
import com.challenge.exception.RetailerNotFound;
import com.challenge.model.product.ProductDTO;
import com.challenge.model.publication.CreatePublicationDTO;
import com.challenge.model.publication.PublicationDTO;
import com.challenge.model.user.RetailerPromoProductsDTO;
import com.challenge.repository.PublicationRepository;
import com.challenge.repository.PublicationSpecification;
import com.challenge.repository.RetailerRepository;
import com.challenge.repository.UserRetailerRepository;
import org.hibernate.criterion.Order;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublicationService {

    private final PublicationRepository publicationRepository;

    private final RetailerRepository retailerRepository;

    private final UserRetailerRepository userRetailerRepository;

    private final ModelMapper mapper;

    public PublicationService(PublicationRepository publicationRepository, ModelMapper mapper, RetailerRepository retailerRepository, UserRetailerRepository userRetailerRepository) {
        this.publicationRepository = publicationRepository;
        this.mapper = mapper;
        this.retailerRepository = retailerRepository;
        this.userRetailerRepository = userRetailerRepository;
    }

    public void publish(CreatePublicationDTO dto) throws RetailerNotFound{
        Product product = mapper.map(dto.getDetail(), Product.class);
        Retailer retailer = retailerRepository.findById(dto.getUserId()).orElseThrow(() -> new RetailerNotFound(dto.getUserId()));
        Publication publication = Publication.builder().retailer(retailer).date(dto.getDate()).category(dto.getCategory()).price(dto.getPrice()).product(product).discount(dto.getDiscount()).hasPromo(dto.getHasPromo()).build();
        publicationRepository.save(publication);
    }

    public List<PublicationDTO> find(Integer userId, String order) {
        List<Publication> all;
        if(order != null) {
            all = order.endsWith("desc") ? publicationRepository.findAll(new PublicationSpecification(userId), Sort.by(Sort.Direction.DESC, "date"))
                    : publicationRepository.findAll(new PublicationSpecification(userId), Sort.by(Sort.Direction.ASC, "date"));
        } else {
            all = publicationRepository.findAll(new PublicationSpecification(userId));
        }
        return all.stream().map(this::toDTO).filter(p -> p.getDate().isAfter(LocalDate.now().minusWeeks(2))).collect(Collectors.toList());
    }

    public RetailerPromoProductsDTO countPromo(Integer userId) throws PromotionalNotFound {
        List<Publication> publications = publicationRepository.findAllByRetailer_IdAndHasPromo(userId, true).get();
        if(publications.isEmpty()) {
            throw new PromotionalNotFound(userId);
        }
        Retailer retailer = publications.get(0).getRetailer();
        return RetailerPromoProductsDTO.builder().userName(retailer.getName()).userId(retailer.getId()).promoproducts_count(publications.size()).build();
    }

    private PublicationDTO toDTO(Publication publication) {
        Product product = publication.getProduct();
        return PublicationDTO.builder().id(publication.getId())
                .date(publication.getDate())
                .detail(ProductDTO.builder().id(product.getId()).productName(product.getProductName()).brand(product.getBrand())
                        .color(product.getColor()).notes(product.getNotes()).type(product.getType()).build())
                .category(publication.getCategory().getCode())
                .price(publication.getPrice())
                .build();
    }
}
