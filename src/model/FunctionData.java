package model;

import java.util.ArrayList;
import java.util.List;

public class FunctionData {
    private List<GraphicPoint> data;

    private void initData() {
        data = new ArrayList<>();
    }

    public FunctionData() {
        initData();
    }

    public List<GraphicPoint> getData() {
        return data;
    }
}
