package com.wrapsody.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
public class WrapsodyController {

    @GetMapping("/wrapsody/tagtree")
    public String getTagTree(@RequestParam (value = "userId") String userId,
                             @RequestParam (value = "uuid", required = false, defaultValue = "") String uuid){
        RequestWrapsodyTagTree requestWrapsodyTagTree = new RequestWrapsodyTagTree(uuid, userId);
        return requestWrapsodyTagTree.getTagTree();
    }

    @GetMapping("/wrapsody/organ")
    public String getOrgan(@RequestParam (value = "userId") String userId,
                           @RequestParam (value = "uuid", required = false, defaultValue = "") String uuid) {
        RequestWrapsodyOrgan requestWrapsodyOrgan = new RequestWrapsodyOrgan(uuid, userId);
        return requestWrapsodyOrgan.getOrgan();
    }

    @GetMapping("/wrapsody/document")
    public ResponseWrapsodyDocument getSyncInfo(@RequestParam (value = "syncId") String syncId)
            throws UnsupportedEncodingException,
            WrapsodyNotFoundException {
        RequestWrapsodyDocument requestWrapsodyDocument = new RequestWrapsodyDocument(syncId);
        return requestWrapsodyDocument.getDocument();
    }
}
