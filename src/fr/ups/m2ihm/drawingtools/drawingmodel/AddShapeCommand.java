/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.drawingtools.drawingmodel;

import undomanager.UndoableCommand;

/**
 *
 * @author diversty
 */
public class AddShapeCommand implements UndoableCommand{
    private final DrawingModel receiver;
    private final Shape shape;

    public AddShapeCommand(DrawingModel receiver, Shape shape) {
        this.receiver = receiver;
        this.shape = shape;
    }
    
    
    @Override
    public void execute(){
        
    }
    
    @Override
    public void undo(){
        
    }
    
}
