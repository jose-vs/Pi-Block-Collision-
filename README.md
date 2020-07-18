# Pi Block Collision 

this is a code which was inspired by 3blue1browns video "The most unexpected answer to a counting puzzle" which includes two sliding blocks with one going towards the smaller one 
which starts off stationary. Therere is a wall behind the small block which will allow the it to keep bouncing and until its velocity is smaller than the bigger block when it 
redirects it assuming that the blocks are purely elastic. This means that there are no energy lost during collision and sliding is frictionless.

https://www.youtube.com/watch?v=HEfHFsfGXjs&t
![](images/3b1b.JPG)

### How it works and what it does

I decided to code this in java with javaswing as its the only thing I've learnt so far in my studies which will allow me to add a GUI to my code. providing that the blocks are purely elastic, I implemented the formula for elastic Collision in my code to update the velocity value for a block object for every collision. If the block hits the wall, the velocity changes from negative to positive as there is no energy lost during that collision. We can just count all the times that this collision occurs.

![](images/elasticCollision.JPG)

### version 1.0
-the code doesn't allow anything past 10 digits
