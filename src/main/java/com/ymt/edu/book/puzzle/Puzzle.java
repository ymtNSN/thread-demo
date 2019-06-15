package com.ymt.edu.book.puzzle;

import java.util.Set;

/**
 * @Description: "搬箱子"的抽象接口
 * @Author: yangmingtian
 * @Date: 2019/6/8
 */
public interface Puzzle<P, M> {
    P initialPosition();

    boolean isGoal(P position);

    Set<M> legalMoves(P position);

    P move(P position, M move);
}
