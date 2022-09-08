This is the readme file for PA11.
Timing experiments that compares my improved algorithm to the required algorithms for the input file big11.mtx and descrption why the algorithms have relative performance differences.

java PA11Main.java big11.mtx TIME: the tim
1. heuristic:      cost =      3.396930754184723, 0 milliseconds
        This algorithm has a better timing performace than the backtracking 
        one becasue it visits the closest city each time. However, it is
        finding the shortest path. The reason for that seems to that
        no matter what it jumps to the next closest city while there might 
        be some shortcuts in between. 
2. backtack:       cost =      1.356677532196045, 227 milliseconds
        This one seems more likey to find the shortest path becasue 
        it tries all possible paths. It has one downside though, which is 
        that it uses up more memory and time.
3. mine:           cost =      3.396930754184723, 0 milliseconds
        I used the simple recursion to find the shortest path.
        It keeps track of which city is the best to visit next in terms
        of the cost. However, it does not look much different than the heuristic approach. 

