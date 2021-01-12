/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.drawingtools.ui;

import fr.ups.m2ihm.drawingtools.drawingmodel.DrawingModel;
import fr.ups.m2ihm.drawingtools.drawingmodel.DrawingView;
import fr.ups.m2ihm.drawingtools.drawingmodel.Shape;
import fr.ups.m2ihm.drawingtools.toolsmodel.AbstractTool;
import fr.ups.m2ihm.drawingtools.toolsmodel.DrawingEvent;
import fr.ups.m2ihm.drawingtools.toolsmodel.DrawingToolView;
import fr.ups.m2ihm.drawingtools.toolsmodel.ToolManager;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.swing.JToggleButton;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author David
 */
public class DrawingToolApplication extends javax.swing.JFrame {

    /**
     * The model that holds the drawings to display.
     */
    private final DrawingModel model;
    /**
     * The model of drawing tool used as a controller for the DrawingModel
     * instance.
     */
    private final ToolManager tool;
    /**
     * Default color for shape drawing.
     */
    private static final Color DEFAULT_SHAPE_COLOR = Color.BLACK;
    /**
     * Default color for ghost drawing.
     */
    private static final Color DEFAULT_GHOST_COLOR = Color.RED;
    /**
     * Default stroke for shape drawing.
     */
    private static final Stroke DEFAULT_SHAPE_STROKE
            = new BasicStroke(
                    2.0f,
                    BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_MITER,
                    10.0f);
    /**
     * Default stroke for ghost drawing.
     */
    private static final Stroke DEFAULT_GHOST_STROKE
            = new BasicStroke(
                    2.0f,
                    BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_MITER,
                    10.0f,
                    new float[]{10.0f, 3.0f}, 0.0f);
    /**
     * Stores the corresponding selecting toggle button for each possible tool.
     */
    private final Map<ToolManager.Tool, JToggleButton> toolSelectors;

