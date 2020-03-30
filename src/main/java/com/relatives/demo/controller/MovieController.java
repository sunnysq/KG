package com.relatives.demo.controller;


import com.relatives.demo.dao.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 */
@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

//
//    @GetMapping("/all")
//    public Iterable<Movie> findAll() {
//        return movieRepository.findAll();
//    }
//
//    @GetMapping("/findByReveueGT")
//    public Iterable<Movie> findByRevenueGT(@RequestParam Integer revenue) {
//        return movieRepository.findByRevenueGreaterThan(revenue);
//    }

}