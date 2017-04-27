package antworld.client;

import antworld.common.Direction;

/**
 * The Class PathNode represents a single node for A* pathfinding
 * @Author: Kirtus Leyba
 */
class PathNode
{
  private int x;
  private int y;
  private double h;
  private double g;
  private double f;
  private PathNode parent;

  /**
   * Pathnode constructo
   * @param x the x coordinate of the pathnode
   * @param y the y coordinate of the pathnode
   */
  PathNode(int x, int y)
  {
    this.x = x;
    this.y = y;
    this.h = 0;
    this.g = 0;
    this.f = 0;
  }

  //Below are the standard setters and getters for this structure
  //@Kirtus L
  public double getH()
  {
    return h;
  }

  public void setH(double h)
  {
    this.h = h;
  }

  public double getG()
  {
    return g;
  }

  public void setG(double g)
  {
    this.g = g;
  }

  //The f cost is g + h
  //@Kirtus L
  public double calcF()
  {
    f = g + h;
    return f;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public double getF() {
    return f;
  }

  public void setParent(PathNode parent)
  {
    this.parent = parent;
  }
  public PathNode getParent()
  {
    return parent;
  }

  public Direction getDirectionTo(PathNode pathNode)
  {
    if(x > pathNode.getX())
    {
      if(y == pathNode.getY())
      {
        return Direction.EAST;
      }
      else if(y < pathNode.getY())
      {
        return Direction.SOUTHEAST;
      }
      else
      {
        return Direction.NORTHEAST;
      }
    }
    else if(x < pathNode.getX())
    {
      if(y == pathNode.getY())
      {
        return Direction.WEST;
      }
      else if(y < pathNode.getY())
      {
        return Direction.SOUTHWEST;
      }
      else
      {
        return Direction.NORTHWEST;
      }
    }
    else if(x == pathNode.getY())
    {
      if(y > pathNode.getY())
      {
        return Direction.NORTH;
      }
      else
      {
        return Direction.SOUTH;
      }
    }
    return Direction.getRandomDir();
  }
}