    /**
     * Creates new form DrawingToolApplication.
     */
    public DrawingToolApplication() {
        initComponents();
        model = new DrawingModel();
        model.addView(new DrawingViewImpl());
        tool = new ToolManager(model);
        tool.addDrawingController(DrawingControllerImpl.getInstance());
        DrawingControllerImpl.getInstance().setTool(tool);
        tool.addDrawingToolView(new DrawingToolViewImpl());

        drawingZone.addMouseListener(DrawingControllerImpl.getInstance());
        drawingZone.addMouseMotionListener(DrawingControllerImpl.getInstance());
        drawingZone.addKeyListener(DrawingControllerImpl.getInstance());
        drawingZone.setFocusable(true);
        drawingZone.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(final MouseEvent e) {
                drawingZone.requestFocus();
            }

        });

        toolSelectors = new HashMap<>(ToolManager.Tool.values().length);
        toolSelectors.put(ToolManager.Tool.LINE, tglLine);
        toolSelectors.put(ToolManager.Tool.CIRCLE, tglOval);
        toolSelectors.get(tool.getCurrentToolName()).setSelected(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolsButtonGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        tglLine = new javax.swing.JToggleButton();
        tglOval = new javax.swing.JToggleButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        undoMenuItem = new javax.swing.JMenuItem();
        redoMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("The Most Useless Drawing Tool");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tool selector"));
        jPanel2.setLayout(new java.awt.GridLayout(0, 1, 0, 20));

        toolsButtonGroup.add(tglLine);
        tglLine.setText("Line");
        tglLine.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tglLineStateChanged(evt);
            }
        });
        jPanel2.add(tglLine);

        toolsButtonGroup.add(tglOval);
        tglOval.setText("Oval");
        tglOval.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tglOvalStateChanged(evt);
            }
        });
        jPanel2.add(tglOval);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
        );

        jMenu2.setText("Edit");

        undoMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        undoMenuItem.setLabel("Undo");
        undoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(undoMenuItem);

        redoMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        redoMenuItem.setLabel("Redo");
        redoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redoMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(redoMenuItem);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(464, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tglLineStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tglLineStateChanged
        tool.selectTool(ToolManager.Tool.LINE);
    }//GEN-LAST:event_tglLineStateChanged

    private void tglOvalStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tglOvalStateChanged
        tool.selectTool(ToolManager.Tool.CIRCLE);
    }//GEN-LAST:event_tglOvalStateChanged

    private void undoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_undoMenuItemActionPerformed

    private void redoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redoMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_redoMenuItemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JMenuItem redoMenuItem;
    private javax.swing.JToggleButton tglLine;
    private javax.swing.JToggleButton tglOval;
    private javax.swing.ButtonGroup toolsButtonGroup;
    private javax.swing.JMenuItem undoMenuItem;
    // End of variables declaration//GEN-END:variables

    /**
     * Utility class that aims at controlling the Tool model. This class is a
     * Singleton (Design Pattern).
     */
    private static final class DrawingControllerImpl
            extends MouseInputAdapter
            implements KeyListener,
            fr.ups.m2ihm.drawingtools.toolsmodel.DrawingController {

        /**
         * Class attribute that holds the singleton instance.
         */
        private static final DrawingControllerImpl DEFAULT_INSTANCE;
        /**
         * Collections that maps event names and their enabling.
         */
        private final Map<DrawingEvent, Boolean> eventEnabling;
        /**
         * The tool controlled by the DrawingController instance.
         */
        private AbstractTool tool;

        static {
            DEFAULT_INSTANCE = new DrawingControllerImpl();
        }

        /**
         * Private constructor for utility class.
         */
        private DrawingControllerImpl() {
            eventEnabling = new HashMap<>(DrawingEvent.values().length);
        }

        /**
         * Get the controlled tool.
         *
         * @return the tool
         */
        public AbstractTool getTool() {
            return tool;
        }

        /**
         * Set the controlled tool.
         * <p>
         * Stores the enabling of each event.
         *
         * @param aTool the tool
         */
        public void setTool(final AbstractTool aTool) {
            this.tool = aTool;
            for (DrawingEvent event : DrawingEvent.values()) {
                eventEnabling.put(event, tool.isEventEnabled(event));
            }
        }

        /**
         * Provides the single instance of this class.
         *
         * @return the Drawing Controller.
         */
        public static DrawingControllerImpl getInstance() {
            return DEFAULT_INSTANCE;
        }

        @Override
        public void mouseReleased(final MouseEvent e) {
            if (tool.isEventEnabled(DrawingEvent.BEGIN_DRAW)) {
                tool.acceptEvent(DrawingEvent.BEGIN_DRAW, e.getPoint());
            } else if (tool.isEventEnabled(DrawingEvent.END_DRAW)) {
                tool.acceptEvent(DrawingEvent.END_DRAW, e.getPoint());
            }
        }

        @Override
        public void mouseMoved(final MouseEvent e) {
            if (tool.isEventEnabled(DrawingEvent.DRAW)) {
                tool.acceptEvent(DrawingEvent.DRAW, e.getPoint());
            }
        }

        @Override
        public void keyTyped(final KeyEvent e) {
            //Do nothing.
        }

        @Override
        public void keyPressed(final KeyEvent e) {
            if (Objects.equals(KeyEvent.VK_ESCAPE, e.getKeyCode())) {
                //BREAK key used.
                if (tool.isEventEnabled(DrawingEvent.CANCEL_DRAW)) {
                    tool.acceptEvent(DrawingEvent.CANCEL_DRAW, null);
                }
            }
        }

        @Override
        public void keyReleased(final KeyEvent e) {
            //Do nothing.
        }

        @Override
        public void eventEnablingChanged(
                final DrawingEvent event,
                final Boolean theEnabling) {
            this.eventEnabling.put(event, theEnabling);
        }
    }

    /**
     * Default implementation of the interface DrawingView.
     */
    private final class DrawingViewImpl implements DrawingView {

        @Override
        public void modelChanged(final DrawingModel aModel) {
            drawingZone.clearAll();
            aModel.getShapes().forEach((shape) -> {
                drawingZone.addShape(
                        shape,
                        DEFAULT_SHAPE_COLOR,
                        DEFAULT_SHAPE_STROKE);
            });
        }
    }

    /**
     * Default implementation of the interface DrawingToolView.
     */
    private final class DrawingToolViewImpl implements DrawingToolView {

        /**
         * Stores the current ghost to allow its modification or removal.
         */
        private Shape currentGhostShape = null;

        @Override
        public void ghostCreated(final Shape shape) {
            assert (Objects.nonNull(shape));
            if (Objects.nonNull(currentGhostShape)) {
                drawingZone.removeShape(
                        currentGhostShape,
                        DEFAULT_GHOST_COLOR,
                        DEFAULT_GHOST_STROKE);
            }
            currentGhostShape = shape;
            drawingZone.addShape(currentGhostShape,
                    DEFAULT_GHOST_COLOR,
                    DEFAULT_GHOST_STROKE);
        }

        @Override
        public void ghostChanged(final Shape shape) {
            assert (Objects.nonNull(shape));
            if (Objects.nonNull(currentGhostShape)) {
                drawingZone.removeShape(currentGhostShape,
                        DEFAULT_GHOST_COLOR,
                        DEFAULT_GHOST_STROKE);
            }
            currentGhostShape = shape;
            drawingZone.addShape(currentGhostShape,
                    DEFAULT_GHOST_COLOR,
                    DEFAULT_GHOST_STROKE);
        }

        @Override
        public void ghostRemoved(final Shape shape) {
            if (Objects.nonNull(currentGhostShape)) {
                drawingZone.removeShape(currentGhostShape,
                        DEFAULT_GHOST_COLOR,
                        DEFAULT_GHOST_STROKE);
            }
            currentGhostShape = null;
        }
    }
}
