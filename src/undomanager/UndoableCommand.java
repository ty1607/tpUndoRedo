/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package undomanager;

/**
 *
 * @author diversty
 */
public interface UndoableCommand {
    
    
    
    public void execute();
    public void undo();
}
