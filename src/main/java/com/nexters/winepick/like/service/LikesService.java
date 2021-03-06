package com.nexters.winepick.like.service;

import com.nexters.winepick.like.api.dto.LikesRequest;
import com.nexters.winepick.like.domain.Likes;
import com.nexters.winepick.like.domain.Likes.UseYn;
import com.nexters.winepick.like.domain.LikesRepository;
import com.nexters.winepick.like.exception.LikeDuplicatedException;
import com.nexters.winepick.user.domain.User;
import com.nexters.winepick.user.repository.UserRepository;
import com.nexters.winepick.user.exception.UserNotFoundException;
import com.nexters.winepick.wine.api.dto.WineResponse;
import com.nexters.winepick.wine.domain.Wine;
import com.nexters.winepick.wine.domain.WineRepository;
import com.nexters.winepick.wine.exception.WineNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikesService {

  private final LikesRepository likesRepository;
  private final WineRepository wineRepository;
  private final UserRepository userRepository;

  public List<WineResponse> getLikesWineList(Integer userId) {
    List<WineResponse> likeList = likesRepository.findLikesByUserId(userId)
        .stream().map(WineResponse::of).collect(Collectors.toList());
    likeList.forEach(l -> l.setLikeYn(true));
    return likeList;
  }

  public void addLike(LikesRequest request) {
    if (likesRepository.existsLikesByWineIdAndUserId(request.getWineId(), request.getUserId())) {
      throw new LikeDuplicatedException();
    }

    Wine wine = wineRepository.findById(request.getWineId())
        .orElseThrow(() -> new WineNotFoundException(request.getWineId()));
    User user = userRepository.findById(request.getUserId())
        .orElseThrow(() -> new UserNotFoundException(request.getUserId()));

    likesRepository.save(Likes.of(user, wine, UseYn.Y));
  }

  public void deleteLike(Integer userId, Integer wineId) {
    likesRepository.findLikesByUserIdAndWineId(userId, wineId)
        .ifPresent(likesRepository::delete);
  }
}
