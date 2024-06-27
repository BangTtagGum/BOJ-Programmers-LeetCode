from collections import deque

test_case = int(input())

xpos = [-1,1,0,0]
ypos = [0,0,-1,1]

def bfs(x, y):
    visited = [[False for _ in range(w+2)] for _ in range(h+2)]

    q = deque()
    q.append((x,y,0))
    
    while q:
        x, y, value = q.popleft()
        for dir in range(4):
            nx = x + xpos[dir]
            ny = y + ypos[dir]
            if 0 <= nx <= h+1 and 0 <= ny <= w+1 and tmap[nx][ny] != "*" and not visited[nx][ny] :
                visited[nx][ny] = True
                if vmap[nx][ny] < 0:
                    vmap[nx][ny] = 0
                if tmap[nx][ny] == '.':
                    q.appendleft((nx, ny, value))
                    vmap[nx][ny] += value
                elif tmap[nx][ny] == '#':
                    q.append((nx,ny, value + 1))
                    vmap[nx][ny] += (value + 1)

def find_prisoner():
    prisoners = []
    for i in range(h):
        for j in range(w):
            if tmap[i+1][j+1] == "$":
                prisoners.append((i+1, j+1))
                tmap[i+1][j+1] = '.'
    return prisoners

for test in range(test_case):
    h, w = map(int, input().split())

    # 상근이 들어올 위치 표시
    tmap = []
    tmap.append(['.'] * (w + 2))
    for i in range(h):
        tmap.append(['.'] + list(input()) + ['.'])
    tmap.append(['.'] * (w + 2))

    vmap = [[-1 for _ in range(w+2)] for _ in range(h+2)]
    
    prisoners = find_prisoner()

    bfs(prisoners[0][0], prisoners[0][1])
    bfs(prisoners[1][0], prisoners[1][1])
    bfs(0,0)

    minimum = (h+2) * (w+2)
    for i in range(h):
        for j in range(w):
            if tmap[i+1][j+1] == '*' or vmap[i+1][j+1] == -1:
                continue
            elif tmap[i+1][j+1] == '#':
                minimum = min(minimum, vmap[i+1][j+1] - 2)
            else:
                minimum = min(minimum, vmap[i+1][j+1])
    print(minimum)