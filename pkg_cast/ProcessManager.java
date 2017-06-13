package pkg_cast;
import java.util.Date;
import java.util.TreeMap;
import java.util.Iterator;

/**
 *@author kobayashi
 *javadoc の使用例のための簡単バージョン
 * 2017.6.2
 */ 
public class ProcessManager{
	private Key key;
	private TreeMap<Integer,Interval> planMap;
	private TreeMap<Integer,Date> startMap;
	private TreeMap<Integer,Date> endMap;

	private int w_p_id;

/**
* デフォルトコンストラクタ
* 工程の予定、開始、終了を予定の順序と日付の前後関係を検査しながら記録します。
*/
	public ProcessManager(){
		planMap=new TreeMap<>() ;
		startMap=new TreeMap<>() ;
		endMap=new TreeMap<>() ;
	}
/*
	public void setDate(Date date){
		this.w_date=date;
	}
*/
/**
*工程番号をセットする
*@param id 工程番号
*/
	public void setProcessId(int id){
		this.w_p_id=id;
	}
/**
*Key(製品番号＋受注日）をセットする
*@param key (製品番号＋受注日）
*/
	public void setKey(Key key){
		this.key=key;
	}
/**
*工程の予定（開始日＋終了日）をセットする
* @param interval 工程の予定
* @throws Exception 追加する計画インスタンスの開始日と終了日の検査、および、追加する計画の開始日が、すでに登録されている計画の最後の終了日よりも若ければエラー
*/
	public void setPlan(Interval interval) throws Exception{
		if(!interval.getEndDate().before(interval.getStartDate())){
			if(this.planMap.size()==0){
				this.planMap.put(this.w_p_id,interval);	
			}else{
				Interval last=this.planMap.get(this.planMap.lastKey());
				if(!interval.getStartDate().before(last.getEndDate())){
					this.planMap.put(this.w_p_id,interval);	
				}else{
					throw new Exception("startDate before last_endDate");
				}
			}
		}else{
			throw new Exception("endDate before startDate");
		}
	}
/**
 *
* 新たに開始する工程が開始MapのN番目であるとして計画MapのN番目の工程番号と新たに追加する工程番号と等しいか
*/
	private boolean isValidId(int p_id){
		int s_idx=this.startMap.size();
		int p_idx=0;
		Iterator<Integer> it=this.planMap.keySet().iterator();
		while(it.hasNext() && p_idx<s_idx){
			int key=it.next();
			p_idx++;
		}
		return it.next()==p_id;
	}
/**
*工程の開始日をセットする
* @param date 開始日
* @throws Exception 別紙「開始」シーケンスを参照
*/
	public void setStart(Date date) throws StartDateException{

		if(this.startMap.size() == 0 && this.planMap.firstKey()==this.w_p_id
			|| this.startMap.size()>0 && this.startMap.size()==this.endMap.size()
			&& isValidId(this.w_p_id) && !date.before(this.endMap.get(this.endMap.lastKey()))){
				this.startMap.put(this.w_p_id,date);

		}else{//this.planMap.firstKey()!=this.w_p_id)
			throw new StartDateException();

		}
	}

/**
*工程の終了日をセットする
* @param date 終了日
* @throws  Exception 別紙「終了」シーケンスを参照
*/
	public void setEnd(Date date)throws Exception{
		try{
			if(this.endMap.size() == 0 && this.startMap.firstKey()==this.w_p_id &&  !date.before(this.startMap.get( this.startMap.firstKey()))||
				this.endMap.size() > 0 && this.startMap.size() == this.endMap.size() +1 && !date.before(this.startMap.get( this.startMap.firstKey()))){
				this.endMap.put(this.w_p_id,date);
			}else{
				throw new Exception("put end eoor");
			}
		}catch(Exception e){
			throw new Exception("put end eoor by system cause");
		}
			
	}
/**
*工程番号を返す
*@return id 工程番号
*/
	public int getProcessId(){
		return this.w_p_id;
	}
/**
*Key（製品番号＋受注日）を返す
* @return 製品番号＋受注日を
*/
	public Key getKey(){
		return this.key;
	}
/**
*工程プランを返す
* @return 工程プラン(工程番号＝＞予定（開始日＋終了日）
*/
	public TreeMap<Integer,Interval> getPlanMap(){
		return this.planMap;
	}
/**
*開始済の工程を返す
*@return 工程(工程番号＝＞開始日）
*/
	public TreeMap<Integer,Date> getStartMap(){
		return this.startMap;
	}
/**
*終了済の工程を返す
*@return 工程(工程番号＝＞終了日）
*/
	public TreeMap<Integer,Date> getEndMap(){
		return this.endMap;
	}
/**
*すべての工程が終了した否かを返す
*@return すべての工程が終了していれば　true でなければ　falseを返す
*/
	public boolean isComplete(){
		return this.planMap.size()==this.startMap.size() && this.startMap.size()== this.endMap.size();
	}

/**
*工程プランをダンプする（テスト用）
*/
	public void printPlanMap(){
		Iterator<Integer> it=this.planMap.keySet().iterator();
		while(it.hasNext()){
			Integer pid=it.next();
			System.out.printf("plan:%3d %tF %tF\n",pid,planMap.get(pid).getStartDate(),planMap.get(pid).getEndDate());
		}
	}
/**
*開始された工程をダンプする（テスト用）
*/
	public void printStartMap(){
		Iterator<Integer> it=this.startMap.keySet().iterator();
		while(it.hasNext()){
			Integer pid=it.next();
			System.out.printf("start:%3d %tF\n",pid,startMap.get(pid));
		}
	}
/**
*終了した工程をダンプする（テスト用）
*/
	public void printEndMap(){
		Iterator<Integer> it=this.endMap.keySet().iterator();
		while(it.hasNext()){
			Integer pid=it.next();
			System.out.printf("end:%3d %tF\n",pid,endMap.get(pid));
		}
	}
}
