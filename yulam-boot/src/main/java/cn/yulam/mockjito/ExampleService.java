package cn.yulam.mockjito;

public class ExampleService {

    private HttpService httpService;

    public void setHttpService(HttpService httpService) {
        this.httpService = httpService;
    }

    public String hello() {
        int status = httpService.queryStatus();
        if (status == 0) {
            return "你好";
        }
        else if (status == 1) {
            return "Hello";
        }
        else {
            return "未知状态";
        }
    }

}

