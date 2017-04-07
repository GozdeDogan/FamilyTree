import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * Created by GozdeDogan on 5.04.2017.
 */
public class FamilyTree<E extends Comparable<E>>{

    /**
     * BinaryTree class'i ornek alinarak olusturuldu
     * @param <E>
     */
    protected static class Node<E> implements Serializable {
        /** The information stored in this node. */
        protected E data;
        /** Reference to the left child. */
        protected Node<E> left;
        /** Reference to the right child. */
        protected Node<E> right;

        /**
         * Construct a node with given data and no children.
         * @param data The data to store in this node
         */
        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }

        public Node(Node<E> node){
            this.data = node.data;
            this.left = node.left;
            this.right = node.right;
        }

        /**
         * Returns a string representation of the node.
         * @return A string representation of the data fields
         */
        @Override
        public String toString() {
            return data.toString();
        }
    }


    //Data fields
    Node<E> root = null;

    //Methods
    /**
     * Starter method find.
     * @pre The target object must implement
     *      the Comparable interface.
     * @param target The Comparable object being sought
     * @return The object, if found, otherwise null
     */
    public E find(Object target) {
        return (E)find(root, (E)target);
    }

    /**
     * Recursive find method.
     * @param localRoot The local subtree?s root
     * @param target The object being sought
     * @return The object, if found, otherwise null
     */
    private E find(Node<E> localRoot, E target) {
        if (localRoot == null) {
            return null;
        }
        // Compare the target with the data field at the root.
        int compResult = target.compareTo(localRoot.data);
        if (compResult == 0) {
            return localRoot.data;
        } else if (compResult < 0) {
            return find(localRoot.left, target);
        } else {
            return find(localRoot.right, target);
        }
    }

    /**
     * Dosyayý satýr satýr okuyarak gerekli iþlemleri yapar
     * @param fileName okunacak dosya
     */
    public void fill(String fileName){
        try {
            FileReader fR = new FileReader(fileName);
            BufferedReader bR = new BufferedReader(fR);

            String line;
            line = bR.readLine();
            root = new Node<E>((E)line); // ilk okunan root oldugu icin direk eklendi!

            //System.out.println("root: " + root.toString());

            while((line = bR.readLine()) != null) {
                //System.out.println("lines: " + line);
                String name = null;
                String parent = null;
                String nickname = null;

                StringTokenizer sT = new StringTokenizer(line, ", ");
                if(sT.hasMoreElements()){
                    name = (String)sT.nextElement();
                }
                if(sT.hasMoreElements()){
                    parent = (String)sT.nextElement();
                }
                if(sT.hasMoreElements()){
                    nickname = (String)sT.nextElement();
                }

                //System.out.println("name:" + name + "\tparent:" + parent + "\tnickname:" + nickname);

                String nick = null;
                String who = null;

                //System.out.println("linessSize: " + linessSize);

                if(name != null && parent != null && nickname != null) {
                    StringTokenizer subST = new StringTokenizer(nickname, "-");
                    if (subST.hasMoreElements())
                        nick = (String)subST.nextElement();
                    if (subST.hasMoreElements())
                        who = (String)subST.nextElement();

                }

                if (nick.equals("ibn")) { //child - sola ekle
                    root.left = addLeft(root.left, (E) name);
                } else if (nick.equals("ebu")) { // kardes - saga ekle
                    root.right = addRight(root.right, (E) name);
                }

            }
            bR.close();

        }catch(FileNotFoundException e){
        } catch (IOException e) {
        }
    }

    /**
     * gelen root null ise ona, null degilde root'un left'ine ekleme yapar.
     * @param lRoot
     * @param item
     * @return
     */
    private Node<E> addLeft(Node<E> lRoot, E item){
        if (lRoot == null)
            return new Node<E>(item);
        else if(lRoot.equals(item))
            return lRoot;
        else{
            lRoot.left = addLeft(lRoot.left, item);
            return lRoot;
        }
    }

    /**
     * gelen root null ise ona, null degilde root'un right'ine ekleme yapar.
     * @param lRoot
     * @param item
     * @return
     */
    private Node<E> addRight(Node<E> lRoot, E item){
        if (lRoot == null)
            return new Node<E>(item);
        else if(lRoot.equals(item))
            return lRoot;
        else{
            lRoot.right = addRight(lRoot.right, item);
            return lRoot;
        }
    }

    /**
     * Starter method add.
     * @pre The object to insert must implement the
     *      Comparable interface.
     * @param item The object being inserted
     * @return true if the object is inserted, false
     *         if the object already exists in the tree
     */
    public boolean add(Object item) {
        boolean res = false;
        root = add(root, (E)item, res);
        return res;
    }

    public boolean contains(Object target) {
        return false;
    }

    /**
     * Recursive add method.
     * @post The data field addReturn is set true if the item is added to
     *       the tree, false if the item is already in the tree.
     * @param localRoot The local root of the subtree
     * @param item The object to be inserted
     * @return The new local root that now contains the
     *         inserted item
     */
    private Node<E> add(Node<E> localRoot, E item, boolean res) {
        if (localRoot == null) {
            // item is not in the tree ? insert it.
            res = true;
            return new Node<E>(item);
        } else if (item.compareTo(localRoot.data) == 0) {
            // item is equal to localRoot.data
            res = false;
            return localRoot;
        } else if (item.compareTo(localRoot.data) < 0) {
            // item is less than localRoot.data
            localRoot.left = add(localRoot.left, item, res);
            return localRoot;
        } else {
            // item is greater than localRoot.data
            localRoot.right = add(localRoot.right, item, res);
            return localRoot;
        }
    }

    /**
     * Starter method delete.
     * @post The object is not in the tree.
     * @param target The object to be deleted
     * @return The object deleted from the tree
     *         or null if the object was not in the tree
     * @throws ClassCastException if target does not implement
     *         Comparable
     */
    public E delete(Object target) {
        E res = null;
        root = delete(root, (E)target, res);
        return res;
    }

    public boolean remove(Object target) {
        return false;
    }

    /**
     * Recursive delete method.
     * @post The item is not in the tree;
     *       deleteReturn is equal to the deleted item
     *       as it was stored in the tree or null
     *       if the item was not found.
     * @param localRoot The root of the current subtree
     * @param item The item to be deleted
     * @return The modified local root that does not contain
     *         the item
     */
    private Node<E> delete(Node<E> localRoot, E item, E res) {
        if (localRoot == null) {
            // item is not in the tree.
            res = null;
            return localRoot;
        }

        // Search for item to delete.
        int compResult = item.compareTo(localRoot.data);
        if (compResult < 0) {
            // item is smaller than localRoot.data.
            localRoot.left = delete(localRoot.left, item, res);
            return localRoot;
        } else if (compResult > 0) {
            // item is larger than localRoot.data.
            localRoot.right = delete(localRoot.right, item, res);
            return localRoot;
        } else {
            // item is at local root.
            res = localRoot.data;
            if (localRoot.left == null) {
                // If there is no left child, return right child
                // which can also be null.
                return localRoot.right;
            } else if (localRoot.right == null) {
                // If there is no right child, return left child.
                return localRoot.left;
            } else {
                // Node being deleted has 2 children, replace the data
                // with inorder predecessor.
                if (localRoot.left.right == null) {
                    // The left child has no right child.
                    // Replace the data with the data in the
                    // left child.
                    localRoot.data = localRoot.left.data;
                    // Replace the left child with its left child.
                    localRoot.left = localRoot.left.left;
                    return localRoot;
                } else {
                    // Search for the inorder predecessor (ip) and
                    // replace deleted node's data with ip.
                    localRoot.data = findLargestChild(localRoot.left);
                    return localRoot;
                }
            }
        }
    }
    /**
     * Find the node that is the
     * inorder predecessor and replace it
     * with its left child (if any).
     * @post The inorder predecessor is removed from the tree.
     * @param parent The parent of possible inorder
     *        predecessor (ip)
     * @return The data in the ip
     */
    private E findLargestChild(Node<E> parent) {
        // If the right child has no right child, it is
        // the inorder predecessor.
        if (parent.right.right == null) {
            E returnValue = parent.right.data;
            parent.right = parent.right.left;
            return returnValue;
        } else {
            return findLargestChild(parent.right);
        }
    }

    /**
     * iterator class'inin objesini return eder
     * @return
     */
    public Iterator<E> iterator(){
        return new FamilyTreeIter<E>(root);
    }

    /**
     * Iterator inner class'i
     * @param <E>
     */
    private static class FamilyTreeIter<E> implements Iterator<E>{
        private ArrayList<E> list = new ArrayList<E>(); //elemanlarin eklenecegi liste
        private int current = 0;

        /**
         * one-parameter constructor.
         * root'u alýr.
         * @param root
         */
        public FamilyTreeIter(Node<E> root) {
            //System.out.println("traversel - root: " + root.toString());
            preorder(root);
        }

        /**
         * pre-order traverse
         * @param root
         */
        private void preorder(Node<E> root) {
            if (root != null) {
                list.add(root.data);
                preorder(root.left);
                preorder(root.right);
            }
        }

        /**
         * Next element for traversing
         */
        public boolean hasNext() {
            if (current < list.size())
                return true;
            return false;
        }

        /**
         * Get the current element and move cursor to the next
         */
        public E next() {
            return list.get(current++);
        }

        /**
         * remove metodu exception firlatir.
         * Kullanilmamasi gerekiyor.
         */
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

    /**
     * tree'yi string'e cevirir.
     * Bu islemi iterator ile yapar.
     * iterator ile yaptigi icin pre-order dolasir.
     * @return StringBuilder retrun eder
     */
    public String toString(){
        StringBuilder sb = new StringBuilder("LEVEL ORDER TRAVELS ==>>\n[");
        Iterator<E> iter = iterator();
        while(iter.hasNext()) {
            E item = iter.next();
            sb.append(item);
            if(iter.hasNext())
                sb.append(" ==>> ");
        }
        sb.append("]");
        //System.out.println("sb: " + sb.toString());
        return sb.toString();
    }
}
