package com.hello.rpsgame.service;


import com.hello.rpsgame.domain.RpsChallenge;
import com.hello.rpsgame.domain.User;
import com.hello.rpsgame.enums.GameResult;
import com.hello.rpsgame.enums.RockPaperScissors;
import com.hello.rpsgame.repository.RpsChallengeRepository;
import com.hello.rpsgame.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RpsService {
    private final RandomGeneratorService randomGeneratorService;
    private final RpsChallengeRepository rpsChallengeRepository;
    private final UserRepository userRepository;

    private RockPaperScissors createRandomRps(){
        return randomGeneratorService.getRockPaperScissors();
    }

    private GameResult checkScore(RockPaperScissors userRps, RockPaperScissors computerRps){
        GameResult result = GameResult.LOST;

        if (userRps == computerRps){
            result=GameResult.TIE;
        }
        else if (userRps == RockPaperScissors.SCISSORS){
            if(computerRps == RockPaperScissors.Rock){
                result=GameResult.LOST;
            }
            else{
                result=GameResult.WON;
            }

        }
        else if (userRps == RockPaperScissors.Rock){
            if(computerRps == RockPaperScissors.SCISSORS){
                result=GameResult.WON;
            }
            else{
                result=GameResult.LOST;
            }
        } else if (userRps == RockPaperScissors.PAPER) {
            if(computerRps == RockPaperScissors.SCISSORS){
                result=GameResult.LOST;
            }
            else{
                result=GameResult.WON;
            }
        }
        return result;
    }

    @Transactional
    public Map<String, String> checkChallenge(RpsChallenge rpsChallenge){
        Map<String, String> map = new HashMap<>();
        Optional<User> user = userRepository.findByAlias(rpsChallenge.getUser().getAlias());

        Assert.isNull(rpsChallenge.getGameResult(), "완료된 상태를 보낼 수 없습니다.");

        RockPaperScissors computerChoice = createRandomRps();
        GameResult gameResult=checkScore(rpsChallenge.getRps().getChallenge(),computerChoice);

        RpsChallenge checkedChallenge = new RpsChallenge(user.orElse(rpsChallenge.getUser()),rpsChallenge.getRps(),computerChoice,gameResult);

        rpsChallengeRepository.save(checkedChallenge);
        map.put("opponent",computerChoice.getCommentary());
        map.put("outcome",checkedChallenge.getGameResult().getCommentary());
        map.put("userId", ""+checkedChallenge.getUser().getId());

        return map;
    }

    public List<RpsChallenge> getStateForUser(String userAlias){
        return rpsChallengeRepository.findTop5ByUserAliasOrderByIdDesc(userAlias);
    }
}
