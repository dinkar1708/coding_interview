def solution(A, B):
    """
    Codility 100%
    https://app.codility.com/demo/results/trainingF5HTCA-YFV/
    
    Idea is to use stack concept
    For each iteration if current fish is of type 1 add it to stack 1 fish
    Create stack 1 fish - it always holds the downstream ie 1 kind of fish
     and keep killing the smaller fish from the list if it is greater
      else killed by current fish.
    Now if stack 1 fish has no fish it means current fish can be counted as remaining fish.


    0 represents a fish flowing upstream - 0 fish
    1 represents a fish flowing downstream - 1 fish

    A[0] = 4    B[0] = 0 alive fish
    A[1] = 3    B[1] = 1 downstream
    A[2] = 2    B[2] = 0 eaten by A[1]
    A[3] = 1    B[3] = 0 eaten by A[1]
    A[4] = 5    B[4] = 0 eat to A[1] and remain alive

    """

    count = 0
    # stores the 1 fish in stack
    stack_1_fish = []
    print(A)
    print(B)
    for index in range(len(A)):
        if B[index] == 0:
            # until stack has some 1 fish
            while stack_1_fish:
                # get last fish from stack and check if it can die or not
                # the larger fish eats the smaller one.
                # two fish P and Q meet each other when P < Q, B[P] = 1 and B[Q] = 0, and there are no living fish between them
                if stack_1_fish[-1] > A[index]:
                    # stack 1 fish kill to current fish
                    # exit from while loop and else block also execute next top for loop
                    # check for other fish fight
                    print("Killed by " + str(stack_1_fish[-1]) + " Die " + str(A[index]))
                    break
                else:
                    # stack 1 fish is killed by current fish
                    p = stack_1_fish.pop()
                    print("Killed by " + str(A[index]) + " Die " + str(p))

            # this is the case when stack becomes empty ie. no fish of kind 1
            # it would never meet previous fish but can fight with downstream fish
            else:
                print("Count fish as remaining......." + str(A[index]))
                count += 1
        else:
            # fish 1 found add to stack
            stack_1_fish.append(A[index])
            print("1 fish found, add to stack, it can kill or killed by someone..." + str(A[index]))
            print(stack_1_fish)

    print("Count: " + str(count))
    print("Stack 1 fish: " + str(len(stack_1_fish)))
    return count + len(stack_1_fish)


if __name__ == '__main__':
    result = solution([4, 3, 2, 1, 5], [0, 1, 0, 0, 0])
    # result = solution([4, 3, 2, 1, 5], [0, 0, 0, 0, 0])
    # result = solution([4, 3, 2, 1, 5], [1, 1, 1, 1, 1])
    print("")
    print("Solution " + str(result))

"""
    [4, 3, 2, 1, 5]
    [0, 1, 0, 0, 0]
    Count fish as remaining.......4
    1 fish found, add to stack, it can kill or killed by someone...3
    [3]
    Killed by 3 Die 2
    Killed by 3 Die 1
    Killed by 5 Die 3
    Count fish as remaining.......5
    Count: 2
    Stack 1 fish: 0
    
    Solution 2
    
    [4, 3, 2, 1, 5]
    [0, 0, 0, 0, 0]
    Count fish as remaining.......4
    Count fish as remaining.......3
    Count fish as remaining.......2
    Count fish as remaining.......1
    Count fish as remaining.......5
    Count: 5
    Stack 1 fish: 0
    
    Solution 5
    
    [4, 3, 2, 1, 5]
    [1, 1, 1, 1, 1]
    1 fish found, add to stack, it can kill or killed by someone...4
    [4]
    1 fish found, add to stack, it can kill or killed by someone...3
    [4, 3]
    1 fish found, add to stack, it can kill or killed by someone...2
    [4, 3, 2]
    1 fish found, add to stack, it can kill or killed by someone...1
    [4, 3, 2, 1]
    1 fish found, add to stack, it can kill or killed by someone...5
    [4, 3, 2, 1, 5]
    Count: 0
    Stack 1 fish: 5
    
    Solution 5

"""
