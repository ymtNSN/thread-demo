package com.ymt.edu.book.puzzle;

import java.util.LinkedList;
import java.util.List;

/**
 * @Description: 连表节点
 * @Author: yangmingtian
 * @Date: 2019/6/8
 */
public class Node<P, M> {
    final P pos;
    final M move;
    final Node<P, M> prev;

    public Node(P pos, M move, Node<P, M> prev) {
        this.pos = pos;
        this.move = move;
        this.prev = prev;
    }

    List<M> asMoveList() {
        List<M> solution = new LinkedList<>();
        for (Node<P, M> n = this; n.move != null; n = n.prev) {
            solution.add(0, n.move);
        }
        return solution;
    }
}
