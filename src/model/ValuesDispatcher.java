package model;

import controller.Controller;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ValuesDispatcher {
    private Controller controller;
    private List<FunctionData> data;
    private ReentrantLock lock;

}
