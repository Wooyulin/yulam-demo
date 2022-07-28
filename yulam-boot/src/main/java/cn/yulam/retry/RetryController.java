package cn.yulam.retry;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableRetry
public class RetryController {

    @Autowired
    private RetryService retryService;
    @GetMapping("/retry")
    public String retry() throws Exception {
        retryService.call();
        return "OK";
    }
}
