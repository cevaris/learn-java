package com.cevaris.datastructures;


public class TreeNode<A> {
  final A data;
  TreeNode<A> left;
  TreeNode<A> right;

  public TreeNode(A data) {
    this.data = data;
  }

  public static <E> TreeNode<E> treeNode(E data, TreeNode<E> left, TreeNode<E> right) {
    TreeNode<E> node = new TreeNode<>(data);
    node.left = left;
    node.right = right;
    return node;
  }
}
