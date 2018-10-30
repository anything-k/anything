package com.anything.boot.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @Author:FanMingxin
 * @Date: 2018/10/10 14:52
 */
@Service
public class DemoService {

    @Autowired
    private ActionService actionService;

    public void mono(){
        Mono<String> emptyMono = Mono.empty();
        Mono<String> staticMono = Mono.just("e4developer");

        staticMono.subscribeOn(Schedulers.parallel()).subscribe(actionService::action);

        Mono.fromCallable(this::getStr).subscribeOn(Schedulers.parallel());

        List<String> list = getList();
        Mono.just(list).repeat().parallel(5)
                .runOn(Schedulers.parallel())
                .doOnNext(li->System.out.println("this is size:"+li.size()))
        .subscribe(li->li.forEach(s -> System.out.println(s +"  " +Thread.currentThread().getName())));
    }

    private String getStr(){
        System.out.println("tt");
        System.out.println("tt  "+Thread.currentThread().getName());
        return "mono-test";
    }

    private List<String> getList(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        return list;
    }

    public void flux(){
        Flux<Integer> emptyFlex = Flux.empty();
        Flux<String> staticFlex = Flux.just("e4developer",
                "reactive",
                "reactor");

        System.out.println(Thread.currentThread().getName());
        staticFlex.publishOn(Schedulers.newParallel("tt")).subscribe(str->{
            System.out.println(str);
            System.out.println(Thread.currentThread().getName());
        });
        System.out.println("end");

        Flux<Integer> numbersOneToTen = Flux.range(1, 10);
        numbersOneToTen.parallel(5)
                .runOn(Schedulers.parallel())
                .subscribe(a->System.out.println(a +"  " +Thread.currentThread().getName()));

        Mono<Boolean> all = numbersOneToTen.all(a -> a == 5);
        Mono<Boolean> any = numbersOneToTen.any(a -> a == 5);
        String as = numbersOneToTen.as(a -> {
            StringBuilder sb = new StringBuilder();
            a.subscribe(num->sb.append(num));
            return sb.toString();
        });
        System.out.println(numbersOneToTen.blockFirst());
    }
}
