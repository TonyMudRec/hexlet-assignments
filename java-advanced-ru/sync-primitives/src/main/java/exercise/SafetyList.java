package exercise;

class SafetyList {
    // BEGIN
    public class SafetyListNode {

        public SafetyListNode(Integer value) {
            this.value = value;
            this.index = 0;
        }

        public SafetyListNode(Integer value, SafetyListNode previousNode) {
            this.value = value;
            this.index = previousNode.index + 1;
            this.previousNode = previousNode;
            previousNode.nextNode = this;
        }

        public Integer value;

        public Integer index;

        public SafetyListNode nextNode;
        public SafetyListNode previousNode;
    }
    public SafetyListNode node;
    private Integer size;

    public SafetyList() {
        size = 0;
    }

    public synchronized void add(Integer i) {
        if (size == 0) {
            node = new SafetyListNode(i);
        } else {
            node = new SafetyListNode(i, node);
        }
        size++;
    }

    public Integer getSize() {
        return this.size;
    }

    public int get(int index) {
        while (true) {
            if (this.node.index == index) {
                return this.node.value;
            }
        }
    }

    // END
}
