/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package undomanager;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diversty
 */
public class UndoManager {
    
    private enum Events {
        Idle,
        UndoOnly,
        UndoRedoable,
        RedoOnly
    }
    
    private final List<UndoableCommand> availableUndo;
    private final List<UndoableCommand> availableRedo;
    
    private Events event;
    
    public UndoManager() {
        this.availableUndo = new ArrayList<>();
        this.availableRedo = new ArrayList<>();
        this.event = Events.Idle;
    }
    
    public void registerCommand(UndoableCommand command){
        switch (event){
            case Idle : 
                action1(command);
                event = Events.UndoOnly;
                break;
            case UndoOnly : 
                action2(command);
                event = Events.UndoOnly;
                break;
            case RedoOnly : 
                action1(command);
                event = Events.UndoOnly;
                break;
            case UndoRedoable : 
                action2(command);
                event = Events.UndoOnly;
                break;
        }
    }
    
    public void undo(){
        switch (event){
            case Idle : 
                //impossible
                break;
            case UndoOnly : 
                if (availableUndo.size() > 1){
                    action5();
                    event = Events.UndoRedoable;
                }
                else if (availableUndo.size() == 1){
                    action5();
                    event = Events.RedoOnly;
                }
                break;
            case RedoOnly : 
                //impossible
                break;
            case UndoRedoable : 
                if (availableUndo.size() > 1){
                    action5();
                    event = Events.UndoRedoable;
                }
                else if (availableUndo.size() == 1){
                    action5();
                    event = Events.RedoOnly;
                }
                break;
        }
    }
    
    public void undoMultiple(int min, int max){
        switch (event){
            case Idle : 
                //impossible
                break;
            case UndoOnly : 
                if (availableUndo.size() > 1){
                    
                    if (max - min < availableUndo.size()){
                        action7( min, max);
                        event = Events.UndoRedoable;
                    } else if (max-min == availableUndo.size()){
                        action6();
                        event = Events.RedoOnly;
                    }
                }
                else if (availableUndo.size() == 1){
                    action5();
                    event = Events.RedoOnly;
                }
                break;
            case RedoOnly : 
                //impossible
                break;
            case UndoRedoable : 
                if (availableUndo.size() > 1){
                   if (max - min < availableUndo.size()){
                        action7( min, max);
                        event = Events.UndoRedoable;
                    } else if (max-min == availableUndo.size()){
                        action6();
                        event = Events.RedoOnly;
                    }
                }
                else if (availableUndo.size() == 1){
                    action5();
                    event = Events.RedoOnly;
                }
                break;
        }
    }
    
    public void redo(){
        switch (event){
            case Idle : 
                //impossible
                break;
            case UndoOnly : 
                //impossible
                break;
            case RedoOnly : 
                if (availableRedo.size() > 1){
                    action4();
                    event = Events.UndoRedoable;
                }
                else if (availableRedo.size() == 1){
                    action3();
                    event = Events.UndoOnly;
                }
                break;
            case UndoRedoable : 
                if (availableRedo.size() > 1){
                    action4();
                    event = Events.UndoRedoable;
                }
                else if (availableRedo.size() == 1){
                    action3();
                    event = Events.UndoOnly;
                }
                break;
        }
    }
    
    public void action1(UndoableCommand command){
        availableUndo.add(command);
        availableRedo.clear();
    }
    
    public void action2(UndoableCommand command){
        availableUndo.add(command);
        availableRedo.clear();
    }
    
    public void action3(){
        UndoableCommand command = availableRedo.remove(availableRedo.size()-1);
        command.execute();
        availableUndo.add(command);
    }
    
    public void action4(){
        UndoableCommand command = availableRedo.remove(availableRedo.size()-1);
        command.execute();
        availableUndo.add(command);
    }
    
    public void action5(){
        UndoableCommand command = availableUndo.remove(availableUndo.size()-1);
        command.undo();
        availableRedo.add(command);
    }
    
    public void action6(){
        
        //Faire boucle qui boucle sur les valeurs entre max et min pour supprimer tous les elements
        for (int i = availableUndo.size()-1; i <= 0; i-- ){
            UndoableCommand command = availableUndo.remove(i);
            command.undo();
            availableRedo.add(command);
            
        }
    }
    
    public void action7(int min, int max){
        
        //Faire boucle qui boucle sur les valeurs entre max et min pour supprimer tous les elements
        for (int i = max; i >= min; i-- ){
            
        UndoableCommand command = availableUndo.remove(i-1);
        command.undo();
        availableRedo.add(command);
        }
    }
    
}
