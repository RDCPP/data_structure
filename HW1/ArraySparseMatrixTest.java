
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ArraySparseMatrixTest {

    @Test
    public void transpose() {
        double input[][] = {
            {0,     0, 1.0,   0},
            {1.0, 2.0,   0,   0},
            {0,     0,   0, 3.0}
        };

        double output[][] = {
            {  0, 1.0,   0},
            {  0, 2.0,   0},
            {1.0,   0,   0},
            {  0,   0, 3.0}
        };

        SparseMatrix given =
            ArraySparseMatrix.create(input, 3, 4,4);

        SparseMatrix expected =
            ArraySparseMatrix.create(output, 4, 3, 4);

        assertTrue("Transpose failed", expected.equals(given.transpose()));
    }

    @Test
    public void add() {
        double m1[][] = {
            {0,     0, 1.0,   0},
            {1.0, 2.0,   0,   0},
            {0,     0,   0, 3.0}
        };

        double m2[][] = {
            {1.0,   0,   0, 2.0},
            {  0, 3.0,   0,   0},
            {4.0,   0, 5.0,   0}
        };

        double output[][] = {
            {1.0,   0, 1.0, 2.0},
            {1.0, 5.0,   0,   0},
            {4.0,   0, 5.0, 3.0}
        };

        SparseMatrix sm1 = ArraySparseMatrix.create(m1, 3, 4, 4);
        SparseMatrix sm2 = ArraySparseMatrix.create(m2, 3, 4, 5);
        SparseMatrix sm3 = sm1.add(sm2);

        SparseMatrix expected = ArraySparseMatrix.create(output, 3,4, 8);

        assertTrue("Addition failed", expected.equals(sm3));
    }
}