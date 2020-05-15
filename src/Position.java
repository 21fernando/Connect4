public class Position {

    private int row;
    private int col;

    public Position(int row, int col){
        this.row = row;
        this.col = col;
    }

    public void setRow(int row){
        this.row = row;
    }

    public void setCol(int col){
        this.col = col;
    }

    public int getRow(){
        return this.row;
    }

    public int getColumn(){
        return this.col;
    }

    public boolean equals(Object other){
        if(!(other instanceof Position)){
            return false;
        }
        Position temp = (Position)other;
        return(temp.row == this.row && temp.col == this.col);
    }

}
