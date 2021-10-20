
# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution(object):
    def addTwoNumbers(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        head = l1
        while l1 != None or l2 != None:
            l1.val = l1.val + l2.val
            if l1.val >= 10:
                l1.val -= 10
                l1.next.val += 1

        return head
    

    l1 = ListNode(val = 2)
    l1.next = ListNode(val = 5)

    l2 = ListNode(val = 3)
    l2.next = ListNode(val = 6)

    print(addTwoNumbers(object,l1, l2))