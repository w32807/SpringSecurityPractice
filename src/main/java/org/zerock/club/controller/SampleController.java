package org.zerock.club.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample/")
@Log4j2
public class SampleController {

    // 로그인을 하지 않아도 접근 가능
    @GetMapping("/all")
    public void exAll(){
        log.info("exAll");
    }

    // 로그인 한 사람만 접근가능
    @GetMapping("/member")
    public void exMember(){
        log.info("exMember");
    }

    // 관리자 로그인 한 사용자만 접근가능
    @GetMapping("/admin")
    public void exAdmin(){
        log.info("exAdmin");
    }
}
