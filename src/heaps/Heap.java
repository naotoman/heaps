package heaps;

public interface Heap {

	int size();

	int value(int key);

	int findMin();

	int deleteMin();

	void decreaseValue(int key, int x);

}
