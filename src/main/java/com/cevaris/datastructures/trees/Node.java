package com.cevaris.datastructures.trees;


public class Node<A> {
  final A data;
  Node<A> left;
  Node<A> right;

  public Node(A data) {
    this.data = data;
  }

  public static <E> Node<E> treeNode(E data, Node<E> left, Node<E> right) {
    Node<E> node = new Node<>(data);
    node.left = left;
    node.right = right;
    return node;
  }
}
