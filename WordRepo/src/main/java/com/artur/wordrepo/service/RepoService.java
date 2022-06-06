package com.artur.wordrepo.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import com.artur.wordrepo.exception.RepoNotFoundException;
import com.artur.wordrepo.types.Repo;

@Service
public class RepoService {
    private ArrayList<Repo> repos;
    private int repoCount;
    public RepoService(){
        this.repos = new ArrayList<Repo>();
        repoCount = 1;
    }


    public ArrayList<Repo> getRepos(){
        return repos;
    }

    public void addRepo(Repo r){
        try{
            int index = findRepoIndex(r.getId());
            repos.get(index).setName(r.getName());
            repos.get(index).setWords(r.getWords());
        }catch (RepoNotFoundException e){
            repos.add(new Repo(repoCount, r.getName(), new ArrayList<String>()));
            repoCount++;
        }
    }

    public int findRepoIndex(int id) throws RepoNotFoundException {
        for(int i=0; i<repos.size(); i++){
            if(repos.get(i).getId() == id){
                return i;
            }
        }
        throw new RepoNotFoundException();
    }

    public void deleteRepo(int id) throws RepoNotFoundException{
        int repoIndex = findRepoIndex(id);
        repos.remove(repoIndex);
    }

    public Repo getRepo(int id) throws RepoNotFoundException{
        int repo = findRepoIndex(id);
        return repos.get(repo);
    }

    public void addWord(int id, String word) throws RepoNotFoundException{
        int index = findRepoIndex(id);
        repos.get(index).addWord(word);
    }

    public ArrayList<Repo> findReposByWord(String word) {
        ArrayList<Repo> foundRepos = new ArrayList<Repo>();
        for (int i=0; i<repos.size(); i++) {
            if (repos.get(i).getWords().contains(word))
                foundRepos.add(repos.get(i));
        }
        return foundRepos;
    }
}
