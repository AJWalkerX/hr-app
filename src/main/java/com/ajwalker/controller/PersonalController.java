package com.ajwalker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ajwalker.constant.RestApis.*;

@RestController
@RequestMapping(PERSONAL)
@RequiredArgsConstructor
@CrossOrigin("*")
public class PersonalController {
}
