package com.Voting.mgmt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotingService {

    @Autowired
    private CandidateRepository repository;

    public void  addCandidate(String name) {
    
        if (!repository.findByName(name).isPresent()) {
            repository.save(new Candidate(name, 0));
        
        }
		
    }

    public int castVote(String name) {
        java.util.Optional<Candidate> candidate = repository.findByName(name);
        if (candidate.isPresent()) {
            Candidate c = candidate.get();
            c.setVoteCount(c.getVoteCount() + 1);
            repository.save(c);
            return c.getVoteCount();
        } else {
            return -1;
        }
    }

    public int getVoteCount(String name) {
        java.util.Optional<Candidate> candidate = repository.findByName(name);
        if (candidate.isPresent()) {
            return candidate.get().getVoteCount();
        } else {
            return -1;
        }
    }

    public List<Candidate> getAllCandidates() {
        return repository.findAll();
    }

    public String getWinner() {
        List<Candidate> candidates = repository.findAll();
        Candidate winner = candidates.get(0);
        for (Candidate candidate : candidates) {
            if (candidate.getVoteCount() > winner.getVoteCount()) {
                winner = candidate;
            }
        }
        return winner.getName();
    }
}

