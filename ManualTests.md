#Manual Tests

##Control Panel
* Clicking a card should highlight it with green, and re-clicking should remove the green.
* Attempting to choose a sixth card should not leave it highlighted.
* Clicking "Lock in cards" should make the robot perform the moves in chronological order.
* Clicking "Lock in cards" when less than 5 cards are chosen should result in no movement.

##Tile functionality
* Conveyors
    * A normal conveyor should move the player 1 step in the direction the conveyor is pointed.
    * An express conveyor should move the player 2 steps in the direction it's pointed.
    * A curved conveyor should move the player, but also rotate the player if the player came
    there from a conveyor.
* Flags
    * Getting any given flag should not be possible without getting all flags with lower associated numbers first.
    * Getting all flags should prompt the win screen.
* Holes should cause the player to respawn (with one less life and zero damage)
* Lasers 
    * Single lasers should give the player one damage, and ten damage should cause respawn.
    * Double lasers should give the player two damage, and ten or more damage should cause respawn.
* Wrenches should remove one damage, but never make damage take values below zero. 
* Cogwheels
    * Green ones should rotate the player clockwise.
    * Red ones should rotate the player counter-clockwise.
* Walls should stop the player from moving through them.