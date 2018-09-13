
public class ArraySparseMatrix implements SparseMatrix {

    public static final int DEFAULT_CAPACITY = 1024;

    private int rowCount;
    private int columnCount;
    private int elemCount;
    private Entry[] elements = new Entry[0];

    public ArraySparseMatrix(int rowCount, int columnCount, int capacity) {
        elements = new Entry[capacity];
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.elemCount = 0;
    }

    public ArraySparseMatrix(int rowCount, int columnCount) {
        this(rowCount, columnCount, DEFAULT_CAPACITY);
    }
    /*
     * This routine simulates reading from files using two-dimensional matrix.
     */
    public static SparseMatrix create(double[][] aMatrix, int rowCount, int columnCount, int elemCount) {
        ArraySparseMatrix matrix = new ArraySparseMatrix(rowCount, columnCount, elemCount);

        int nonZeroCount = 0;
        for (int i = 0; i < aMatrix.length; i++)
            for (int j = 0; j < aMatrix[i].length; j++) {
                if (Double.compare(aMatrix[i][j], 0.0) != 0) {
                    matrix.put(new Entry(i, j, aMatrix[i][j]));
                    nonZeroCount++;
                }
            }

        if (nonZeroCount != elemCount)
            throw new IllegalStateException("Non zero count doesn't match");

        return matrix;
    }

    @Override
    public SparseMatrix transpose() {

        double[][] transposed = new double[this.columnCount][this.rowCount];

        for(int i = 0; i<this.elemCount ; i++){
            transposed[this.elements[i].col][this.elements[i].row] =
            this.elements[i].value;
        }

        SparseMatrix tmatrix = ArraySparseMatrix.create(transposed, this.columnCount, this.rowCount, this.elemCount);

        return tmatrix;
    }

    @Override
    public SparseMatrix add(SparseMatrix other) {
        if (this.rowCount != other.getRowCount() || this.columnCount != other.getColumnCount())
            throw new IllegalArgumentException("Matrix size doesn't match");

        ArraySparseMatrix oth = (ArraySparseMatrix) other;

        int addednonzero = 0;
        
        double[][] added = new double[this.rowCount][this.columnCount];

        for(int i = 0 ; i<this.elemCount ; i++){
            added[this.elements[i].row][this.elements[i].col] = this.elements[i].value;
            addednonzero++;
        }

        for(int i = 0 ; i<oth.elemCount ; i++){
            if(Double.compare(added[oth.elements[i].row][oth.elements[i].col], 0.0) == 0){
                addednonzero++;
            }
            added[oth.elements[i].row][oth.elements[i].col] =
            Double.sum(added[oth.elements[i].row][oth.elements[i].col], oth.elements[i].value);
        }

        SparseMatrix addmatrix = ArraySparseMatrix.create(added, this.rowCount, this.columnCount, addednonzero);

        return addmatrix;
    }


    public void put(Entry entry) {
        elements[elemCount++] = entry;
    }

    @Override
    public SparseMatrix multiply(SparseMatrix other) {
        throw new IllegalStateException("Not implemented");
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public int getElemCount() {
        return elemCount;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ArraySparseMatrix)) return false;
        ArraySparseMatrix other = (ArraySparseMatrix) obj;

        if (rowCount != other.rowCount || columnCount != other.columnCount || elemCount != other.elemCount)
            return false;

        for (int i = 0; i < elements.length; i++) {
            if (!elements[i].equals(other.elements[i])) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(rowCount + "\t" + columnCount + "\t" + elemCount + "\n");
        for (int i = 0; i < elemCount; i ++)
            builder.append(elements[i] + "\n");

        return builder.toString();
    }
}
