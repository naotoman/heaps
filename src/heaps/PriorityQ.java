package heaps;

/**
 * <p>ヒープに基づく優先度付きキューです。(key, val)のペアを要素としてもち、優先度はvalの小さい順です。
 * 各keyは互いに異なる必要があります。
 * <p>このキューの先頭は、最小のvalをもつ要素です。最小のvalをもつ要素が複数存在する場合は、先頭はこれらの要素の一つになります。
 * <p>コンストラクタ中で全要素からなるヒープを一度に構成することを想定しています。
 * そのため、要素の挿入操作はサポートしていません。
 */
public interface PriorityQ {

	/**
	 * このキュー中の要素の数を返します。
	 * @return このキューの要素数
	 */
	int size();

	/**
	 * 指定されたkeyをもつ要素のvalを返します。存在しないkeyを指定した場合の動作は未定義です。
	 * @param key 関連付けられたvalが返されるkey
	 * @return 指定されたkeyをもつ要素のval
	 */
	int value(int key);

	/**
	 * このキューの先頭の要素のkeyを返します。
	 * キューが空のときの動作は未定義です。
	 * @return このキューの先頭の要素のkey
	 */
	int findMin();

	/**
	 * このキューの先頭の要素を削除します。
	 * キューが空のときの動作は未定義です。
	 */
	void deleteMin();

	/**
	 * 指定されたkeyをもつ要素のvalを、指定された値{@code d}だけ減少させます。
	 * 存在しないkeyを指定した場合の動作は未定義です。
	 * @param key 減少させるvalに関連付けられたkey
	 * @param d 指定されたkeyをもつ要素のvalを減少させる値{@code (d>=0)}
	 */
	void decreaseValue(int key, int d);

}
