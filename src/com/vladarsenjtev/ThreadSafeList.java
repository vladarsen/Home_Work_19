package com.vladarsenjtev;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class ThreadSafeList {
    private final List<Callable<Integer>> callables = new ArrayList<>();
    private final PetrolStation petrolStation = new PetrolStation();


    public void add(int value) {
        callables.add(callables(value));
    }

    private Callable<Integer> callables(int value) throws RuntimeException {
        return new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                if (value < petrolStation.getAmount()) {
                    petrolStation.doRefuel(value);
                    return value;
                }
                throw new RuntimeException("Нет топлива");
            }
        };
    }

    public List<Callable<Integer>> getCallables() {
        return callables;
    }

    public double getPetrolStation() {
        return petrolStation.getAmount();
    }
}
