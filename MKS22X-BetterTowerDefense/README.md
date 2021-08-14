# BetterTowerDefense (BTD) by No Clue
A Tower Defense game where the enemies can choose their own path from their start to your home base (That you need to defend). The player can build walls, and build defensive towers which also can act as walls to route the enemies in any strategy they wish. Different types of towers have different statistics, and special effects which can be used.
## How To Run
The project must be run through Processing 3+. To open the game, you must open the BTD.pde file, and click play. To play the game, you start with $1000, and earn money by popping balloons that come in waves. To pop these balloons you must place towers on the map and walls to route enemies in your strategy. To place towers, click on one of the tower buttons and click where on the map you would like to place it. To start press the pause/play button, which will play each wave of balloons (each wave pauses the game when it ends).
## Development Log
#### 5/17/19
Amit: Added most enemy variables and basic methods, as well as the same for world methods and variables.
Mario: Added most tower variables and basic methods such as fire and display.
#### 5/20/19
Amit & Mario: Added display and scaling methods, created some test towers and enemies. Fixed enemy display bug where they wouldn't display in the middle.
#### 5/21/19
Amit: Added Some basic structure for towers and enemies, added screenToWorld grid scaling functions, mouse click to add towers to the map.  
Amit & Mario: Added smooth enemy movement between tiles  
Mario: Started pathfinding but ran into bugs
#### 5/22/19
Amit: Fixed Mario's pathfinding issue by switching y and x values, then partially rewrote so that x mapped to x and y mapped to y again. Made a secondary algorithm in branch pathFinding-recursive that was used mostly for debugging.
Mario: Started working on different ballons generating.
#### 5/23/19
Amit: Started working on Bullet and Tower classes to allow for shooting balloons. Ran into some thinking problems concerning collision between bullets and balloons. Thinking about using the pixel buffer and checking for balloons.
Mario: Added different speeds and types of balloons. Found an issue where balloons with a "speed" of 3 (specifically) would pathfind correctly to the player's base but get stuck on the last tile...
#### 5/24/19
Amit: Implemented dart towers and their shooting, walls (basically towers with no fire), found an issue where balloons hit with bullets would start going in one direction forever. Tweaked balloons speeds as well.
Mario: Sorry this is a lot of the days, but continued working on pathfinding bugs.
#### 5/25/19  
Amit: Added Freeze Tower, started working on fixing some bugs with damage and balloons floating through the walls. Fixed the issue by changing the way balloons move a little bit. Added effects class to balloons so that they can be modified by towers (used by freeze to slow balloons)
Mario: More balloon types, fixed speed bugs where some speeds wouldn't work out well"
#### 5/28/19
Amit: Resized screen into a square the whole time, fixed the waves from not doing anything, fixed an index out of bounds.
Amit & Mario: Both worked on creating waves of enemies -- initial system together using a queue of actions that the computer does to spawn enemies in each wave.
#### 5/29/19  
Mario: Added MOABS -- "Boss" enemies. Minor balancing changes and fixes for MOABS including giving them more health before popping like normal enemies.
Amit: Fixed really unusual bug in enemy pathfinding caused by towers being placed in specific positions around the enemy base, and then causing balloons to either get stuck inside the enemy base, and/or going through towers next to the enemy base.
#### 5/30/19
Amit: Optimized Enemy Drawing by changing them to color a PShape (currently a circle) and then draw that to screen, which is much more efficient than drawing a ellipse to the screen. This allowed the amount of enemies to be on screen to increase from ~80 to ~300. Also added first 10 waves of the game to the system.
Mario: Added tack towers and more design changes.
#### 5/31/19
Amit: Added lots more enemy waves and fixed the fullscreen not working on school computers.
Mario: Added tack towers and more design changes.
#### 6/2/19
Mario: More rounds, game balancing, finishing touches
#### 6/4/19
Amit: Added Game UI and Tower selection buttons, as well as changed towers to use PNG photos for their textures. Added lots of labels and optimized a couple of things.
Mario: Final touches, helped with UI. Added some features for the presentation that wouldn't be in real game.
