import java.util.*;

public class AIAgent {
    Random rand;

  public AIAgent(){
    rand = new Random();
  }

/*
  The method randomMove takes as input a stack of potential moves that the AI agent
  can make. The agent uses a rondom number generator to randomly select a move from
  the inputted Stack and returns this to the calling agent.
*/

  public Move randomMove(Stack possibilities){

    int moveID = rand.nextInt(possibilities.size());
    System.out.println("Agent randomly selected move : "+moveID);
    for(int i=1;i < (possibilities.size()-(moveID));i++){
      possibilities.pop();
    }
    Move selectedMove = (Move)possibilities.pop();
    return selectedMove;
  }

  public Move nextBestMove(Stack whiteStack, Stack blackStack) {
        Stack whitePieces = (Stack) whiteStack.clone();
        Stack black = (Stack) blackStack.clone();
        Move whiteMove, currentMove, bestMove;
        Square blackPosition;
        int points = 0;
        int highestpoints = 0;
        bestMove = null;

        while (!whiteStack.empty()) {
            whiteMove = (Move) whiteStack.pop();
            currentMove = whiteMove;

            //compare white landing positions to black positions, if eating a piece is possible it takes it and if not it does a random move
            while (!black.isEmpty()) {
                points = 0;
                blackPosition = (Square) black.pop();
                if ((currentMove.getLanding().getXC() == blackPosition.getXC()) && (currentMove.getLanding().getYC() == blackPosition.getYC())) {
                    //adds black piece points
                    if(blackPosition.getName().contains("BlackPawn")){
                      points = 2;
                    }
                    else if(blackPosition.getName().contains("BlackBishop")){
                      points = 3;
                    }
                    else if(blackPosition.getName().contains("BlackKing")){
                      points = 4;
                    }
                    else if(blackPosition.getName().contains("BlackRook")){
                      points = 5;
                    }
                    else if(blackPosition.getName().contains("BlackQueen")){
                      points = 6;
                    }
                    else if(blackPosition.getName().contains("BlackKing")){
                      points = 7;
                    }
                }
                // updates the best next move
                if (points > highestpoints) {
                    highestpoints = points;
                    bestMove = currentMove;
                }
            }
            // checks the center of the board and assigns a point to the x axis 3/4 for a move
            if ((currentMove.getStart().getYC() < currentMove.getLanding().getYC())
            && (currentMove.getLanding().getXC() == 3)&&(currentMove.getLanding().getYC() == 3)
            || (currentMove.getLanding().getXC() == 4)&& (currentMove.getLanding().getYC() == 3)
            || (currentMove.getLanding().getXC() == 3) && (currentMove.getLanding().getYC() == 4)
            || (currentMove.getLanding().getXC() == 4) && (currentMove.getLanding().getYC() == 4)) {
              points = 1;

            //update bestmove
            if (points > highestpoints) {
                    highestpoints = points;
                    bestMove = currentMove;
                }
            }
            //reloads the black squares
            black = (Stack) blackStack.clone();
        }
        // uses the  best move  available or just go random
        if (highestpoints > 0) {
            System.out.println("Agent selected next best move.");
            return bestMove;
        }
        return randomMove(whitePieces);

    }

  public Move twoLevelsDeep(Stack possibilities){
    Move selectedMove = new Move();
    return selectedMove;
  }
}
