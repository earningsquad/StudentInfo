package com.dev.core.pageModel;

import lombok.Data;

import java.util.Date;

@Data
public class basicPage {
    private int page = 1;
    private int limit = 10;
    private Date startTime;
    private Date endTime;
}
