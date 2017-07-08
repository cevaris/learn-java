package com.cevaris.datastructures;

import org.junit.Assert;
import org.junit.Test;

import static com.cevaris.datastructures.TreeNode.treeNode;

public class BinaryTreeTest {

  private TreeNode<Integer> root =
      treeNode(1, null, treeNode(2, null, treeNode(5, treeNode(3, null, treeNode(4, null, null)), treeNode(6, null, null))));

  @Test
  public void testInOrder() {
    BinaryTree<Integer> tree = new BinaryTree<>(BinaryTree.IN_ORDER, root);
    Object[] actual = tree.toArray();
    Object[] expected = {1, 2, 3, 4, 5, 6};
    Assert.assertArrayEquals(actual, expected);
  }

  @Test
  public void testPreOrder() {
    BinaryTree<Integer> tree = new BinaryTree<>(BinaryTree.PRE_ORDER, root);
    Object[] actual = tree.toArray();
    Object[] expected = {1, 2, 5, 3, 4, 6};
    Assert.assertArrayEquals(actual, expected);
  }

  @Test
  public void testPostOrder() {
    BinaryTree<Integer> tree = new BinaryTree<>(BinaryTree.POST_ORDER, root);
    Object[] actual = tree.toArray();
    Object[] expected = {4, 3, 6, 5, 2, 1};
    Assert.assertArrayEquals(actual, expected);
  }

  @Test
  public void testHeight() {
    BinaryTree<Integer> tree = new BinaryTree<>(root);
    Assert.assertEquals(tree.height(), 4);
  }

  @Test
  public void testTopView() {
    BinaryTree<Integer> tree = new BinaryTree<>(root);
    Assert.assertEquals("1 2 5 6", tree.topView().trim());
  }

  @Test
  public void testBreadthFirstSearch() {
    BinaryTree<Integer> tree = new BinaryTree<>(BinaryTree.BREADTH_FIRST_ORDER, root);
    Object[] actual = tree.toArray();
    Object[] expected = {1, 2, 5, 3, 6, 4};
    Assert.assertArrayEquals(actual, expected);
  }

}