package com.rutrob.recipesapi.repositories;

import com.rutrob.recipesapi.entities.Follow;
import com.rutrob.recipesapi.entities.embeddable.FollowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, FollowId> {

    @Query("SELECT f FROM Follow f WHERE f.id.followerId = :followerId AND f.id.followedId = :followedId")
    Optional<Follow> findByFollowerIdAndFollowedId(@Param("followerId") Long followerId, @Param("followedId") Long followedId);

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN TRUE ELSE FALSE END FROM Follow f WHERE f.id.followerId = :followerId AND f.id.followedId = :followedId")
    boolean existsByFollowerIdAndFollowedId(@Param("followerId") Long followerId, @Param("followedId") Long followedId);
}
