package com.epam.esm.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Helper {
    private final CreateGCPlusTag createGCPlusTag;

    public Helper(CreateGCPlusTag createGCPlusTag) {
        this.createGCPlusTag = createGCPlusTag;
    }


    public void getCreateGCPlusTag() {

        try {
            createGCPlusTag.createTag();
            createGCPlusTag.createGC();
            createGCPlusTag.createUser();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
