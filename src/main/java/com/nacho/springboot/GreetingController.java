package com.nacho.springboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private Map<Long, Greeting> greetingList;

    public GreetingController() {
        this.greetingList = new HashMap<>();
    }

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        Long id =counter.incrementAndGet();
        Greeting g = new Greeting(id, String.format(template, name));
        this.greetingList.put(id, g);

        return g;
    }

    @RequestMapping(method = RequestMethod.GET, value="/greeting")
    public Greeting greeting(@RequestParam("id") Long id) {
        return greetingList.get(id);
    }

    @RequestMapping(method = RequestMethod.GET, value="/allGreeting")
    public List<Greeting> greeting() {

        List<Greeting> list = (List<Greeting>) this.greetingList.values();

        return list;
    }

}
