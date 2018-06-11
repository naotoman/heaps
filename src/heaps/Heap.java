package heaps;

public interface Heap {

	int size();

	void clear();

	int findMin();

	int deleteMin();

	void decreaseValue(int key, int x);

}
