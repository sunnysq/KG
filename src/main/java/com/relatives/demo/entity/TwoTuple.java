package com.relatives.demo.entity;

public class TwoTuple<RelativeRlation,RelativesRelation> {
    public final RelativeRlation first;
    public final RelativesRelation second;
    public TwoTuple(RelativeRlation a, RelativesRelation b) {
        first = a;
        second = b;
    }
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

}
