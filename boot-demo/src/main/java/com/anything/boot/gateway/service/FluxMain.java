package com.anything.boot.gateway.service;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2018/10/30 10:50
 */
public class FluxMain {

    private static List<String> getList(){
        List<String> list = new ArrayList<>();
        list.add("a1");
        list.add("b2");
        list.add("c");
        list.add("d");
        list.add("e");
        return list;
    }

    public static void main(String[] args){
        api();
    }

    public static void api(){
        /*Flux<Integer> emptyFlex = Flux.empty();
        Flux<String> staticFlex = Flux.just("a","b","c");

        System.out.println(Thread.currentThread().getName());
        staticFlex.publishOn(Schedulers.newParallel("tt")).subscribe(str->{
            System.out.println(str);
            System.out.println(Thread.currentThread().getName());
        });
        System.out.println("end");*/

        Flux<Integer> numbersOneToTen = Flux.range(1, 10);
        numbersOneToTen.parallel(10)
                .runOn(Schedulers.parallel())
                .subscribe(FluxMain::pri);

        /*Mono<Boolean> all = numbersOneToTen.all(a -> a == 5);
        Mono<Boolean> any = numbersOneToTen.any(a -> a == 5);
        String as = numbersOneToTen.as(a -> {
            StringBuilder sb = new StringBuilder();
            a.subscribe(num->sb.append(num));
            return sb.toString();
        });
        System.out.println(System.currentTimeMillis());
        System.out.println(numbersOneToTen.blockFirst(Duration.ofSeconds(2)));
        System.out.println(System.currentTimeMillis());
        System.out.println(numbersOneToTen.blockFirst());*/
    }

    public static void pri(Integer a){
        /*try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        int i = 1;
        while(i < 1000){
            System.out.println(a +"  "+ i + " " +Thread.currentThread().getName());
            i ++ ;
        }
    }
}
