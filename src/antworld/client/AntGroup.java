package antworld.client;


import antworld.common.AntType;
import antworld.common.PacketToClient;
import antworld.common.PacketToServer;
import antworld.common.AntAction;
import antworld.common.AntAction.AntState;
import antworld.common.AntData;
import antworld.common.Constants;
import antworld.common.Direction;
import antworld.common.NestNameEnum;
import antworld.common.TeamNameEnum;
import antworld.common.AntAction.AntActionType;

import java.util.ArrayList;


/**
 * Created by hIM on 4/24/2017.
 */
public class AntGroup
{

  int count;
  AntType type;
  ArrayList<AntData> antlist = new ArrayList<>();

  //generate the number of ants needed for this group
  public AntGroup (AntType type, TeamNameEnum myTeam){
    this.type = AntType.WORKER;
    if (type == AntType.WORKER){
      AntData ant1 = new AntData(type, myTeam);

    }
  }

  public ArrayList<AntData> getAntList (){
    return antlist;
  }

  //the arrangement for each ant can be define with it spawn position
  void spawn(int x, int y){
    if (this.type == AntType.WORKER) {
      for (AntData ant : antlist) {
        AntAction action = new AntAction(AntAction.AntActionType.EXIT_NEST);
        action.x = x;
        action.y = y;
      }
    }
  }




}
