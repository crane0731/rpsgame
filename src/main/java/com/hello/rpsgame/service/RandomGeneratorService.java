package com.hello.rpsgame.service;

import com.hello.rpsgame.enums.RockPaperScissors;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class RandomGeneratorService {

    public RockPaperScissors getRockPaperScissors(){
        return RockPaperScissors.randomRps();
    }
}
