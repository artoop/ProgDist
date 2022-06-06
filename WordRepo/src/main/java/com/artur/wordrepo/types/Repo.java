package com.artur.wordrepo.types;

import java.util.ArrayList;
import java.util.Objects;

public class Repo {
    private int id;
    private String name;
    private ArrayList<String> words;

    public Repo () {}
    
    public Repo(int id, String name, ArrayList<String>words){
        this.id = id;
        this.name = name;
        this.words = words;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public ArrayList<String> getWords(){
        return words;
    }

    public void setWords(ArrayList<String> words){
        this.words = words;
    }

    public void addWord(String w){
        words.add(w);
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void deleteWord(String w){
        for(int i=0; i<words.size(); i++){
            if(words.get(i).equals(w)){
                words.remove(i);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repo r = (Repo) o;
        return id == r.id &&
                Objects.equals(name, r.name) &&
                Objects.equals(words, r.words);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, words);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("City{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", words=").append(words);
        sb.append('}');
        return sb.toString();
    }
}
