class LinkedListDeque:
    """
    >>> ld = LinkedListDeque()
    >>> ld.addFirst(10)
    >>> ld.addFirst(100)
    >>> print(ld.size())
    2
    """
    class QueueNode:
        def __init__(self, item=None):
            self._item = item
            self._next = self
            self._pre = self

    def __init__(self):
        self._centinel = self.QueueNode()
        self._size = 0


    def addFirst(self, item):
        """and item to the front of the queue

        Args:
            item (any): _description_
        >>> ld = LinkedListDeque()
        >>> ld.addFirst(3)
        >>> ld._centinel._next._next == ld._centinel._next._pre
        True
        """
        first = self.QueueNode(item)
        oldFirst = self._centinel._next
        self.connect(self._centinel, first, oldFirst)
        self._size += 1

    def addLast(self, item):
        last = self.QueueNode(item)
        oldLast = self._centinel._pre
        self.connect(oldLast, last, self._centinel)
        self._size += 1

    def connect(self, lnode, mnode, rnode):
        mnode._pre = lnode
        mnode._next = rnode
        lnode._next = mnode
        rnode._pre = mnode
    
    def size(self):
        return self._size