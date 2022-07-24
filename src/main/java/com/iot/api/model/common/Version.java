package com.iot.api.model.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Document
public class Version{
    private int versionCode=1;
    private String versionName = "1.0";
    private String remarks = "Init Version";
    private Date timestamp = new Date();
}


