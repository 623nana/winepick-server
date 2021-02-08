package com.nexters.winepick.like.api;

import com.nexters.winepick.base.BaseResponse;
import com.nexters.winepick.like.api.dto.LikesRequest;
import com.nexters.winepick.like.service.LikesService;
import com.nexters.winepick.wine.api.dto.WineResponse;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikesController {

  private final LikesService likesService;

  @GetMapping("/{userId}")
  public BaseResponse<List<WineResponse>> getLikesWineList(@PathVariable Integer userId) {
    List<WineResponse> likes = likesService.getLikesWineList(userId);
    return new BaseResponse<>(200, "0", likes);
  }

  @PostMapping("")
  public BaseResponse addLike(@RequestBody LikesRequest likesRequest) {
    likesService.addLike(likesRequest);
    return new BaseResponse<>(200, "0", null);
  }

  @PutMapping("/{userId}/{wineId}")
  public BaseResponse deleteLike(@PathVariable Integer userId, @PathVariable Integer wineId) {
    likesService.deleteLike(userId, wineId);
    return new BaseResponse<>(200, "0", null);
  }

}
