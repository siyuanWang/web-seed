package com.wsy.webseed.controller;

import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

import com.wsy.webseed.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/role")
public class SysRoleController {
    @Autowired
    SysRoleService roleService;
    
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String add() {
       
        return "/index/idx";
    }
    
    @RequestMapping(value="/save",method = RequestMethod.POST)
    public ResponseEntity<String> save(HttpServletRequest request){
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("rext", "plain", Charset.forName("utf-8")));
        String result = "";
        
        return new ResponseEntity<String>(result, headers, HttpStatus.OK);
    }
    
    @RequestMapping(value="/edit/{id}",method = RequestMethod.GET)
    public String edit(Model model,HttpServletRequest request){
       
       return null;   
        
    }
    
    @RequestMapping(value="/edit/{id}",method = RequestMethod.POST)
    public ResponseEntity<String> update(HttpServletRequest request){
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("rext", "plain", Charset.forName("utf-8")));
        String result = "";
        return new ResponseEntity<String>(result, headers, HttpStatus.OK);
    }
    
    @RequestMapping(value="/del/{id}",method = RequestMethod.POST)
    public String del(HttpServletRequest request){
        return null;
        
    }
    
    @RequestMapping(value="/assign/{id}",method = RequestMethod.GET)
    public String assignMenu(){
        return null;
        
    }
    
    public ResponseEntity<String> assignMenu(HttpServletRequest request){
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("rext", "plain", Charset.forName("utf-8")));
        String result = "";
        return new ResponseEntity<String>(result, headers, HttpStatus.OK);
    }
}
