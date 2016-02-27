package com.wsy.webseed.controller.pojo;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.wsy.webseed.util.PaginationRow;

public class OperationLog implements Serializable, PaginationRow {
    private static final long serialVersionUID = 1212248980731694885L;

    public OperationLog() {
    };

    public OperationLog(String operationTime, String description, String loginName, String functionId,
            String operationType) {
        this.operationTime = operationTime;
        this.description = description;
        this.loginName = loginName;
        this.functionId = functionId;
        this.operationType = operationType;
    }

    @JSONField(name = "operationTime", format = "yyyy-MM-dd HH:mm:ss")
    private String   operationTime;

    // log info
    private String description;

    private String loginName;

    private String functionId;

    // save,update,query,delete
    private String operationType;

    public String getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    @Override
    public SimplePropertyPreFilter getSimplePropertyPreFilter() {
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(OperationLog.class, "operationTime", "description",
                "loginName", "functionId", "operationType");
        return filter;
    }
}
