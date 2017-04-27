package antworld.client;

import antworld.common.*;
import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by hiManshu on 4/26/2017.
 */
public class ExplorerGroup extends AntGroup{

  private PathNode goal;
  private PathFinder pathFinder;
  private int x;
  private int y;
  private ArrayList<PathNode> path = new ArrayList<PathNode>();
  private ArrayList<PathNode> emptyPath = new ArrayList<PathNode>();
  private int[][] relativePositions;
  private int currentPathSpot = 0;

    public ExplorerGroup(TeamNameEnum myTeam, PathFinder pathFinder)
    {
      int numAnts = 30;
      for (int i = 0; i < numAnts; i++)
      {
        this.antlist.add(new AntData(AntType.EXPLORER, myTeam));
        //Setting up relative positions
      }
      setUpPositions(numAnts);
      this.pathFinder = pathFinder;
    }

  private void setUpPositions(int numAnts)
  {
    relativePositions = new int[numAnts][2]; //Index 1 is ant index, index 2 is x (0) and y (1)
    for(int i = 0; i < numAnts; i++)
    {
      relativePositions[i][0] = i;
      relativePositions[i][1] = 0;
    }
  }

  public void chooseAction()
    {
      if(path != null)
      {
        if(currentPathSpot < path.size() - 1)
        {
          currentPathSpot++;
          x = path.get(currentPathSpot).getX();
          y = path.get(currentPathSpot).getY();
        }
        else
        {
          currentPathSpot = 0;
          path = emptyPath;
        }
      }
      for (int antIndex = 0; antIndex < antlist.size(); antIndex++)
      {
        AntData ant = antlist.get(antIndex);
        PathNode antNode = new PathNode(ant.gridX, ant.gridY);
        PathNode goal = new PathNode(x + relativePositions[antIndex][0], y + relativePositions[antIndex][1]);
        if(!antNode.equals(goal))
        {
          ArrayList<PathNode> antPath = pathFinder.generatePath(antNode, goal);
          dir = antNode.getDirectionTo(antPath.get(1));
          ant.action.type = AntAction.AntActionType.MOVE;
          ant.action.direction = dir;
        }
        else
        {
          ant.action.type = AntAction.AntActionType.NOOP;
        }
      }
    }

    //needs work
    //spawns in a line for now
     void spawn(int x, int y)
     {
       this.x = x;
       this.y = y;
       boolean temp = true;
       for (AntData ant : antlist)
       {
         ant.action.type = AntAction.AntActionType.EXIT_NEST;
         ant.action.x = x;
         ant.action.y = y;
         if (temp) x++;
         else y++;
         temp = !temp;
       }
    }

  /**
   * Sets the goal of the group
   * @param goal the location of the goal
   */
  public void setGoal(PathNode goal)
  {
    this.goal = goal;
  }

  /**
   * Tells the group to find a path
   */
  public void findPath()
  {
    PathNode start = new PathNode(x,y);
    path = pathFinder.generatePath(start, goal);
  }

}
