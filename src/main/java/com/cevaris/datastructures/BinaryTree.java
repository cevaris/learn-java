package com.cevaris.datastructures;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class BinaryTree<A extends Comparable<A>> implements Collection<A> {

  public final static int PRE_ORDER = -1;
  public final static int IN_ORDER = 0;
  public final static int POST_ORDER = 1;
  public final static int BREADTH_FIRST_ORDER = 2;

  private final int traversalOrder;

  private TreeNode<A> root;

  public BinaryTree(int traversalOrder, TreeNode<A> root) {
    this.traversalOrder = traversalOrder;
    this.root = root;
  }

  public BinaryTree(TreeNode<A> root) {
    this.traversalOrder = PRE_ORDER;
    this.root = root;
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public boolean contains(Object o) {
    return false;
  }

  @Override
  public Iterator<A> iterator() {
    return toList(traversalOrder).iterator();
  }

  @Override
  public void forEach(Consumer<? super A> action) {

  }

  @Override
  public Object[] toArray() {
    return toList(traversalOrder).toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return toList(traversalOrder).toArray(a);
  }

  @Override
  public boolean add(A a) {
    addNode(root, a);
    return true;
  }

  private void addNode(TreeNode<A> node, A value) {
    if (node == null) {
      node = new TreeNode<>(value);
      return;
    }

    if (value.compareTo(node.data) < 0) {
      addNode(node.left, value);
    } else {
      addNode(node.right, value);
    }
  }

  @Override
  public boolean remove(Object o) {
    return false;
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return false;
  }

  @Override
  public boolean addAll(Collection<? extends A> c) {
    return false;
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return false;
  }

  @Override
  public boolean removeIf(Predicate<? super A> filter) {
    return false;
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return false;
  }

  @Override
  public void clear() {
    this.root = null;
  }

  @Override
  public Spliterator<A> spliterator() {
    return null;
  }

  @Override
  public Stream<A> stream() {
    return null;
  }

  @Override
  public Stream<A> parallelStream() {
    return null;
  }

  public String topView() {
    Deque<A> ls = new ArrayDeque<>();
    ls.add(root.data);

    traverseTop(root.left, ls, true);
    traverseTop(root.right, ls, false);

    StringBuilder builder = new StringBuilder();
    ls.forEach(x -> builder.append(String.format("%s ", x)));
    return builder.toString();
  }

  private void traverseTop(TreeNode<A> node, Deque<A> ls, boolean traverseLeft) {
    if (node == null) {
      return;
    }

    if (traverseLeft) {
      ls.addFirst(node.data);
      traverseTop(node.left, ls, traverseLeft);
    } else {
      ls.addLast(node.data);
      traverseTop(node.right, ls, traverseLeft);
    }
  }

  public int height() {
    return findHeight(root) - 1;
  }

  private int findHeight(TreeNode<A> node) {
    if (node == null) {
      return 0;
    } else {
      return 1 + Math.max(findHeight(node.left), findHeight(node.right));
    }
  }

  private List<A> postOrder(TreeNode<A> node, List<A> ls) {
    if (node == null) {
      return ls;
    }

    postOrder(node.left, ls);
    postOrder(node.right, ls);
    ls.add(node.data);
    return ls;
  }

  private void breadthFirstSearch(List<A> ls, Deque<TreeNode<A>> queue) {
    if (queue.isEmpty()) {
      return;
    }

    TreeNode<A> curr = queue.poll();
    ls.add(curr.data);

    if (curr.left != null) {
      queue.add(curr.left);
    }

    if (curr.right != null) {
      queue.add(curr.right);
    }

    breadthFirstSearch(ls, queue);
  }

  private List<A> preOrder(TreeNode<A> node, List<A> ls) {
    if (node == null) {
      return ls;
    }

    ls.add(node.data);
    preOrder(node.left, ls);
    preOrder(node.right, ls);
    return ls;
  }

  private List<A> inOrder(TreeNode<A> node, List<A> ls) {
    if (node == null) {
      return ls;
    }

    inOrder(node.left, ls);
    ls.add(node.data);
    inOrder(node.right, ls);
    return ls;
  }

  private List<A> toList(int order) {
    List<A> ls = new ArrayList<>();
    switch (order) {
      case PRE_ORDER:
        ls = preOrder(root, ls);
        break;
      case IN_ORDER:
        ls = inOrder(root, ls);
        break;
      case POST_ORDER:
        ls = postOrder(root, ls);
        break;
      case BREADTH_FIRST_ORDER:
        Deque<TreeNode<A>> queue = new ArrayDeque<>();
        queue.add(root);
        breadthFirstSearch(ls, queue);
        break;
      default:
        throw new IllegalArgumentException("Invalid traversal order:" + order);
    }
    return ls;
  }

}
