package Model;

import Model.Terrain;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.List;

public class OffsetPane extends Pane {

   int nextId;

   public interface PositionFunction {

      public Point2D getNextPosition(int index, double x, double y, double width, double height);

   }

   private static final PositionFunction DEFAULT_FUNCTION = new PositionFunction() {

      @Override
      public Point2D getNextPosition(int index, double x, double y, double width, double height) {
         return new Point2D(x, y);
      }

   };

   private final ObjectProperty<PositionFunction> hPositionFunction;
   private final ObjectProperty<PositionFunction> vPositionFunction;

   private ObjectProperty<PositionFunction> createPosProperty(String name) {
      return new SimpleObjectProperty<PositionFunction>(this, name, DEFAULT_FUNCTION) {

         @Override
         public void set(PositionFunction newValue) {
            if (newValue == null) {
               throw new IllegalArgumentException();
            } else if (get() != newValue) {
               super.set(newValue);
               requestLayout();
            }
         }

      };
   }

   public OffsetPane() {
      this.hPositionFunction = createPosProperty("hPositionFunction");
      this.vPositionFunction = createPosProperty("vPositionFunction");
      nextId = 0;
   }

   @Override
   protected void layoutChildren() {
      super.layoutChildren();
      double width = getWidth();

      List<Node> children = getManagedChildren();
      final int childSize = children.size();
      if (childSize > 0) {
         int row = 0;
         Node lastRowStart = children.get(0);
         Node lastNode = lastRowStart;
         lastRowStart.relocate(0, 0);
         PositionFunction hFunc = getHPositionFunction();
         PositionFunction vFunc = getVPositionFunction();
         int index = 1;
         int columnIndex = 0;

         while (index < childSize) {
            Node child = children.get(index);
            Bounds lastBounds = lastNode.getLayoutBounds();
            Bounds bounds = child.getLayoutBounds();
            Point2D pt = hFunc.getNextPosition(columnIndex, lastNode.getLayoutX(), lastNode.getLayoutY(), lastBounds.getWidth(), lastBounds.getHeight());

            if (pt.getX() + bounds.getWidth() > width) {
               // break row
               lastBounds = lastRowStart.getLayoutBounds();
               pt = vFunc.getNextPosition(row, lastRowStart.getLayoutX(), lastRowStart.getLayoutY(), lastBounds.getWidth(), lastBounds.getHeight());
               child.relocate(pt.getX(), pt.getY());

               lastRowStart = child;
               row++;
               columnIndex = 0;
            } else {
               child.relocate(pt.getX(), pt.getY());
               columnIndex++;
            }

            lastNode = child;

            index++;
         }
      }
   }

   public final PositionFunction getHPositionFunction() {
      return this.hPositionFunction.get();
   }

   public final void setHPositionFunction(PositionFunction value) {
      this.hPositionFunction.set(value);
   }

   public final ObjectProperty<PositionFunction> hPositionFunctionProperty() {
      return this.hPositionFunction;
   }

   public final PositionFunction getVPositionFunction() {
      return this.vPositionFunction.get();
   }

   public final void setVPositionFunction(PositionFunction value) {
      this.vPositionFunction.set(value);
   }

   public final ObjectProperty<PositionFunction> vPositionFunctionProperty() {
      return this.vPositionFunction;
   }

   public void add(Terrain o)
   {
      //if( !(o instanceof Empty))
      //{
         o.setTerrainId(nextId);
         nextId++;
      //}
      getChildren().add(o);

   }
}