package cn.yulam.mockjito;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExampleServiceTest {

    @Test
    void hello() {
        HttpService httpService = mock(HttpService.class);
        when(httpService.queryStatus()).thenReturn(1);
        Assert.assertEquals(1, httpService.queryStatus());

        ExampleService exampleService = new ExampleService();
        exampleService.setHttpService(httpService);
        Assert.assertEquals(1, exampleService.hello());
    }
}