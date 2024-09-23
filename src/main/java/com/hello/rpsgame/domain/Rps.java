package com.hello.rpsgame.domain;

import com.hello.rpsgame.enums.RockPaperScissors;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
public class Rps {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="rpsplay_id")
    private Long id;

    private final RockPaperScissors challenge;

    public Rps() {
        this(null);
    }


}
