package com.test.poll.services;


import com.test.poll.dao.AnswerRepository;
import com.test.poll.dao.VoteUserRepository;
import com.test.poll.domains.Answer;
import com.test.poll.domains.VoteUser;
import com.test.poll.exceptions.BusinessException;
import com.test.poll.utils.AssertsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoteUserService {

    @Autowired
    private VoteUserRepository voteUserRepository;

    @Autowired
    private AssertsUtils assertsUtils;

    @Autowired
    private AnswerRepository answerRepository;

    public VoteUser save(VoteUser voteUser) throws BusinessException {
        this.assertsUtils.isNotNull(voteUser, "voteuser cannot be null!.");

        String idUser = voteUser.getIdUser();

        Optional<Answer> answer = this.answerRepository.findById(voteUser.getIdAnswer());

        if (!answer.isPresent()) {
            this.assertsUtils.isNotNull(null, "an error occurred while saving the vote.");
        }

        String idPoll = answer.get().getIdPoll();
        Boolean userHasVoted = this.checkVoteUserForPoll(idPoll, idUser);
        voteUser.setIdPoll(idPoll);

        if (userHasVoted) {
            throw new BusinessException("cannot vote more than once for the same poll.");
        }

        answer.get().setNumberVotes(answer.get().getNumberVotes() + 1);
        this.answerRepository.save(answer.get());

        this.voteUserRepository.save(voteUser);
        this.assertsUtils.isNotNull(voteUser, "an error occurred while saving the vote.");

        return voteUser;
    }

    private Boolean checkVoteUserForPoll(String idPoll, String idUser) {
        this.assertsUtils.isNotNull(idPoll, "idPoll cannot be null!.");
        this.assertsUtils.isNotNull(idUser, "voteuser cannot be null!.");
        VoteUser voteUser = this.voteUserRepository.findByIdUserAndIdPoll(idUser, idPoll);
        return voteUser != null;
    }
}
