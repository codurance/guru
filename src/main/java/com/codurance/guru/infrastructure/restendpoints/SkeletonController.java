package com.codurance.guru.infrastructure.restendpoints;

import com.codurance.guru.craftspeople.Craftsperson;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SkeletonController {

    @RequestMapping(path = "/api/tdd/{data}", method= RequestMethod.GET)
    public Response getData(@PathVariable("data") String data) {
        return new Response(data);
    }

    class Response {
        private String data;
        public Response(String data) {
            this.data = data;
        }
        public String getData() {
            return data;
        }
    }

    @RequestMapping(path = "/restApiTest", method= RequestMethod.GET)
    public List<Craftsperson> getCraftPeopleForReactTest() {
        List<Craftsperson> data = new ArrayList<>();
        data.add(new Craftsperson("Riccardo","Toni"));
        data.add(new Craftsperson("Jose", "Campos"));
        return data;
    }
}
