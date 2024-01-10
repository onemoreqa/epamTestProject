package com.example.auto;

//Strong Dependency
//public class ExperimentalConfiguration {
//    public String makeConfiguration() {
//    return "StrangeCar";
//    }
//}

//Interface Mode
public class ExperimentalConfiguration implements Configurable {
    @Override
    public String makeConfiguration() {
        return "StrangeCar";
    }
}