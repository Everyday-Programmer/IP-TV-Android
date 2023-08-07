package com.example.ip_tv;

public class Country {
    String name, code, languages, flag;

    public void setCode(String code) {
        this.code = code;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getFlag() {
        return flag;
    }

    public String getName() {
        return name;
    }

    public String getLanguages() {
        return languages;
    }
}