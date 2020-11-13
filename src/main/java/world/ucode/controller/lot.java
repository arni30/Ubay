package world.ucode.controller;

import org.springframework.web.multipart.MultipartFile;

public class lot {
    private MultipartFile file;
    private String name;

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getFile() {
        return file;
    }

    public String getName() {
        return name;
    }
}
