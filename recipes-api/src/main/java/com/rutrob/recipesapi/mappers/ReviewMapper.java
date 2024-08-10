package com.rutrob.recipesapi.mappers;

import com.rutrob.recipesapi.entities.Review;
import com.rutrob.recipesapi.rest.dto.ReviewDto;
import com.rutrob.recipesapi.rest.dto.CreateReviewRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ReviewMapper {

    Review toEntity(CreateReviewRequest reviewRequest);

    @Mapping(source = "user.username", target = "username")
    ReviewDto toDto(Review review);
}
