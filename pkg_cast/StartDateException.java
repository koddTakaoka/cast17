package pkg_cast;
/**
 * @author kobayashi
 * javadoc の使用例のための簡単バージョン
 * 2017.6.2
 * 開始日付の違反を報告する例外です。
 */ 
public class StartDateException extends Exception{
	public StartDateException(){
		super("start date order error");
	}
}
