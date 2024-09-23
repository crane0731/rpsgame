package com.hello.rpsgame.domain;

import com.hello.rpsgame.enums.GameResult;
import com.hello.rpsgame.enums.RockPaperScissors;
import jakarta.persistence.*;
import lombok.*;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
public class RpsChallenge {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="user_id")
    private final User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="rpsplay_id")
    private  final Rps rps;

    private final RockPaperScissors opponent;
    private final GameResult gameResult;

    public RpsChallenge() {
        user = null;
        rps = null;
        opponent = null;
        gameResult = null;
    }
}
