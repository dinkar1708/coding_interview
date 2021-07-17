def solution(A, B):
    """
    https://app.codility.com/demo/results/trainingDJQ263-4J9/
    :param A:  weights
    :param B: direction
    0 represents a fish flowing upstream - 0 fish ------ left direction
    1 represents a fish flowing downstream - 1 fish ------  right direction
    :return:
    """
    stay_alive = 0
    stack = []
    for index, current_weight in enumerate(A):
        # left or upstream
        if B[index] == 0:
            # stack has downstream fish information
            weight_down_stream = stack.pop() if stack else -1
            while weight_down_stream != - 1 and weight_down_stream < current_weight:
                # current fish weight is greater, it will check for all stack fish and keep killing
                weight_down_stream = stack.pop() if stack else -1
            if weight_down_stream == -1:
                stay_alive += 1
            else:
                # stack fish killed the current weight fish
                stack.append(weight_down_stream)
        else:
            # 1 right or downstream
            stack.append(current_weight)
    return len(stack) + stay_alive


if __name__ == '__main__':
    result = solution([4, 3, 2, 1, 5], [0, 1, 0, 0, 0])
    print("Solution " + str(result))
