import java.util.ArrayList;
import java.util.Arrays;

public class SudokuCell {

	private int mValue;
	private ArrayList<Integer> mPossibleVals;
	
	public SudokuCell(int num){
		mValue = num;
		mPossibleVals = null;
	}
	
	public SudokuCell(){
		mValue = 0;
		mPossibleVals =  new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
	}
	
	public void setCellVal(int num){
		mValue = num;
		mPossibleVals = null;
	}
	
	public int getCellVal(){
		return mValue;
	}
	
	public boolean updateCellPossiblities(int dangerNum){
		if (mValue == dangerNum){
			return false;
		}
		
		if(mValue > 0 && null == mPossibleVals){
			return true;
		}
		
		if(mPossibleVals.contains(dangerNum)){
			if(mPossibleVals.size() > 1){
				mPossibleVals.remove((Object)dangerNum);
			}
			else return false;	
		}
		return true;
	}
	
	public int getPossibility(){
		if(null != mPossibleVals)
			return mPossibleVals.get(0);
		return -1;
	}
	
	public int getNumPossibilities(){
		if (null != mPossibleVals)
			return mPossibleVals.size();
		return 0;
	}
	
}
