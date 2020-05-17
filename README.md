Battleship-Game
====
A simple version of the classic game Battleship.
Battleship is usually a two-player game, where each player has a fleet of ships and an ocean (hidden from the other player),
and tries to be the first to sink the other player's fleet.
This is a one-player vs. computer version, where the computer places the ships, and the human attempts to sink them.

BattleshipGame
----
This the 'main' class, containing the main method, which starts by creating an instance of Ocean.

Ocean
----
This contains a 10x10 array of Ships,representingan“ocean”,and some methods to manipulate it.

Ship
----
There are 4 types of ships: Battleship, Cruiser, Destroyer and Submarine.
The 4 types are extended from abstract class Ship. EmptySea is also extended from Ship.
