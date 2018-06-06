package com.udacity.gradle.builditbigger.backend;

import com.debdroid.jokeproviderjavalibrary.JokeProvider;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /**
     * An endpoint method to retrieve a joke and sends it back to caller
     */
    @ApiMethod(name = "getJoke")
    public MyBean getJoke() {
        // Create a JokeProvider instance of Java library
        JokeProvider jokeProvider = new JokeProvider();
        // Ger a random joke
        String joke = jokeProvider.getJoke();

        // Set the response for caller
        MyBean response = new MyBean();
        response.setData(joke);

        return response;
    }
}
