package com.sbs.basic1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    private int cnt;
    public HomeController(){
        cnt = -1;
    }
    @GetMapping("/home/main")
    @ResponseBody
    public String showHome(){
        return "안녕하세요";
    }

    @GetMapping("/home/main2")
    @ResponseBody
    public String showHome2(){
        return "환영합니다!";
    }
    @GetMapping("/home/plus")
    @ResponseBody
    public int showPlus(@RequestParam(defaultValue = "0") int a,@RequestParam(defaultValue = "0") int b){
        return a+b;
    }
}
