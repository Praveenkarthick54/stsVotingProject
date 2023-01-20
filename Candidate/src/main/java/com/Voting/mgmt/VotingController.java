package com.Voting.mgmt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Voting.mgmt.VotingService;

@RestController
public class VotingController {

    @Autowired
    private VotingService service;

    @PostMapping("/api/entercandidate")
    public void enterCandidate(@RequestParam("name") String name) {
        service.addCandidate(name);
    }

    @PutMapping("/api/castvote")
    public int castVote(@RequestParam("name") String name) {
        return service.castVote(name);
    }

    @GetMapping("/api/countvote")
    public int countVote(@RequestParam("name") String name) {
        return service.getVoteCount(name);
    }

    @GetMapping("/listvote")
    public List<Candidate> listVote() {
        return service.getAllCandidates();
    }

    @GetMapping("/api/getwinner")
    public String getWinner() {
       return service.getWinner();	
    }
    }
