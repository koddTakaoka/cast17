package pkg_cast;
import java.util.Date;

/**
 * @author kobayashi
 * javadoc の使用例のための簡単バージョン
 * 2017.6.2
 */ 
public class Interval{
	private Date startDate;
	private Date endDate;
/**
* デフォルトコンストラクタ
* jsp用のダミーです。
*/
public Interval(){
	this.startDate=null;
	this.endDate=null;
}
	
/**
* コンストラクタ
* 受注時に、工程の計画を記録するための開始予定日と終了予定日のペアです。
* @param start 開始日
* @param end 終了日
* @throws Exception 開始日が終了日よりも後であればエラー
*/
	public Interval(Date start,Date end)throws Exception{
		//if(!end.before(start)){
		if(start.compareTo(end)<=0){//!end.before(start)){
			this.startDate=start;
			this.endDate=end;
		}else{
			throw new Exception("date order error");
		}
	}
/**
* 開始日を返す
* @return 開始日
*/
	public Date getStartDate(){
		return this.startDate;
	}
/**
* 終了日を返す
* @return 終了日
*/
	public Date getEndDate(){
		return this.endDate;
	}
/**
* 開始日をセットする
* @param date 開始日
* @throws Exception 終了日がnullでないとき、開始日が終了日よりも後であればエラー
*/
	public void setStartDate(Date date)throws Exception{
		if(endDate == null || endDate !=null && !endDate.before(date)){
			this.startDate=date;
		}else{
			throw new Exception("date order error");
		}
	}
/**
* 終了日をセットする
* @param date 終了日
* @throws Exception 開始日がnull、あるいは、開始日が終了日よりも後であればエラー
*/
	public void setEndDate(Date date)throws Exception{
		if(startDate !=null && !date.before(startDate)){
			this.endDate=date;
		}else{
			throw new Exception("date order error");
		}
	}
	
}
