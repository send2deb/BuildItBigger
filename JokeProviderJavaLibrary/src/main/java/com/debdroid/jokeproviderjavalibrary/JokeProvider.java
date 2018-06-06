package com.debdroid.jokeproviderjavalibrary;

import java.util.ArrayList;
import java.util.Random;

public class JokeProvider {

    private static ArrayList<String> jokes = new ArrayList<>();

    static {
        jokes.add("How do you make holy water? You boil the hell out of it");
        jokes.add("Why is six afraid of seven? Because seven ate nine");
        jokes.add("What did the 0 say to 8? Nice belt!");
        jokes.add("What do you call a cow wiht two legs? Lean beef");
        jokes.add("The past, present and future walk into a bar. It was tense.");
        jokes.add("I never make mistake..I did once; but I was wrong!");
        jokes.add("Why did the orange stop? Because, it ran outta juice");
        jokes.add("Can a kangaroo jump higher than a house? Of course, a house doesn’t jump at all.");
        jokes.add("Patient: Oh doctor, I’m just so nervous. This is my first operation. Doctor: Don't worry. Mine too.");
        jokes.add("How do you catch a tame rabbit? The 'tame' way");
    }

    public JokeProvider() {}

    public String getJoke() {
        System.out.println("Hello from JokeProvider");

        // Ger a random number using Java Random
        Random random = new Random();
        // Set the bound to size of the "jokes - 1" to generate number range '0' to 'jokes -1'
        int jokeNumber = random.nextInt(jokes.size() - 1);

        // Return the randomly selected joke
        return jokes.get(jokeNumber);
//            return "";
    }
}
