package com.wsy.webseed.controller;

import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wsy.webseed.service.SysMenuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wsy.webseed.controller.pojo.tool.BootstrapTreeviewPojo;
import com.wsy.webseed.dao.entity.SysMenu;
import com.wsy.webseed.service.SysUserService;
import com.wsy.webseed.util.Pagination;

@Controller
@RequestMapping(value = "/menu")
public class SysMenuController {
    @Autowired
    SysUserService userService;

    @Autowired
    SysMenuService menuService;

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ResponseEntity<String> queryAuthMenu() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("text", "plain", Charset.forName("utf-8")));
        String loginId = "administrator";
        List<BootstrapTreeviewPojo> menus = menuService.queryBootstrapTreeviewPojos(loginId);
        // final SerializerFeature[] serializerFeatures = { SerializerFeature.WriteNullStringAsEmpty,
        // SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue};
        final SerializerFeature[] serializerFeatures = {};
        String returnJson = JSON.toJSONStringWithDateFormat(menus, "yyyy-MM-dd HH:mm:ss", serializerFeatures);

        return new ResponseEntity<String>(returnJson, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/testDataTable", method = RequestMethod.GET)
    public ResponseEntity<String> allMenus(HttpServletRequest request) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("text", "plain", Charset.forName("utf-8")));
        String params = request.getParameter("dataTableParamsJson");
        String returnJson;
        if (StringUtils.isNotBlank(params)) {
            @SuppressWarnings("unchecked")
            Pagination<SysMenu> pagination = JSON.parseObject(params, Pagination.class);
            returnJson = menuService.queryNotTreeMenus(pagination);
        } else {
            returnJson = menuService.queryNotTreeMenus(null);
        }

        return new ResponseEntity<String>(returnJson, headers, HttpStatus.OK);
    }
}
