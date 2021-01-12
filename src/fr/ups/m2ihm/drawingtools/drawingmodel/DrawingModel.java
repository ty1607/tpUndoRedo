/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.drawingtools.drawingmodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import undomanager.UndoableCommand;

/**
 *
 * @author David
 */
public class DrawingModel {

    /**
     * Memory allocation for drawing.
     */
    private static final int DEFAULT_SHAPE_CAPACITY = 200;
    /**
     * Memory allocation for views.
     */
    private static final int DEFAULT_NUMBER_OF_VIEWS = 5;
    /**
     * Set of shapes within the model.
     */
    private final List<Shape> shapes;
    /**
     * Set of views (MVC convention).
     */
    private final List<DrawingView> views;

    /**
     * Prepare the handler of drawings. This class behaves as a model (MVC
     * convention).
     */
    public DrawingModel() {
        this.shapes = new ArrayList<>(DEFAULT_SHAPE_CAPACITY);
        this.views = new ArrayList<>(DEFAULT_NUMBER_OF_VIEWS);
    }

    /**
     * Register one view.
     *
     * @param view the view to register.
     */
    public final void addView(final DrawingView view) {
        views.add(view);
    }

    /**
     * Unregister one view.
     *
     * @param view the view to unregister.
     */
    public final void removeView(final DrawingView view) {
        views.remove(view);
    }

    /**
     * Add a shape to the scene.
     *
     * @param shape the to be added.
     */
    public final void addShape(final Shape shape) {
        //shapes.add(shape);
        //fireModelChanged();
        UndoableCommand command = new AddShapeCommand(this, shape);
        
    }
    
    /**
     * 
     * @param shape 
     */
    final void addShapeForReal(final Shape shape ){
        shapes.add(shape);
        fireModelChanged();
    }
    /**
     * 
     * @param shape 
     */
    final void removeShapeForReal(final Shape shape){
        shapes.remove(shape);
        fireModelChanged();
    }

    /**
     * Remove a shape to the scene.
     *
     * @param shape the to be removed.
     */
    public final void removeShape(final Shape shape) {
        shapes.remove(shape);
        fireModelChanged();
    }

    /**
     * Used to notify each of any change.
     */
    private void fireModelChanged() {
        synchronized (this) {
            views.forEach((view) -> {
                view.modelChanged(this);
            });
        }
    }

    /**
     * Provides an unmodifiable collection of shapes.
     *
     * @return the shapes
     */
    public final List<Shape> getShapes() {
        return Collections.unmodifiableList(shapes);
    }
}
