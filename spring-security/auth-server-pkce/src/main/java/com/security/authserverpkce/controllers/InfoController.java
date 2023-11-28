package com.security.authserverpkce.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class InfoController {
  public static final Logger log = LoggerFactory.getLogger(InfoController.class);

  @GetMapping
  public ResponseEntity<Map<String, Object>> info() {
    Map<String, Object> response = new HashMap<>();
    response.put("estado", true);
    response.put("mensaje", "La aplicación esta desplegada.");
    return ResponseEntity.ok(response);
  }
}
