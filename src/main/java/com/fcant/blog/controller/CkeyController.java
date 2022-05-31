package com.fcant.blog.controller;

import com.fcant.blog.service.CkeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CkeyController
 * <p>
 * encoding:UTF-8
 *
 * @author Fcant 下午 22:37 2022/5/30/0030
 */
@RestController
@RequestMapping("/ckey")
public class CkeyController {

    @Autowired
    CkeyService ckeyService;

    @GetMapping("/by/{userId}")
    public ResponseEntity<String> getCkeyByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(ckeyService.getCkeyByUser(userId));
    }
}
