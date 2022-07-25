package com.fcant.blog.controller;

import com.fcant.blog.amqp.producers.BusinessProducers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Business
 * <p>
 * encoding:UTF-8
 *
 * @author Fcant 下午 14:28 2022/7/25/0025
 */
@RestController
@RequestMapping("/amqp")
public class BusinessController {

    @Autowired
    BusinessProducers businessProducers;

    @GetMapping("{id}")
    public ResponseEntity<String> send(@PathVariable String id) {
        for (int i = 0; i < 100; i++) {
            businessProducers.sendMsg(String.valueOf(i));
        }
        return ResponseEntity.ok("成功");
    }

}
