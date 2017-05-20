package Table;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import Values.AbstractValue;

public class BinarySearchTree {
	private long rowLength;
	private File binFile;
	private RandomAccessFile nodeFile;
	private RandomAccessFile tableFile;
	private int offset;
	private Field field;

	public BinarySearchTree(File table, File file, int n, Field f, long l)
			throws IOException {
		binFile = file;
		nodeFile = new RandomAccessFile(binFile, "rw");
		tableFile = new RandomAccessFile(table, "r");
		offset = n;
		field = f;
		rowLength = l;
	}

	public void delete() {
		binFile.delete();
	}

	public void delete(Node node) throws IOException {
		Node temp = predecessor(node);
		if (node.getVal() == temp.getVal())
			temp = successor(node);
		Node par = temp.getParent();
		if (temp.getLeft() != null)
			if (par.getLeft() == temp) {
				par.setLeft(temp.getLeftPointer());
				temp.getLeft().setParent(temp.getParentPointer());
			} else {
				par.setRight(temp.getLeftPointer());
				temp.getLeft().setParent(temp.getParentPointer());
			}
		else if (temp.getRight() != null)
			if (par.getLeft() == temp) {
				par.setLeft(temp.getRightPointer());
				temp.getRight().setParent(temp.getParentPointer());
			} else {
				par.setRight(temp.getRightPointer());
				temp.getRight().setParent(temp.getParentPointer());
			}
		else {
			if (par.getLeft() == temp)
				par.setLeft(-1);
			else
				par.setRight(-1);
		}
		temp.setLeft(node.getLeftPointer());
		temp.setRight(node.getRightPointer());
		temp.setParent(node.getParentPointer());
	}

	private Node getBaseNode() throws IOException {
		return new Node(0);
	}

