
public interface SparseMatrix {
    SparseMatrix transpose();
    SparseMatrix add(SparseMatrix other);

    // You don't have to implement multiply.
    SparseMatrix multiply(SparseMatrix other);

    int getRowCount();
    int getColumnCount();
    int getElemCount();
}
