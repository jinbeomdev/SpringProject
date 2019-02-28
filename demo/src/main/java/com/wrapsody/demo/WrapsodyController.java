package com.wrapsody.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class WrapsodyController {

    @GetMapping("/wrapsody/tagtree")
    public String getTagTree(@RequestParam (value = "userId") String userId) throws IOException {
        RequestWrapsodyTagTree requestWrapsodyTagTree = new RequestWrapsodyTagTree(userId);
        return requestWrapsodyTagTree.getTagTree();
    }

    @GetMapping("/wrapsody/organ")
    public String getOrgan(@RequestParam (value = "userId") String userId) {
        RequestWrapsodyOrgan requestWrapsodyOrgan = new RequestWrapsodyOrgan(userId);
        return requestWrapsodyOrgan.getOrgan();
    }
}
