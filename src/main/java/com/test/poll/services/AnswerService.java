package com.test.poll.services;

import com.test.poll.dao.AnswerRepository;
import com.test.poll.domains.Answer;
import com.test.poll.domains.enums.Status;
import com.test.poll.exceptions.BusinessException;
import com.test.poll.utils.AssertsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.BatchUpdateException;
import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private AssertsUtils assertsUtils;

    public List<Answer> saveAnswers(List<Answer> answers, String idPoll) {
        this.assertsUtils.isNotNull(idPoll, "idPoll cannot be null!.");
        this.assertsUtils.isNotNull(answers, "answers cannot be null!.");

        answers.forEach(item->{
            item.setIdPoll(idPoll);
            item.setStatus(Status.ACTIVE.name());
        });
       return this.answerRepository.saveAll(answers);
    }

    public List<Answer> findAndswerByIdPoll(String idPoll) throws BusinessException {
        this.assertsUtils.isNotNull(idPoll, "idPoll cannot be null!.");
        List<Answer> answers = this.answerRepository.findByIdPoll(idPoll);

        if(answers.isEmpty()) {
            throw new BusinessException("answers not found for poll");
        }

        return answers;
    }

}
