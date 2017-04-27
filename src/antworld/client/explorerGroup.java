package antworld.client;

import antworld.common.*;

/**
 * Created by hiManshu on 4/26/2017.
 */
public class explorerGroup extends AntGroup{

    public explorerGroup(TeamNameEnum myTeam){
        for (int i = 0; i < 30; i++){
            this.antlist.add(new AntData(AntType.EXPLORER, myTeam));
        }
        dir = Direction.getRandomDir();

    }

    public void chooseAction(){
        for (AntData ant : antlist){
            ant.action.type = AntAction.AntActionType.MOVE;
            ant.action.direction = dir;
        }

    }

    //needs work
    //spawns in a line for now
     void spawn(int x, int y){
        int xPos = x;
        int yPos = y;
        boolean temp = true;
         for (AntData ant : antlist){
             ant.action.type = AntAction.AntActionType.EXIT_NEST;
             ant.action.x = xPos;
             ant.action.y = yPos;
             if (temp) xPos ++;
             else yPos++;
             temp = !temp;
         }

    }
}
