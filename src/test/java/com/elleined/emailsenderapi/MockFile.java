package com.elleined.emailsenderapi;

import org.springframework.mock.web.MockMultipartFile;

public interface MockFile {

    static MockMultipartFile get() {
        return new MockMultipartFile("attachment", "attachment.txt", "text/plain", "attachment".getBytes());
    }
}
