package com.example.auto;

//Strong Dependency
//public class FlyingConfiguration {
//    public String makeConfiguration() {
//    return "AirCar";
//    }
//}

//Interface Mode
public class FlyingConfiguration implements Configurable {
    @Override
    public String makeConfiguration() {
        return "AirCar";
    }
}