package com.hello.rpsgame.repository;

import com.hello.rpsgame.domain.RpsChallenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;


public interface RpsChallengeRepository extends JpaRepository<RpsChallenge, Long> {
    List<RpsChallenge> findTop5ByUserAliasOrderByIdDesc(String userAlias);
}
