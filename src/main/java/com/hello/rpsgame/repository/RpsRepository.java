package com.hello.rpsgame.repository;

import com.hello.rpsgame.domain.Rps;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RpsRepository extends JpaRepository<Rps,Long> {

}
