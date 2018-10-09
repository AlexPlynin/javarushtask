package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
//метод лучше рекурсии как по мне
        File rootDir = new File(root);
        List<String> result = new ArrayList<>();
        Queue<File> fileTree = new PriorityQueue<>();

        Collections.addAll(fileTree, rootDir.listFiles());

        while (!fileTree.isEmpty()) {
            File currentFile = fileTree.remove();
            if (currentFile.isDirectory()) {
                Collections.addAll(fileTree, currentFile.listFiles());
            } else {
                result.add(currentFile.getAbsolutePath());
            }
        }


        return result;

*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {

    Entry<String> root;
    //
    //
    //Entry<String> moveRoot;
    Queue<Entry<String>> queue = new LinkedList<>();
    Queue<Entry<String>> constQueue = new LinkedList<>();

    int countOfElem;

    ArrayList<Entry<String>> list = new ArrayList<>();

    public CustomTree() {
        this.root = new Entry<>("Корень");
        countOfElem++;
        this.root.lineNumber = 0;
        //moveRoot = root;
        list.add(root);
    }

    @Override
    public boolean remove(Object o) {


        if (o instanceof String) {

            for (Entry<String> elem : constQueue) {
                if (elem.elementName.equals(o)){
                    elem.parent=null;
                    elem.leftChild=null;
                    elem.rightChild=null;



            }

            }



        } else throw new UnsupportedOperationException();


        return true;
    }

    @Override
    public int size() {
        return countOfElem - 1;
    }

    @Override
    public String get(int index) {

        throw new UnsupportedOperationException();
        //return null;
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();

    }

    @Override
    public boolean add(String s) {
//        moveRoot.checkChildren();
//        if (moveRoot.isAvailableToAddChildren()) {
//            if (moveRoot.availableToAddLeftChildren) {
//                moveRoot.leftChild = new Entry<>(s);
//                moveRoot.leftChild.lineNumber = moveRoot.lineNumber++;
//                moveRoot.leftChild.parent = moveRoot;
//            }
//            if (moveRoot.availableToAddRightChildren) {
//                moveRoot.rightChild = new Entry<>(s);
//                moveRoot.rightChild.lineNumber = moveRoot.lineNumber++;
//                moveRoot.rightChild.parent = moveRoot;
//                //root = root.leftChild;
//                // add()
//            }
////если заняты 2 реенка
//
//        } else {
//
//            //тут должно быть условие о заполнение ширины
//            if (moveRoot.lineNumber!=0){
//
//
//
//            }
//            else {
//
//
//                moveRoot = moveRoot.leftChild;
//                add(s);
//            }
//
//        }
//
//
        // Выполним обход в Ширину!***************************************

        //queue.add()
        root.checkChildren();
        //do {

        if (root.isAvailableToAddChildren()) {
            if (root.availableToAddLeftChildren) {
                root.leftChild = new Entry<>(s);
                root.leftChild.checkChildren();
                //root.checkChildren();
                // root.leftChild.lineNumber = root.lineNumber++;
                root.leftChild.parent = root;
                queue.add(root.leftChild);
                constQueue.add(root.leftChild);
                countOfElem++;

            } else if (root.availableToAddRightChildren) {
                root.rightChild = new Entry<>(s);
                root.rightChild.checkChildren();
                root.checkChildren();
                // root.rightChild.lineNumber = root.lineNumber++;
                root.rightChild.parent = root;
                queue.add(root.rightChild);
                constQueue.add(root.rightChild);
                countOfElem++;
            }
            root.checkChildren();
        }
        if (!queue.isEmpty()) {
            if (!(root.availableToAddRightChildren || root.availableToAddLeftChildren)) {
                root = queue.remove();
                return true;
            }
            //else  return true;

        }
        ;//NOT RIGHT
        // }
        //while (!queue.isEmpty());

        return true;
    }

    public String getParent(String s) throws NullPointerException {
//тут можно применить обход в ширину для поиска целевого узла
        for (Entry<String> elem : constQueue) {
            if (elem.elementName.equals(s))
                return elem.parent.elementName;

        }


        return null;

    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();

    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();

    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();

    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();

    }

    static class Entry<T> implements Serializable {
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;

            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;

        }

        void checkChildren() {
            if (leftChild != null) availableToAddLeftChildren = false;
            if (rightChild != null) availableToAddRightChildren = false;
        }

        boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }

    }
}
