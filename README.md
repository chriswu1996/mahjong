# Mahjong

  This is the code for a game of Mahjong (a classic Chinese tile-based game) and an AI for it that I wrote for a project. I have also developed graphics and UI for the game using Unity, but will not upload it on Github because of the possibility of actually making that into an APP in the future.
  
  On each turn, the AI considers every possible play and evaluates the hands that it can end up with. It then chooses the best play based on the probability of winning calculated from the remaining tiles in the game and the tiles in its hand. I decreased the computational load of the AI to linear time complexity by introducing an encoding scheme for Mahjong tiles that tailors specifically to the rules of the game, effectively reducing the problem to an array sum calculation. In `Board.java`, the numbers
  `{1,2,3,4,5,6,7,8,9,21,22,23,24,25,26,27,28,29,41,42,43,44,45,46,47,48,49,52,55,58,61,64,67,70};`
are Mahjong tiles where `1-9, 21-29, 41-49` correspond to Tiao, Bing and Wan accordingly. `52,55,58,61,64,67,70` correspond to Dong, Xi, Nan, Bei, Zhong, Fa, Bai accordingly.
