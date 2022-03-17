package ztlClass;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Create By Intellij idea
 * Author:Macro
 * Date:2022/1/21
 * Time:20:28
 * Describe:
 */

public class Stack<T> {
    private class Node{
        T item;
        Node next;
        Node(T t){
            item = t;
            next = null;
        }
    }
    Node first;
    public Stack(){
        super();
        first=null;
    }
    public boolean isEmpty(){
        return first==null;
    }
    public void push(T t){
        if(isEmpty()) first = new Node(t);
        else {
            Node cur = new Node(t);
            cur.next = first;
            first = cur;
        }
    }
    public T pop(){
        if (isEmpty()) return null;
        Node n = first;
        first = first.next;
        return n.item;
    }
    public int size(){
        Node tmp = first;
        int count = 0;
        while(first!=null){
            count++;
            first = first.next;
        }
        return count;
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        Scanner scanner = new Scanner(System.in);
        String s = "";
        do{
            s = scanner.next();
            if(s.equals("-")) System.out.println(stack.pop());
            else stack.push(s);
        }while(!"EXIT".equals(s));
        while(!stack.isEmpty())
            System.out.print(stack.pop()+" ");

    }
}
