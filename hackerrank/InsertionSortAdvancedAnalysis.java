package com.ttech.training.hackerrank;

import java.util.Scanner;

public class InsertionSortAdvancedAnalysis {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int t = in.nextInt();

        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int[] ar = new int[n];
            for (int j = 0; j < n; j++) {
                ar[j] = in.nextInt();
                // System.err.println(ar[j]);
            }
            long c = insertSort(ar);
            System.out.println(c);
        }
        in.close();
    }

    public static long insertSort(int[] ar) {
        long swapCount = 0;
        long rightLeftBalance = 0;
        // Compute the required count
        Item top = new Item(null, ar[0]);
        for (int i = 1; i < ar.length; i++) {
            Item item = new Item(top, ar[i]);
            Item parent = top;
            int compare = 0; // -1: lower 0: equals 1: greater
            while (true) {
                if (parent == null) {
                    if (compare == -1) {
                        if (rightLeftBalance < 0) {
                            top = rotateRight(item, top);
                        } else {
                            item.getParent().setLeft(item);
                        }
                    } else if (compare == 1) {
                        if (rightLeftBalance < 0) {
                            item.getParent().setRight(item);
                        } else {
                            top = rotateLeft(item, top);
                        }
                    } else {
                        item.getParent().incrementSameCount();
                    }
                    break;
                } else if (item.getValue() < parent.getValue()) {
                    swapCount += parent.getRightCount() + parent.getSameCount();
                    item.setParent(parent);
                    parent = parent.getLeft();
                    compare = -1;
                    rightLeftBalance--;
                } else if (item.getValue() > parent.getValue()) {
                    parent.incrementRightCount();
                    item.setParent(parent);
                    parent = parent.getRight();
                    compare = 1;
                    rightLeftBalance++;
                } else if (item.getValue() == parent.getValue()) {
                    swapCount += parent.getRightCount();
                    item.setParent(parent);
                    parent = null;
                    compare = 0;
                } else {
                    break;
                }

            }
        }
        return swapCount;

    }

    //returns top
    static Item rotateRight(Item item, Item top) {
        Item parentsParent = item.getParent().getParent();
        Item parent = item.getParent();
        if (parentsParent != null) {
            if (item.getValue() < parentsParent.getValue()) {                
                parentsParent.setLeft(item);
            } else {                
                parentsParent.setRight(item);
            }
        } else {
             top = item; 
        }
        item.setRightCount(parent.getRightCount() + parent.getSameCount());
        item.setParent(parentsParent);
        parent.setParent(item);
        item.setRight(parent);
        return top;
    }
    
    //returns top
    static Item rotateLeft(Item item, Item top) {
        Item parentsParent = item.getParent().getParent();
        Item parent = item.getParent();
        if (parentsParent != null) {
            if (item.getValue() < parentsParent.getValue()) {
                parentsParent.setLeft(item);
            } else {                
                parentsParent.setRight(item);
            }
        } else {
             top = item; 
        }
        item.setParent(parentsParent);
        parent.setRightCount(0);
        parent.setParent(item);
        item.setLeft(parent);
        return top;
    }

    static class Item {
        private Item parent;
        private Item right;
        private Item left;
        private int value;
        private long rightCount = 0;
        private long sameCount = 1;

        public Item(Item parent, int value) {
            super();
            this.parent = parent;
            this.value = value;
        }

        public Item getParent() {
            return parent;
        }

        public void setParent(Item parent) {
            this.parent = parent;
        }

        public Item getRight() {
            return right;
        }

        public void setRight(Item right) {
            this.right = right;
        }

        public Item getLeft() {
            return left;
        }

        public void setLeft(Item left) {
            this.left = left;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public void setRightCount(long rightCount) {
            this.rightCount = rightCount;
        }

        public void incrementRightCount() {
            rightCount++;
        }

        public long getRightCount() {
            return rightCount;
        }

        public long getSameCount() {
            return sameCount;
        }

        public void incrementSameCount() {
            sameCount++;
        }

    }

}
