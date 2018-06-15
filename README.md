# heaps
いろいろなヒープの実装です。
src/heapsパッケージをダウンロードして使ってください。
## contents
src/heapsパッケージの中身について簡単に説明すると、
PriorityQ.javaがひな型となるインターフェースで、その他のファイルはすべてPriorityQ.javaを実装した個々のヒープになります。  
詳しくはJavadocをご覧ください。

## パフォーマンス
src/heapsパッケージの優先度付きキューの各実装のパフォーマンスについて述べます。  
比較実験をいくつかしてみましたが、平均的にD_Heap.javaが最も高速なようです。
PairingHeap.javaは、辺密度が大きいときはD_Heap.javaに匹敵するほど速いです。
FibonacciHeap.javaは常に遅かったです。
