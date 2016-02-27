package com.wsy.webseed.controller;

import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.wsy.webseed.dao.entity.SysUser;
import com.wsy.webseed.service.SysUserService;
import com.wsy.webseed.util.Pagination;

@Controller
@RequestMapping(value = "/user")
public class SysUserController {
    @Autowired
    SysUserService userService;

    private Logger LOGGER = LoggerFactory.getLogger(SysUserController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<String> query(HttpServletRequest request) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("text", "plain", Charset.forName("utf-8")));
        String params = request.getParameter("dataTableParamsJson");
        String returnJson;
        if (StringUtils.isNotBlank(params)) {
            @SuppressWarnings("unchecked")
            Pagination<SysUser> pagination = JSON.parseObject(params, Pagination.class);
            returnJson = userService.queryTableJson(pagination);
        } else {
            returnJson = userService.queryTableJson(null);
        }

        return new ResponseEntity<String>(returnJson, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> queryById(@PathVariable Integer id) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("text", "plain", Charset.forName("utf-8")));

        SysUser user = userService.query(id);
        LOGGER.info("{} query", id);
        String returnJson = JSON.toJSONString(user, user.getSimplePropertyPreFilter());

        return new ResponseEntity<String>(returnJson, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<String> save(HttpServletRequest request, @RequestBody
    final String json) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("rext", "plain", Charset.forName("utf-8")));
        String result = "";
        SysUser user = JSON.parseObject(json, SysUser.class);
        userService.saveUser(user);
        return new ResponseEntity<String>(result, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> update(HttpServletRequest request) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("rext", "plain", Charset.forName("utf-8")));
        String result = "";
        return new ResponseEntity<String>(result, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> del(HttpServletRequest request) {
        return null;
    }

}
