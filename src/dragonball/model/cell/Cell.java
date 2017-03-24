package dragonball.model.cell;

import java.io.Serializable;



abstract public class Cell implements Serializable{
    private CellListener cellListener;
    
	abstract public String toString();
	abstract public void onStep() ;
	public CellListener getCellListener() {
		return cellListener;
	}
	public void setListener(CellListener cellListener) {
		this.cellListener = cellListener;
	}

}
