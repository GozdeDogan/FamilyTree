<<<<<<< Updated upstream
# FamilyTree
Consider the binary tree representation of a family tree in the book. Extend
BinaryTree class to implement FamilyTree class. Your FamilyTree class should
include the following features:
1. One parameter constructor to build a person’s family. The person should
be at the root.
2. Add a new person in the family tree.
The add method should take three parameters:
 The name of the person to be added
 The name of the person’s father or mother
 The nickname of the person's father or mother.
The nickname is given as in Arabic culture:
 ibn-Fatih (son/daughter of Fatih) or
 ebu-Fatih (father/mother of Fatih).
So, the name of a son/daughter or the name of the father/mother is given
in nickname format to the method. So, there could be more than one
person with the same name in the family tree. To specify where to insert
the person, the name of his/her mother/father is specified together with
mother's/father's nickname. If there are more than one father/mother with
the same name and nickname the insertion cannot be completed. Your
method should throw an exception specifying both person's mother/father
and daughters/sons.
3. A method to traverse the family tree in pre-order. Note that the pre-order
traversal is not the same as the traversal of the binary tree. You should
override the pre-order traversal method of BinaryTree.
4. Override the iterator method of BinaryTree o that the iterator correctly
traverses the family tree.
Test:
 Read family.txt file which includes a family with a person in each line.
The format of the txt file will be as:
Hasan
Ayşe, Hasan, ebu-Ayşe
Ali, Ayşe, ibn-Hasan
Sema, Hasan, ebu-Ayşe
…
 Create a FamilyTree.
 Write your traverse method such that prints each traversed node to
console. Traverse your tree.
=======
FamilyTree
>>>>>>> Stashed changes
