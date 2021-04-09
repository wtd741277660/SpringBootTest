package com.leetcode.test.daily;

import lombok.Data;

@Data
public class TopoNode {

    private boolean isBus;//是否是母线

    private boolean isInflectionPoint;//是否是拐点

    private int x;

    private int y;

    private int level;//所属层级
}
