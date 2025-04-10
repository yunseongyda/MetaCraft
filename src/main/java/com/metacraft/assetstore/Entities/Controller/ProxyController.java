package com.metacraft.assetstore.Entities.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ProxyController {

    @GetMapping("/proxy")
    public ResponseEntity<byte[]> proxy(@RequestParam("url") String url) {
        RestTemplate restTemplate = new RestTemplate();
        byte[] data = restTemplate.getForObject(url, byte[].class);
        return ResponseEntity.ok().body(data);
    }
}