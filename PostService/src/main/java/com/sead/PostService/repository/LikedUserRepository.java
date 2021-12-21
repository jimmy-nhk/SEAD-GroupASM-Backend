package com.sead.PostService.repository;

import com.sead.PostService.model.LikedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikedUserRepository extends JpaRepository<LikedUser, Long> {
}
