package com.example.studentsityapp.Adapters;

import java.util.Map;

public class PresentStudents {
    private Map<String,Integer> map;

    public PresentStudents() {
    }

    public PresentStudents(Map<String, Integer> map) {
        this.map = map;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }
}
