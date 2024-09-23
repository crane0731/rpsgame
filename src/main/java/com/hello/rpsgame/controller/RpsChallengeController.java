package com.hello.rpsgame.controller;

import com.hello.rpsgame.domain.Rps;
import com.hello.rpsgame.domain.RpsChallenge;
import com.hello.rpsgame.domain.User;
import com.hello.rpsgame.dto.RequestDto;
import com.hello.rpsgame.dto.ResultDto;
import com.hello.rpsgame.enums.RockPaperScissors;
import com.hello.rpsgame.service.RpsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/results")
public class RpsChallengeController {

    private final RpsService rpsService;

    private RockPaperScissors stringToRockPaperScissors(String code) {
        RockPaperScissors result=null;

        for(RockPaperScissors rps: RockPaperScissors.values()) {
            log.info("RockPaperScissors value: " + rps.getCommentary());
            if(rps.getCommentary().equals(code)) {
                result = rps;
                break;
            }
        }
        return result;
    }

    @PostMapping
    Map<String,String> postResult(@RequestBody RequestDto dto) {
        User user = new User(dto.userAlias());
        log.info("userChoice: " + dto.userAlias());
        Rps rps = new Rps(stringToRockPaperScissors(dto.userChoice()));

        RpsChallenge rpsChallenge = new RpsChallenge(user,rps,null, null);
        Map<String,String> map = rpsService.checkChallenge(rpsChallenge);
        log.info("outcome: "+map.get("outcome"));
        log.info("opponent: "+map.get("opponent"));
        return map;
    }

    @GetMapping
    List<ResultDto> getStatistics(@RequestParam String alias){
        List<RpsChallenge> challenges= rpsService.getStateForUser(alias);
        List<ResultDto> results = new ArrayList<>();
        for (RpsChallenge challenge : challenges) {
            ResultDto result = new ResultDto(challenge.getId()
                    ,challenge.getRps().getChallenge().getCommentary()
                    ,challenge.getOpponent().getCommentary()
                    ,challenge.getGameResult().getCommentary()
                    ,challenge.getUser().getId());
            results.add(result);
        }
        return results;
    }
}
