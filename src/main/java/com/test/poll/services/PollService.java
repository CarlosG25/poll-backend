package com.test.poll.services;

import com.test.poll.dao.PollRepository;
import com.test.poll.domains.Answer;
import com.test.poll.domains.Poll;
import com.test.poll.domains.dto.PollDto;
import com.test.poll.domains.enums.Status;
import com.test.poll.exceptions.BusinessException;
import com.test.poll.utils.AssertsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private AssertsUtils assertsUtils;

    @Autowired
    private AnswerService answerService;

    public PollDto save(PollDto pollDto) {
        this.assertsUtils.isNotNull(pollDto, "pollDto cannot be null");

        String name = pollDto.getNamePoll();
        String description =  pollDto.getDescriptionPoll();

        this.assertsUtils.isNotNullOrEmpty(name, "name poll");
        this.assertsUtils.isNotNullOrEmpty(description, "description poll");

        Poll poll = new Poll();
        poll.setName(pollDto.getNamePoll());
        poll.setIdUser(pollDto.getIdUser());
        poll.setDescription(pollDto.getDescriptionPoll());
        poll.setStatus(Status.ACTIVE.name());

        this.pollRepository.save(poll);
        this.assertsUtils.isNotNull(poll, "an error occurred while saving the poll.");
        this.answerService.saveAnswers(pollDto.getAnswers(), poll.get_id());

        return pollDto;
    }

    public List<Poll> findAll() {
        List<Poll> polls = this.pollRepository.findByStatusOrderByCreatedAtDesc(Status.ACTIVE.name());
        this.populateAnswers(polls);
        return polls;
    }

    private void populateAnswers(List<Poll> polls) {
        polls.forEach(poll->{
            try {
                List<Answer> answerList = this.answerService.findAndswerByIdPoll(poll.get_id());
                poll.setAnswers(answerList);
            } catch (BusinessException e) {
                e.printStackTrace();
            }
        });
    }

    public List<Poll> findAllByIdUser(String idUser) {
        this.assertsUtils.isNotNullOrEmpty(idUser, "idUser");
        List<Poll> polls = this.pollRepository.findAllByIdUser(idUser);
        this.populateAnswers(polls);
        return  polls;
    }

    public Poll findById(String idPoll) {
        this.assertsUtils.isNotNullOrEmpty(idPoll, "idPoll");
        return this.findById(idPoll);
    }
}
