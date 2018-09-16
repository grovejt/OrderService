** Sonic Order Exam **

* John Grove
* grovejt@gmail.com
* [https://jackgrove.com](https://jackgrove.com/)

This is an implementation of the Sonic exam project consisting of a simple order domain object that contains a collection of order items and an calculate the cost of an order taking into account which items are taxable and which are not.

The original exam specifications are in: [./docs/SonicJavaProgrammingExercise.md](./docs/SonicJavaProgrammingExercise.md)

The source code files relevant to this exam are under [.src/main/java/com/exam/domain](src/main/java/com/exam/domain) and [src/main/java/com/exam/domain](./src/test/java/com/exam/domain) 

---
** Program requirements: **

You are working on a team developing an e-commerce application. One of your tasks is to complete the implementation of the Order class that an intern has started along with any other class or classes on which it depends. 

The Order constructor requires an array of OrderItems. The business rules dictate that there are two types of order items required, service and material. There is one distinction between them, only material items are taxable. An instance of an OrderItem is only required to contain an Item and a quantity.

An Order, once constructed, is immutable (no one should be able to change it). 

The Order object also needs to be Serializable as it will be distributed across multiple VM’s. 

It is critical that the method that returns the order-total returns accurately to the nearest penny.

There is an expected future requirement that Items be used as keys in a Hashtable so address this issue now.

Make any changes needed to the Order object to meet the requirements stated above, although you should not have to add any more public methods. This API will be used by many developers so implement all common methods.

---

** Simplified Class Diagram **

![](./docs/DomainClassOverview.png?raw=true "Simplified Class Diagram")
---

** Latest Reports: **

* javadocs: [./docs/javadoc/index.html](./docs/javadoc/index.html)
* code coverage metrics: [./docs/jacoco/test/html/index.html](./docs/jacoco/test/html/index.html)
* static code analysis (pmd): [./docs/pmd/pmd/main.html](./docs/pmd/pmd/main.html)


---
** Notes **
1) Clone the repo
2) Run a full gradle build

After a full gradle build the following updated documentation will be available:
* javadocs: [./build/docs/javadoc/index.html](./build/docs/javadoc/index.html)
* code coverage metrics: [./build/reports/jacoco/test/html/index.html](./build/reports/jacoco/test/html/index.html)
* static code analysis (pmd): [./build/reports/pmd/main.html](./build/reports/pmd/main.html)


 