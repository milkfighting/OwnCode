package org.example.juc.link;

/**
 * Request
 *
 * @author 王泓桥
 * @date 2021.12.24
 */
public class Request {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                '}';
    }
}