	@SuppressWarnings("rawtypes")
	public Long[] getData(String operator, AbstractValue test)
			throws IOException {
		return getData(operator, test, getBaseNode());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Long[] getData(String operator, AbstractValue test, Node root)
			throws IOException {
		if (TableCollection.get().debug() && root != null)
			System.out.println("Testing row " + (root.getVal() - offset)
					/ rowLength);
		if (root != null) {
			root = getBaseNode();
			ArrayList<Long> vals = new ArrayList<Long>(0);
			int result = root.getData().compareTo(test);
			if ((operator.contains("=") && !operator.contains("!"))
					&& result == 0) {
				if (result == 0) {
					vals.add(root.getVal());
					Long[] rest = getData(operator, test, root.getRight());
					for (long l : rest)
						vals.add(l);
				}
			} else if (operator.equals("!=")) {
				if (result != 0)
					vals.add(root.getVal());
				Long[] left = getData(operator, test, root.getLeft());
				Long[] right = getData(operator, test, root.getRight());
				for (long l : left)
					vals.add(l);
				for (long l : right)
					vals.add(l);
			}
			if (operator.contains(">") && result > 0) {
				if (result == 1) {
					vals.add(root.getVal());
					Long[] rest = getData(operator, test, root.getLeft());
					for (long l : rest)
						vals.add(l);
				}
				Long[] rest = getData(operator, test, root.getRight());
				for (long l : rest)
					vals.add(l);
			}
			if (operator.contains("<") && result < 0) {
				if (result == -1) {
					vals.add(root.getVal());
					Long[] rest = getData(operator, test, root.getRight());
					for (long l : rest)
						vals.add(l);
				}
				Long[] rest = getData(operator, test, root.getLeft());
				for (long l : rest)
					vals.add(l);
			}
			return vals.toArray(new Long[0]);
		}
		return new Long[0];
	}

	public Long[] inOrderWalk(Node root) throws IOException {
		if (root == null)
			root = getBaseNode();
		ArrayList<Long> node = new ArrayList<Long>(0);
		if (root.getLeft() != null)
			for (Long n : inOrderWalk(root.getLeft()))
				node.add(n);
		node.add(root.getVal() - offset);
		if (root.getRight() != null)
			for (Long n : inOrderWalk(root.getRight()))
				node.add(n);
		return node.toArray(new Long[0]);
	}

	public void insert(long val) throws IOException {
		if (nodeFile.length() == 0) {
			nodeFile.writeLong(val);
			nodeFile.writeLong(-1);
			nodeFile.writeLong(-1);
			nodeFile.writeLong(-1);
		} else
			insert(val, getBaseNode());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void insert(long val, Node root) throws IOException {
		Node in = new Node();
		in.setVal(val);
		Node par = root.getParent();
		Node left = root.getLeft();
		Node right = root.getRight();
		tableFile.seek(val);
		AbstractValue aVal = field.getData(tableFile);
		if (root.getData().compareTo(aVal) > 0 && left != null)
			insert(val, root.getLeft());
		else if (root.getData().compareTo(aVal) < 0 && right != null)
			insert(val, root.getRight());
		else {
			nodeFile.seek(nodeFile.length());
			nodeFile.writeLong(val);
			nodeFile.writeLong(root.getPos());
			nodeFile.writeLong(-1);
			nodeFile.writeLong(-1);
			if (root.getData().compareTo(aVal) > 0) {
				if (par == null)
					in.setParent(0);
				else if (par.getData().compareTo(root.getData()) > 0)
					in.setParent(par.getLeftPointer());
				else
					in.setParent(par.getRightPointer());
				root.setLeft(nodeFile.length() - 32);
			} else {
				nodeFile.seek(nodeFile.length());
				if (par == null)
					in.setParent(0);
				else if (par.getData().compareTo(root.getData()) > 0)
					in.setParent(par.getLeftPointer());
				else
					in.setParent(par.getRightPointer());
				root.setRight(nodeFile.length() - 32);
			}
		}
	}

	public Node max(Node root) throws IOException {
		if (root.getRight() != null)
			return max(root.getRight());
		return root;
	}

	public Node min(Node root) throws IOException {
		if (root.getLeft() != null)
			return max(root.getLeft());
		return root;
	}

	public Long[] reverseInOrderWalk(Node root) throws IOException {
		if (root == null)
			root = getBaseNode();
		ArrayList<Long> node = new ArrayList<Long>(0);
		if (root.getRight() != null)
			for (Long n : inOrderWalk(root.getRight()))
				node.add(n);
		node.add(root.getVal());
		if (root.getLeft() != null)
			for (Long n : inOrderWalk(root.getLeft()))
				node.add(n);
		return node.toArray(new Long[0]);
	}

	public Node select(long val) throws IOException {
		Node root = getBaseNode();
		if (root.getVal() < val)
			return select(val, root.getLeft());
		else if (root.getVal() > val)
			return select(val, root.getRight());
		else if (root.getVal() == val)
			return root;
		else
			return null;
	}

	public Node select(long val, Node root) throws IOException {
		if (root.getVal() < val)
			return select(val, root.getLeft());
		else if (root.getVal() > val)
			return select(val, root.getRight());
		else if (root.getVal() == val)
			return root;
		else
			return null;
	}

	public Node successor(Node root) throws IOException {
		if (root.getRight() != null)
			return min(root.getRight());
		return root;
	}

	public Node predecessor(Node root) throws IOException {
		if (root.getLeft() != null)
			return max(root.getLeft());
		return root;
	}

	class Node {
		private Long pos;
		private Long val;
		private Long parent;
		private Long left;
		private Long right;

		public Node() {
			pos = (long) -1;
			val = (long) -1;
			parent = (long) -1;
			left = (long) -1;
			right = (long) -1;
		}

		public Node(long pointer) throws IOException {
			pos = pointer;
			nodeFile.seek(pointer);
			val = nodeFile.readLong();
			parent = nodeFile.readLong();
			left = nodeFile.readLong();
			right = nodeFile.readLong();
		}

		public Long getPos() {
			if (pos == -1)
				return null;
			return pos;
		}

		public Node getLeft() throws IOException {
			if (left >= 0)
				return new Node(left);
			return null;
		}

		public Long getLeftPointer() {
			if (left == -1)
				return null;
			return left;
		}

		public void setLeft(long in) throws IOException {
			left = in;
			if (pos != -1) {
				nodeFile.seek(pos + 16);
				nodeFile.writeLong(in);
			}
		}

		public Node getParent() throws IOException {
			if (parent >= 0)
				return new Node(parent);
			return null;
		}

		public Long getParentPointer() {
			if (parent == -1)
				return null;
			return parent;
		}

		public void setParent(long in) throws IOException {
			parent = in;
			if (pos != -1) {
				nodeFile.seek(pos + 8);
				nodeFile.writeLong(in);
			}
		}

		public Node getRight() throws IOException {
			if (right >= 0)
				return new Node(right);
			return null;
		}

		public Long getRightPointer() {
			if (right == -1)
				return null;
			return right;
		}

		public void setRight(long in) throws IOException {
			right = in;
			if (pos != -1) {
				nodeFile.seek(pos + 24);
				nodeFile.writeLong(in);
			}
		}

		public Long getVal() {
			if (val == -1)
				return null;
			return val;
		}

		@SuppressWarnings("rawtypes")
		public AbstractValue getData() throws IOException {
			tableFile.seek(val);
			return field.getData(tableFile);
		}

		public void setVal(long in) throws IOException {
			val = in;
			if (pos != -1) {
				nodeFile.seek(pos);
				nodeFile.writeLong(in);
			}
		}
	}
}
