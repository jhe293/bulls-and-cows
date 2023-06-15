# Bulls and Cows Game

The Bulls and Cows game is a code-breaking game designed for two or more players. Each player chooses a secret code of 4 digits from 0 – 9. The digits must be all different. The goal of the game is for each player to guess the other player's secret code.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 11 or higher

### How to Run

Run the game controller

## How to Play

1. The game starts with one player setting a secret code.

2. Each player takes turns guessing the opponent's secret code.

3. After each guess, the player receives feedback in the form of bulls and cows.

- Bulls: the number of matching digits in their right positions.
- Cows: the number of matching digits in different positions.

4. The game continues until a player correctly guesses the opponent's secret code or a maximum number of attempts is reached.

## Example

If the computer's secret code is 4281, the match responses for the following guesses would be:

| Guess | Bulls | Cows |
|-------|------|------|
| 1234  |   0  |  2   |
| 5678  |   0  |  0   |
| 4821  |   2  |  2   |
| 4281  |   4  |  0   |

## Author

Author: [Jiawei He](https://github.com/jhe293)

