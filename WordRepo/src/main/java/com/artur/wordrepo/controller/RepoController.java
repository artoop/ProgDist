package com.artur.wordrepo.controller;

import java.util.ArrayList;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.artur.wordrepo.exception.RepoNotFoundException;
import com.artur.wordrepo.service.RepoService;
import com.artur.wordrepo.types.Repo;

@CrossOrigin
@RestController
@RequestMapping("/wordrepo/repos")
public class RepoController {
    @Autowired
    private RepoService repoService;
    
    @GetMapping("")
    public ArrayList<Repo> getRepos(@RequestParam(required = false) String word) {
        if (word != null)
            return repoService.findReposByWord(word);
        else
            return repoService.getRepos();
    }
    
    @PostMapping("")
    public void addRepo(@RequestBody Repo repo){
        System.out.println(repo.getId());
        repoService.addRepo(repo);
    }
    
    @GetMapping("/{repo-id}")
    public Repo getRepo(@PathVariable("repo-id")int id){
        try{
            return repoService.getRepo(id);
        }catch(RepoNotFoundException e){
            return null;
        }
    }
    
    @GetMapping("/{repo-id}/words")
    public ArrayList<String> getWords(@PathVariable("repo-id")int id){
        try{
            return repoService.getRepo(id).getWords();
        }catch(RepoNotFoundException e){
            return null;
        }
    }
    
    @PostMapping("/{repo-id}/words")
    public void addWord(@PathVariable("repo-id")int id, @RequestBody String word){
        try{
            repoService.addWord(id, word);
        }catch(RepoNotFoundException e){
        }
    }
    
    @DeleteMapping("/{repo-id}")
    public void deleteRepo(@PathVariable("repo-id") int id){
        try{
            repoService.deleteRepo(id);
        }catch(RepoNotFoundException e){
        }
    } 

    @DeleteMapping("/{repo-id}/words")
    public void deleteWord(@PathVariable("repo-id")int id, @RequestBody String word){
        try{
            repoService.getRepos().get(id).deleteWord(word);
        }catch(RepoNotFoundException e){}
    }
}
