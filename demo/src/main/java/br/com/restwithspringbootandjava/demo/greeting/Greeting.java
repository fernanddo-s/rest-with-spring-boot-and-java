package br.com.restwithspringbootandjava.demo.greeting;

import lombok.Data;

@Data
public class Greeting {
    private final long id;

    private final String content;

}
