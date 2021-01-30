package com.nexters.winepick.survey.domain;

import com.nexters.winepick.base.BaseEntity;
import com.nexters.winepick.survey.api.constant.AnswerType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Answer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    @Enumerated(EnumType.STRING)
    private AnswerType answerType;
}