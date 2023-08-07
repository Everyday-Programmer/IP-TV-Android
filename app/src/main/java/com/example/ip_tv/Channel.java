package com.example.ip_tv;

public class Channel {
    String id, name, alt_names, network, owners, country, city, broadcast_area, languages, categories, launched, closed, replaced_by, website, logo;
    boolean is_nsfw;

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlt_names(String alt_names) {
        this.alt_names = alt_names;
    }

    public void setBroadcast_area(String broadcast_area) {
        this.broadcast_area = broadcast_area;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIs_nsfw(boolean is_nsfw) {
        this.is_nsfw = is_nsfw;
    }

    public void setLaunched(String launched) {
        this.launched = launched;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public void setOwners(String owners) {
        this.owners = owners;
    }

    public void setReplaced_by(String replaced_by) {
        this.replaced_by = replaced_by;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLanguages() {
        return languages;
    }

    public String getName() {
        return name;
    }

    public String getAlt_names() {
        return alt_names;
    }

    public String getBroadcast_area() {
        return broadcast_area;
    }

    public String getCategories() {
        return categories;
    }

    public String getCity() {
        return city;
    }

    public String getClosed() {
        return closed;
    }

    public String getCountry() {
        return country;
    }

    public String getId() {
        return id;
    }

    public String getLaunched() {
        return launched;
    }

    public String getLogo() {
        return logo;
    }

    public String getNetwork() {
        return network;
    }

    public String getOwners() {
        return owners;
    }

    public String getReplaced_by() {
        return replaced_by;
    }

    public String getWebsite() {
        return website;
    }

    public boolean isIs_nsfw() {
        return is_nsfw;
    }
}