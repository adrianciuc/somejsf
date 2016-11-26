package com.tj.tema5.dto

import org.apache.commons.collections4.IteratorUtils

import javax.swing.tree.MutableTreeNode
import javax.swing.tree.TreeNode


class Node implements MutableTreeNode, Serializable  {
    String name
    String type
    Node parent
    List<Node> childes

    TreeNode getChildAt(int i) {
        if (childes == null)
            return null
        return childes.get(i)
    }

    private boolean childrenSet() {
        return childes != null && childes.size() > 0
    }

    int getChildCount() {
        if (childrenSet())
            return childes.size()
        else
            return 0
    }

    TreeNode getParent() {
        return parent
    }

    int getIndex(TreeNode treeNode) {
        return childes.indexOf(treeNode)
    }

    boolean getAllowsChildren() {
        return childes != null
    }

    boolean isLeaf() {
        return getChildCount() == 0
    }

    Enumeration children() {
        if (childes == null)
            return IteratorUtils.asEnumeration(IteratorUtils.emptyIterator())
        return IteratorUtils.asEnumeration(childes.iterator())
    }

    void insert(MutableTreeNode mutableTreeNode, int i) {
        mutableTreeNode.setParent(this)
        childes.add(i, (Node)mutableTreeNode)
    }

    void remove(int i) {
        childes.remove(i)
    }

    void remove(MutableTreeNode mutableTreeNode) {
        childes.remove(mutableTreeNode)
    }

    void setUserObject(Object o) {
        throw new UnsupportedOperationException()
    }

    void removeFromParent() {
        if (parent != null)
            parent.remove(this)
    }

    void setParent(MutableTreeNode mutableTreeNode) {
        parent = (Node) mutableTreeNode
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Node node = (Node) o

        if (name != node.name) return false
        if (type != node.type) return false

        return true
    }

    int hashCode() {
        int result
        result = (name != null ? name.hashCode() : 0)
        result = 31 * result + (type != null ? type.hashCode() : 0)
        return result
    }

    void setChildes(Collection<Node> nodes) {
        this.childes = nodes.each {
            it.setParent(this)
        }
    }
}
