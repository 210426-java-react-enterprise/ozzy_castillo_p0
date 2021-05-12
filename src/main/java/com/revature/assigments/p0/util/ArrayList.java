package com.revature.assigments.p0.util;

/**
 * A basic implementation of a ArrayList
 * @param <T>
 */
public class ArrayList<T> implements List<T> {

    private final int defaultArraySize = 9;
    private int arraySize = defaultArraySize;
    private int size=0;
    private Iterator[] iteArray = new Iterator[defaultArraySize];

    //Method to add iterator the array
    @Override
    public void add(T data) throws IllegalArgumentException {

        if (data == null) {
            throw new IllegalArgumentException("This Array List does not accept null values");
        } else {
            if (size == 0) {
                iteArray[size] = new Iterator(data);
                //System.out.println("Testing Add Method>> " + iteArray[size].data);
                size++;
            } else{
                for (int i = 0; i < defaultArraySize; i++) {
                    if(iteArray[i] == null){
                        iteArray[i] = new Iterator(data);
                       // System.out.println("Testing Add Method >> " + i);
                        iteArray[i].data = data;
                        size++;
                        break;
                    }

                }

            }
        }
    }


        //Method to get the correspondent iterator from the Array with index passed
        @Override
        public T get ( int index) {
            T dataToReturn;
            if (index > size){
                throw new IllegalArgumentException("The provided index would be out bounds" + size);
            }else{
                if(size == 0){
                    dataToReturn = null;
                }else{
                    dataToReturn = (T) iteArray[index].data;
                }

                return dataToReturn;
            }

        }

        public int indexOf(T data){
            for (int i = 0; i < size; i++) {
                if(iteArray[i].data.equals(data)) return i;
            }
            return -1;
        }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public boolean constains (T data){
            return false;
        }


    //Method to get the size of the Array
    @Override
    public int size () {
        int evalSize = 0;

        while( iteArray[evalSize]!= null){
            if(evalSize ==0)evalSize=1;

                evalSize++;
            }

            /*
            for (int i = 0; i < iteArray.length ; i++) {
                System.out.println(i);
                if(iteArray[i].data != null){
                    eval_size++;
                }
            }
            */
            //System.out.println("Testing Size Method >> " + evalSize);
            return evalSize;
        }
    @Override
    public void sort (T data){

        }

        /*
        private Iterator[] grow(Iterator[] IteArray){
            int newSize = arraySize*2;
            Iterator[] newIteArray = new Iterator[newSize];
            newIteArray.

        }
        */

        //Inner class to describe the data structure iterator
        private static class Iterator<T>{

            T data;

            Iterator(T data) {
                this.data = data;
            }

        }

    }