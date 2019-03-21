package com.wrapsody.demo.wrapsody.controller;

import com.wrapsody.demo.wrapsody.*;
import com.wrapsody.demo.wrapsody.dto.RequestWrapsodyDocumentDto;
import com.wrapsody.demo.wrapsody.dto.ResponseWrapsodyDocumentDto;
import com.wrapsody.demo.wrapsody.exception.WrapsodyNotFoundException;
import com.wrapsody.demo.wrapsody.exception.WrapsodyUnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sun.awt.SunHints;

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
    public ResponseWrapsodyDocumentDto getSyncInfo(@RequestParam (value = "syncId") String syncId)
            throws UnsupportedEncodingException,
            WrapsodyNotFoundException {
        RequestWrapsodyDocument requestWrapsodyDocument = new RequestWrapsodyDocument(syncId);
        return requestWrapsodyDocument.getDocument();
    }

    @PostMapping("/wrapsody/document")
    public void applyWrapsody(@RequestBody RequestWrapsodyDocumentDto requestDto) {
        RequestWrapsodySetTag requestWrapsodySetTag = new RequestWrapsodySetTag(requestDto.getSyncId(), requestDto.getTagsAsString());
        RequestWrapsodySetAuth requestWrapsodySetAuth = new RequestWrapsodySetAuth(requestDto.getSyncId(),
                requestDto.getMasterIdAsXml(),
                requestDto.getCheckoutUserIdsAsXml(),
                requestDto.getCheckoutDeptCodesAsXml(),
                requestDto.getViewAuthAllUsers(),
                requestDto.getViewUserIdsAsXml(),
                requestDto.getViewDeptCodes());


        try {
            requestWrapsodySetTag.addTags();
            requestWrapsodySetAuth.addAuths();
        } catch (WrapsodyNotFoundException notFoundEx) {
            notFoundEx.printStackTrace();
        } catch (WrapsodyUnauthorizedException UnAuthEx) {
            UnAuthEx.printStackTrace();
        }
    }
}
