package pkg_cast;
import java.util.Date;

/**
 * @author kobayashi
 * javadoc の使用例のための簡単バージョン
 * 2017.6.2
 */ 
public class Key{
	private int id;
	private Date date;
	public Key(){
		this.id=0;
		this.date=null;
	}
/**
* コンストラクタ
* このパッケージ内で共通に使用する、製品番号と受注日のペアを作成します。
* @param id 製品番号
* @param date 受注日
*/
	public Key(int id,Date date){
		this.id=id;
		this.date=date;
	}
/**
* 工程番号を返す
* @return 製品番号
*/
	public int getId(){return id;}
/**
* 受注日を返す
* @return 受注日
*/
	public Date getDate(){return date;}
/**
* 製品番号をセットする
* @param id 製品番号
*/
	public void setId(int id){
		this.id=id;
	}
/**
* 受注日をセットする
* @param date 受注日
*/
	public void setDate(Date date){
		this.date=date;
	}
}
