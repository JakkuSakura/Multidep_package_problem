package top.jackhack.mutidep;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolverTest {

    @Test
    public void test1() {
        Solver solver = new Solver();
        solver.addItem(new Item(1, 1));
        solver.addItem(new Item(2, 2));
        solver.addItem(new Item(3, 3));
        solver.addItem(new Item(4, 10));
        solver.addItem(new Item(5, 5));
        solver.addItem(new Item(6, 6));

        solver.addDep(2, 1);
        solver.addDep(3, 1);
        solver.addDep(5, 1);
        solver.addDep(4, 2);
        solver.addDep(4, 3);
        solver.addDep(6, 2);
        assertEquals(16, solver.solve(10));

    }
    @Test
    public void test2() {
        Solver solver = new Solver();
        solver.addItem(new Item(10, 60));
        solver.addItem(new Item(20, 100));
        solver.addItem(new Item(30, 120));
        assertEquals(220, solver.solve(50));

    }
}