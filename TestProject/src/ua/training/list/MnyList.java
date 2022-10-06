package ua.training.list;

import java.util.Arrays;

class MnyItem {
	private int value;
	private MnyItem previous;
	private MnyItem next;
	
	public MnyItem(int value) {
		this.value = value;
	}

	public MnyItem getPrevious() {
		return previous;
	}

	public MnyItem getNext() {
		return next;
	}

	public void setPrevious(MnyItem previous) {
		this.previous = previous;
	}

	public void setNext(MnyItem next) {
		this.next = next;
	}
	
	public boolean hasNext() {
		return null == next;
	}
	
	public boolean hasPrevious() {
		return null == previous;
	}

	public int getValue() {
		return value;
	}
	
}

public class MnyList {
	MnyItem start;
	MnyItem end;
	int amount;
//	MnyItem[] array = new MnyItem[100];
	
	MnyList() {
		start = null;
		end = null;
		amount = 0;
	}

	public void add(int value) {
		MnyItem item = new MnyItem(value);
		amount++;
		
		if (null == start) {
			start = item;
			end = item;
		} else {
			item.setPrevious(end);
			end.setNext(item);
			end = item;
		}		
	}

	public boolean hasItem(int value) {
		if (null == start) {
			return false;
		}
		
		MnyItem item = start;
		while (null != item) {
			if (item.getValue() == value) {
				return true;
			}
			item = item.getNext();
		}
		return false;
	}

	public int get(int index) {
		if (null == start) {
			return -1;
		}
		
		MnyItem item = start;
		int current = 0;
		while ((null != item) && (current++ < index)) {
			item = item.getNext();
		}
		
		if ((current == index) && (null != item)) {
			return item.getValue();
		}
		return -1;
	}
	
	public String toString() {
		int array[] = getArrayElements();
		return Arrays.toString(array);		
	}

	public int[] getArrayElements() {
		int array[] = new int[amount];
		int index = 0;
		
		MnyItem item = start;
		while (null != item) {
			array[index++] = item.getValue();
			item = item.getNext();
		}
		
		return array;
	}
	
	public MnyItem getItemByIndex(int index) {
		MnyItem item = start;
		int current = 0;
		while ((null != item) && (current++ < index)) {
			item = item.getNext();
		}
		return item;
	}

	public void remove(int index) {
		MnyItem item = getItemByIndex(index);
		
		if (item.hasPrevious() && item.hasNext()) {
			MnyItem previous = item.getPrevious();
			MnyItem next = item.getNext();
			previous.setNext(next);
			next.setPrevious(previous);
			System.out.println("remove element in the middle of linked list");
		} else if (item.hasNext()) {
			start = item.getNext();
			start.setPrevious(null);
			System.out.println("remove element in the start of linked list");
		} else if (item.hasPrevious()) {
			end = item.getPrevious();
			end.setNext(null);
			System.out.println("remove element in the end of linked list");
		}
	}

}
