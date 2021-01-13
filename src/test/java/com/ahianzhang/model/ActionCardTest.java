package com.ahianzhang.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActionCardTest {
  @Test
  void should_genrate_jsonstr() throws JsonProcessingException {
    String json = new ActionCard().appendRow("1").appendRow("2").singleURL("http://sadsadsadsad.com").toJson();
    System.out.println(json);
  }
}