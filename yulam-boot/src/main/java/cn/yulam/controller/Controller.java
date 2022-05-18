package cn.yulam.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 5yl
 * date: 2022/4/14
 */
@RestController
public class Controller {
    static class OOMObject {

    }

    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    @RequestMapping("/heapoom")
    public String heapoom(String id) {

        List<OOMObject> list = new ArrayList<>();

        while (id.equals("2")) {
            list.add(new OOMObject());
        }
        return "sss";
    }

    @RequestMapping("/redirect")
    @CrossOrigin
    public void redirect(HttpServletResponse response, String redirectUrl) throws IOException {
        response.sendRedirect(redirectUrl);
    }
}
