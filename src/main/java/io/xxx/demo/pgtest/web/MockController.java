package io.xxx.demo.pgtest.web;

import io.xxx.demo.pgtest.service.MockService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/mock")
public class MockController {

    private final MockService mockService;

    public MockController(MockService mockService) {
        this.mockService = mockService;
    }

    @RequestMapping("/insert/batch")
    public void insertBatch(@RequestParam(required = false, defaultValue = "1000000") Integer count) {
        Flux.range(1, count)
                .parallel()
                .runOn(Schedulers.parallel())
                .doOnComplete(() -> System.out.println("已完成"))
                .subscribe(integer -> mockService.saveOrder());
    }
}
