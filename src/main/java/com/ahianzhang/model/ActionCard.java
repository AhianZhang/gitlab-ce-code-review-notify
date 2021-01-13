package com.ahianzhang.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 1/13/21 10:39 AM
 */
public class ActionCard  {
    private final StringBuilder stringBuilder = new StringBuilder();


    private final String title = "CR 通知";
    private String text;
    private String btnOrientation = "0";
    private String singleTitle = "快速查看";
    private String singleURL;



 public ActionCard appendRow(String content){
  text = stringBuilder.append("# ").append(content).append("\n").toString();
  return this;
}
public ActionCard singleURL(String url){
     singleURL = url;
     return this;
}


public String toJson() throws JsonProcessingException {
     ActionCard actionCard = this;
     Map<String, Object> map = new HashMap<>();
     map.put("actionCard",actionCard);
     map.put("msgtype","actionCard");
     ObjectMapper oj = new ObjectMapper();
    return oj.writeValueAsString(map);
}

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getBtnOrientation() {
        return btnOrientation;
    }

    public String getSingleTitle() {
        return singleTitle;
    }

    public String getSingleURL() {
        return singleURL;
    }


}
