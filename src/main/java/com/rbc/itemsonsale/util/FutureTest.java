package com.rbc.itemsonsale.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class FutureTest {
    public static List<Integer> test(Integer i) throws InterruptedException {
        TimeUnit.SECONDS.sleep(i);
        List<Integer> res = new ArrayList<>();
        res.add(i);
        res.add(i + 1);
        res.add(i + 2);
        System.out.println(res.toString());
        return res;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<List<Integer>> f1 = CompletableFuture.supplyAsync(()-> {
            try {
                return test(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return new ArrayList<Integer>();
            }
        });
        CompletableFuture<List<Integer>> f2 = CompletableFuture.supplyAsync(()-> {
            try {
                return test(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        });
        CompletableFuture<List<Integer>> f3 = CompletableFuture.supplyAsync(()-> {
            try {
                return test(8);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        });


        System.out.println(f3.get().toString());
        System.out.println(f2.get().toString());
        System.out.println(f1.get().toString());
    }
}
