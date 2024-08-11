package com.rutrob.recipesapi.services;

import com.rutrob.recipesapi.entities.Follow;
import com.rutrob.recipesapi.repositories.FollowRepository;
import com.rutrob.recipesapi.repositories.UserRepository;
import com.rutrob.recipesapi.rest.dto.request.FollowRequest;
import com.rutrob.recipesapi.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public void createFollow(FollowRequest followRequest, Authentication authentication) {
        Long userId = ((SecurityUser) authentication.getPrincipal()).getUserId();
//        followRepository.findByFollowerIdAndFollowedId(userId, followRequest.followedId())
//                .ifPresent(follow -> {
//                    throw new RuntimeException("Follow already exists");
//                });

        if (followRepository.existsByFollowerIdAndFollowedId(userId, followRequest.followedId())) {
            throw new RuntimeException("Follow already exists");
        }

        Follow follow = new Follow(
                userRepository.getReferenceById(userId),
                userRepository.getReferenceById(followRequest.followedId())
        );

        followRepository.save(follow);

    }

    public void deleteFollow(FollowRequest followRequest, Authentication authentication) {
        Long userId = ((SecurityUser) authentication.getPrincipal()).getUserId();

        Follow follow = followRepository.findByFollowerIdAndFollowedId(userId, followRequest.followedId())
                .orElseThrow(() -> new RuntimeException("Follow not found"));

        followRepository.delete(follow);
    }
}
