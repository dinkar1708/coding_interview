def solution(S):
    """
    Codility 100%
    https://app.codility.com/demo/results/trainingTJQUQF-HAE/
    
    Idea is to use stack concept
    Used array [] as stack
    Keep adding item in stack until open brackets found, Keep popping up item if closing bracket found.
    On each pop of item check popped item it should match with the opposite bracket as explained in example else not balanced.

    4 push 4 pop
    {[()()]}
    push {
    push [
    push (
        pop )
    push (
        pop )
        pop ]
        pop }
    gets empty

    2 push 1 pop
    ([)()]
    (
    [
        ) - not matching with [ so not balanced

    """

    stack = []
    for bracket in S:
        # open bracket found push in to stack, used array as stack append item at top
        if bracket == "{" or bracket == "[" or bracket == "(":
            stack.append(bracket)
        # any closing bracket found and stack is not empty remote item from stack top
        elif bracket == "}" and stack:
            pop_item = stack.pop()
            # check  popped item, if it is not similar( ie. opposite or open) as bracket then this S can not be balanced
            if pop_item != "{":
                return 0
        elif bracket == "]" and stack:
            pop_item = stack.pop()
            if pop_item != "[":
                return 0
        elif bracket == ")" and stack:
            pop_item = stack.pop()
            if pop_item != "(":
                return 0
        # at any point if neither open nor close operation can be performed, stack is not balanced
        else:
            return 0

        print(stack)

    # if stack is empty stack, it is balanced ie. all push and pop operation on S is successful¬.
    if not stack:
        return 1
    # S is not balanced
    return 0


if __name__ == '__main__':
    # result = solution("{[()()]}")
    result = solution("([)()]")
    print("")
    print("Solution " + str(result))

"""
    ['{']
    ['{', '[']
    ['{', '[', '(']
    ['{', '[']
    ['{', '[', '(']
    ['{', '[']
    ['{']
    []
    
    Solution 1
    
    ['(']
    ['(', '[']
    
    Solution 0
"""
