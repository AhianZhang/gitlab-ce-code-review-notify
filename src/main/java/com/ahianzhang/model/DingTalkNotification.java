package com.ahianzhang.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 1/12/21 2:34 PM
 */
public class DingTalkNotification {
  /**
   * 项目名称
  */
  private String projectName;

  /**
   * 内容
   */
  private String content;
  /**
   * 审批人
   */
  private String approvalMember;
  /**
   * 创建人
   */
  private String committer;
  /**
   * Code Request 地址
   */
  private String crUrl;


    public DingTalkNotification getCurrentMergeRequest(String json) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode = objectMapper.readTree(json);
    this.projectName = jsonNode.path("project").path("name").textValue();
    this.content = jsonNode.path("object_attributes").path("description").textValue().replaceAll("\"","'");
    this.approvalMember = jsonNode.path("assignee").path("name").textValue();
    this.committer = jsonNode.path("user").path("name").textValue();
    this.crUrl = jsonNode.path("object_attributes").path("url").textValue();
    return this;
  }

  public String getProjectName() {
    return projectName;
  }

  public String getContent() {
    return content;
  }

  public String getApprovalMember() {
    return approvalMember;
  }

  public String getCommitter() {
    return committer;
  }

  public String getCrUrl() {
    return crUrl;
  }
}
