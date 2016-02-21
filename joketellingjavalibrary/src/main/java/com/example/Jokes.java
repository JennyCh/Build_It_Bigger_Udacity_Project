package com.example;

/*
    Created by Jenny Eckstein on 1/24/2016
    This library provides a jokes for the Build It Bigger Udacity Project
 */

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jokes {

    private static List<String> jokes;

    private BufferedReader bufferedReader;

    public Jokes() {
        this.jokes = new ArrayList<>();

        this.jokes.add("Q: Why did the chicken cross the road? A: To get to the other side!");
        this.jokes.add("Q: What did the pig say at the beach on a hot summer's day? A: \"I'm bakin'.\"");
        this.jokes.add("Q: Why did the chicken cross the road? A: He felt like it!");
        this.jokes.add("Q: What do you call an alligator in a vest? A: An Investigator");
        this.jokes.add("Q: Why can't you give Elsa a balloon? A: Because she will Let it go.");
    }

    public static String getRandomJoke(){
        Random random = new Random();
        return jokes.get(random.nextInt(jokes.size()));
    }

}


