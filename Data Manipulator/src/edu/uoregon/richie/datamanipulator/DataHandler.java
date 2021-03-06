package edu.uoregon.richie.datamanipulator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class DataHandler {
	
	// generate an set of random UUID Strings
	public static List<String> createRandomList(int size) {
		List<String> output = new ArrayList<String>(size);
		for (int i = 0; i < size; i++) {
			output.add(i, UUID.randomUUID().toString());
		}
		return output;
	}
	
	// perform a selection sort on the given array, returning a sorted copy
	public static List<String> getSelectionSortedCopy(List<String> input) {
		List<String> output = new ArrayList<String>(input);
		int smallest;
		for (int i = 0; i < output.size(); i++) {
			smallest = i;
			// find smallest element in unsorted half
			for (int j = i; j < output.size(); j++) {
				if (output.get(j).compareTo(output.get(smallest)) < 0) {
					smallest = j;
				}
			}
			// move smallest element to end of sorted half
			output.add(i, output.remove(smallest));
		}
		return output;
	}

	// perform a (recursive) merge sort on the given array, returning a sorted copy
	public static List<String> getMergeSortedCopy(List<String> input) {
		// base case
		if (input.size() <= 1)
			return input;
		// output array, sub-arrays
		List<String> output = new ArrayList<String>(input);
		int split = (int) Math.floor(input.size() / 2);
		List<String> sub1 = input.subList(0, split);
		List<String> sub2 = input.subList(split, input.size());
		// recursion
		sub1 = getMergeSortedCopy(sub1);
		sub2 = getMergeSortedCopy(sub2);

		int i = 0, j = 0;
		while (i < sub1.size() && j < sub2.size()) {
			// add smaller value to output
			if (sub1.get(i).compareTo(sub2.get(j)) <= 0)
				output.add(sub1.get(i++));
			else
				output.add(sub2.get(j++));
		}
		// add remaining values to output
		if (i >= sub1.size())
			output.addAll(sub2.subList(j, sub2.size()));
		if (j >= sub2.size())
			output.addAll(sub1.subList(i, sub1.size()));
		return output;
	}
}