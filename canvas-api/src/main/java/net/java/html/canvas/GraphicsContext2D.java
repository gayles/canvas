/*
 * #%L
 * DukeScript Canvas API - a library from the "DukeScript Canvas API" project.
 * Visit http://dukescript.com for support and commercial license.
 * %%
 * Copyright (C) 2015 Eppleton IT Consulting
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */
package net.java.html.canvas;

import java.util.Map;
import net.java.html.canvas.Style.Color;
import net.java.html.canvas.Style.LinearGradient;
import net.java.html.canvas.Style.Pattern;
import net.java.html.canvas.Style.RadialGradient;
import net.java.html.canvas.spi.GraphicsEnvironment;
import org.apidesign.html.canvas.impl.CnvsAccssr;

/**
 * A 2D Graphics Context similar to HTML5 or JavaFX GraphicsContext. Use this to
 * paint on your Canvas. To get a GraphicsContext2D call
 * For HTML 5 :
 * <pre>
 * {@code
 * GraphicsContext2D gc = HTML5Graphics.getOrCreate("canvas");
 * }
 * </pre> For JavaFX:
 * <pre>
 * {@code
 * GraphicsContext2D gc = JavaFXGraphics.getOrCreate("canvas");
 *  }
 * </pre>
 *
 * @author antonepple
 */
public abstract class GraphicsContext2D {

    static {
        CnvsAccssr cnvsAccssr = new CnvsAccssr() {
            @Override
            public <C> GraphicsContext2D create(GraphicsEnvironment<C> environment, C c) {
                return new GraphicsContext2DImpl<C>(environment, c);
            }
        };
    }

    /**
     * only one subclass GraphicsContextImpl
     */
    GraphicsContext2D() {
    }

    /**
     * Adds path elements to the current path to make an arc.
     *
     * @param centerX the center x position of the arc.
     * @param centerY the center y position of the arc.
     * @param startAngle the startAngle of the arc
     * @param radius the radius of the arc.
     * @param endAngle the endAngle of the arc
     * @param ccw the direction of the arc (counterclockwise)
     */
    public abstract void arc(double centerX,
            double centerY,
            double startAngle,
            double radius,
            double endAngle,
            boolean ccw);

    /**
     * Adds segments to the current path to make an arc.
     *
     * @param x1 the X coordinate of the first point of the arc.
     * @param y1 the Y coordinate of the first point of the arc.
     * @param x2 the X coordinate of the second point of the arc.
     * @param y2 the Y coordinate of the second point of the arc.
     * @param radius the radius of the arc in the range {0.0-positive infinity}.
     */
    public abstract void arcTo(double x1,
            double y1,
            double x2,
            double y2,
            double radius);

    /**
     * Returns true if the the given x,y point is inside the path.
     *
     * @param x the X coordinate to use for the check.
     * @param y the Y coordinate to use for the check.
     * @return true if the point given is inside the path, false otherwise.
     */
    public abstract boolean isPointInPath(double x, double y);

    /**
     * Fills the path with the current fill paint.
     */
    public abstract void fill();

    /**
     * Strokes the path with the current stroke paint.
     */
    public abstract void stroke();

    /**
     * Starts a Path
     */
    public abstract void beginPath();

    /**
     * Closes the path.
     */
    public abstract void closePath();

    /**
     * Clips using the current path
     */
    public abstract void clip();

    /**
     * Issues a move command for the current path to the given x,y coordinate.
     *
     * @param x the X position for the move to command.
     * @param y the Y position for the move to command.
     */
    public abstract void moveTo(double x, double y);

    /**
     * Adds segments to the current path to make a line at the given x,y
     * coordinate.
     *
     * @param x the X coordinate of the ending point of the line.
     * @param y the Y coordinate of the ending point of the line.
     */
    public abstract void lineTo(double x, double y);

    /**
     * Adds segments to the current path to make a quadratic curve.
     *
     * @param cpx the X coordinate of the control point
     * @param cpy the Y coordinate of the control point
     * @param x the X coordinate of the end point
     * @param y the Y coordinate of the end point
     */
    public abstract void quadraticCurveTo(double cpx, double cpy, double x, double y);

    /**
     * Adds segments to the current path to make a cubic bezier curve.
     *
     * @param cp1x the X coordinate of first bezier control point.
     * @param cp1y the Y coordinate of the first bezier control point.
     * @param cp2x the X coordinate of the second bezier control point.
     * @param cp2y the Y coordinate of the second bezier control point.
     * @param x the X coordinate of the end point.
     * @param y the Y coordinate of the end point.
     */
    public abstract void bezierCurveTo(double cp1x, double cp1y, double cp2x, double cp2y, double x, double y);

    /**
     * Fills a rectangle using the current fill paint.
     *
     * @param x the X position of the upper left corner of the rectangle.
     * @param y the Y position of the upper left corner of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     */
    public abstract void fillRect(double x, double y, double width, double height);

    /**
     * Strokes a rectangle using the current stroke paint.
     *
     * @param x the X position of the upper left corner of the rectangle.
     * @param y the Y position of the upper left corner of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     */
    public abstract void strokeRect(double x, double y, double width, double height);

    /**
     * Clears a portion of the canvas with a transparent color value.
     *
     * @param x X position of the upper left corner of the rectangle.
     * @param y Y position of the upper left corner of the rectangle.
     * @param width width of the rectangle.
     * @param height height of the rectangle.
     */
    public abstract void clearRect(double x, double y, double width, double height);

    /**
     * Clears a portion of the canvas with a transparent color value.
     *
     * @param x X position of the upper left corner of the rectangle.
     * @param y Y position of the upper left corner of the rectangle.
     * @param width width of the rectangle.
     * @param height height of the rectangle.
     */
    public abstract void rect(double x, double y, double width, double height);

    /**
     * Saves the following attributes onto a stack.
     * <ul>
     * <li>Global Alpha</li>
     * <li>Global Blend Operation</li>
     * <li>Transform</li>
     * <li>Fill Paint</li>
     * <li>Stroke Paint</li>
     * <li>Line Width</li>
     * <li>Line Cap</li>
     * <li>Line Join</li>
     * <li>Miter Limit</li>
     * <li>Number of Clip Paths</li>
     * <li>Font</li>
     * <li>Text Align</li>
     * <li>Text Baseline</li>
     * <li>Effect</li>
     * <li>Fill Rule</li>
     * </ul>
     * This method does NOT alter the current state in any way. Also, not that
     * the current path is not saved.
     */
    public abstract void save();

    /**
     * Pops the state off of the stack, setting the following attributes to
     * their value at the time when that state was pushed onto the stack. If the
     * stack is empty then nothing is changed.
     *
     * <ul>
     * <li>Global Alpha</li>
     * <li>Global Blend Operation</li>
     * <li>Transform</li>
     * <li>Fill Paint</li>
     * <li>Stroke Paint</li>
     * <li>Line Width</li>
     * <li>Line Cap</li>
     * <li>Line Join</li>
     * <li>Miter Limit</li>
     * <li>Number of Clip Paths</li>
     * <li>Font</li>
     * <li>Text Align</li>
     * <li>Text Baseline</li>
     * <li>Effect</li>
     * <li>Fill Rule</li>
     * </ul>
     */
    public abstract void restore();

    /**
     * Rotates the current transform in degrees.
     *
     * @param angle value in degrees to rotate the current transform.
     */
    public abstract void rotate(double angle);

    /**
     * Concatenates the input with the current transform.
     *
     * @param mxx - the X coordinate scaling element of the 3x4 matrix
     * @param myx - the Y coordinate shearing element of the 3x4 matrix
     * @param mxy - the X coordinate shearing element of the 3x4 matrix
     * @param myy - the Y coordinate scaling element of the 3x4 matrix
     * @param mxt - the X coordinate translation element of the 3x4 matrix
     * @param myt - the Y coordinate translation element of the 3x4 matrix
     */
    public abstract void transform(double mxx, double myx, double mxy, double myy, double mxt, double myt);

    /**
     * Concatenates the input with the current transform.
     *
     * @param mxx - the X coordinate scaling element of the 3x4 matrix
     * @param myx - the Y coordinate shearing element of the 3x4 matrix
     * @param mxy - the X coordinate shearing element of the 3x4 matrix
     * @param myy - the Y coordinate scaling element of the 3x4 matrix
     * @param mxt - the X coordinate translation element of the 3x4 matrix
     * @param myt - the Y coordinate translation element of the 3x4 matrix
     */
    public abstract void setTransform(double mxx, double myx, double mxy, double myy, double mxt, double myt);

    /**
     * Translates the current transform by x, y.
     *
     * @param x value to translate along the x axis.
     * @param y value to translate along the y axis.
     */
    public abstract void translate(double x, double y);

    /**
     * Scales the current transform by x, y.
     *
     * @param x value to scale in the x axis.
     * @param y value to scale in the y axis.
     */
    public abstract void scale(double x, double y);

    /**
     * Draws an image at the given x, y position using the width and height of
     * the given image.
     *
     * @param image the image to be drawn.
     * @param x the X coordinate on the destination for the upper left of the
     * image.
     * @param y the Y coordinate on the destination for the upper left of the
     * image.
     */
    public abstract void drawImage(Image image, double x, double y);

    /**
     * Draws an image into the given destination rectangle of the canvas. The
     * Image is scaled to fit into the destination rectagnle.
     *
     * @param image the image to be drawn.
     * @param x the X coordinate on the destination for the upper left of the
     * image.
     * @param y the Y coordinate on the destination for the upper left of the
     * image.
     * @param width the width of the destination rectangle.
     * @param height the height of the destination rectangle.
     */
    public abstract void drawImage(Image image, double x, double y, double width, double height);

    /**
     * Draws the current source rectangle of the given image to the given
     * destination rectangle of the Canvas.
     *
     * @param image the image to be drawn.
     * @param sx the source rectangle's X coordinate position.
     * @param sy the source rectangle's Y coordinate position.
     * @param sw the source rectangle's width.
     * @param sh the source rectangle's height.
     * @param dx the destination rectangle's X coordinate position.
     * @param dy the destination rectangle's Y coordinate position.
     * @param dw the destination rectangle's width.
     * @param dh the destination rectangle's height.
     */
    public abstract void drawImage(Image image, double sx, double sy, double sw, double sh, double dx, double dy, double dw, double dh);

    /**
     * Merges two images drawing one on top of the other and returning the
     * result.
     *
     * @param a the lower Image
     * @param b the upper Image
     * @return the merged Image
     */
    public abstract Image merge(Image a, Image b);

//    public void setShadowColor(String color) {
//        graphicsEnvironmentImpl.setShadowColor(color);
//    }
//
//    public void setShadowBlur(double blur) {
//        graphicsEnvironmentImpl.setShadowBlur(blur);
//    }
//
//    public void setShadowOffsetX(double x) {
//        graphicsEnvironmentImpl.setShadowOffsetX(x);
//    }
//
//    public void setShadowOffsetY(double y) {
//        graphicsEnvironmentImpl.setShadowOffsetY(y);
//    }
//
//    public String getShadowColor() {
//        return graphicsEnvironmentImpl.getShadowColor();
//    }
//
//    public double getShadowBlur() {
//        return graphicsEnvironmentImpl.getShadowBlur();
//    }
//
//    public double getShadowOffsetX() {
//        return graphicsEnvironmentImpl.getShadowOffsetX();
//    }
//
//    public double getShadowOffsetY() {
//        return graphicsEnvironmentImpl.getShadowOffsetY();
//    }
    /**
     * Gets the current stroke line cap attribute.
     *
     * @return a value of butt, round, or square.
     */
    public abstract String getLineCap();

    /**
     * Sets the current stroke line cap attribute.
     *
     * @param style a value of butt,round,square.
     */
    public abstract void setLineCap(String style);

    /**
     * Gets the current stroke line join attribute.
     *
     * @return a value of miter, bevel, or round.
     */
    public abstract String getLineJoin();

    /**
     * Sets the current stroke line join attribute.
     *
     * @param style a value of miter, bevel, or round.
     */
    public abstract void setLineJoin(String style);

    /**
     * Gets the current line width attribute.
     *
     * @return value between 0 and infinity, with any other value being ignored
     * and leaving the value unchanged.
     *
     */
    public abstract double getLineWidth();

    /**
     * Sets the current line width attribute.
     *
     * @param width value between 0 and infinity, with any other value being
     * ignored and leaving the value unchanged.
     *
     */
    public abstract void setLineWidth(double width);

    /**
     * Gets the current miter limit attribute.
     *
     * @return limit value between 0 and positive infinity with any other value
     * being ignored and leaving the value unchanged.
     */
    public abstract double getMiterLimit();

    /**
     * Sets the current miter limit attribute.
     *
     * @param limit miter limit value between 0 and positive infinity with any
     * other value being ignored and leaving the value unchanged.
     */
    public abstract void setMiterLimit(double limit);

    /**
     * Sets the fill style. Will be used when rendering something, e.g. calling
     * one of the fillText Methods.
     *
     * @param style the desired fill style
     */
    public abstract void setFillStyle(Style style);

    /**
     * get the current font
     *
     * @return current Font. of the fillText Methods.
     */
    public abstract String getFont();

    /**
     * Set the Font. Will be used when rendering Text, e.g. by calling one of
     * the fillText Methods.
     *
     * @param font the desired font as a String
     */
    public abstract void setFont(String font);

    /**
     * sets the Style of the Stroke.
     *
     * @param style the style to use
     */
    public abstract void setStrokeStyle(Style style);

    /**
     * Gets the current TextAlignment attribute
     *
     * @return TextAlignment with values of left, center, right, or justify.
     */
    public abstract String getTextAlign();

    /**
     * Defines horizontal text alignment, relative to the text {@code x} origin.
     * <p>
     * Let horizontal bounds represent the logical width of a single line of
     * text. Where each line of text has a separate horizontal bounds.
     * <p>
     * Then TextAlignment is specified as:
     * <ul>
     * <li>left: the left edge of the horizontal bounds will be at {@code x}.
     * <li>center: the center, halfway between left and right edge, of the
     * horizontal bounds will be at {@code x}.
     * <li>right: the right edge of the horizontal bounds will be at {@code x}.
     * </ul>
     * <p>
     *
     * Note: Canvas does not support line wrapping, therefore the text alignment
     * Justify is identical to left aligned text.
     * <p>
     *
     * @param textAlign with values of left, center, right.
     */
    public abstract void setTextAlign(String textAlign);

    /**
     * Gets the current Text Baseline attribute.
     *
     * @return baseline with values of top, center, baseline, or bottom
     */
    public abstract String getTextBaseline();

    /**
     * Sets the current Text Baseline attribute.
     *
     * @param textbaseline with values of top, center, baseline, or bottom
     */
    public abstract void setTextBaseline(String textbaseline);

    /**
     * Renders the indicated String with current fill. default is black.
     *
     * @param text the text to stroke
     * @param x x coordinate of start position
     * @param y y coordinate of start position
     */
    public abstract void fillText(String text, double x, double y);

    /**
     * Renders the indicated String with current fill. default is black.
     *
     * @param text the text to stroke
     * @param x x coordinate of start position
     * @param y y coordinate of start position
     * @param maxWidth maximum width of text
     */
    public abstract void fillText(String text, double x, double y, double maxWidth);

    /**
     * Check the length of a text before writing it to the Canvas. Takes into
     * account the current Font.
     *
     * @param text the text to measure
     * @return the length in pixels
     */
    public abstract Dimension measureText(String text);

    /**
     * Renders the indicated String (with no fill)
     *
     * @param text the text to stroke
     * @param x x coordinate of start position
     * @param y y coordinate of start position
     */
    public abstract void strokeText(String text, double x, double y);

    /**
     * Renders the indicated String (with no fill)
     *
     * @param text the text to stroke
     * @param x x coordinate of start position
     * @param y y coordinate of start position
     * @param maxWidth maximum width of text
     */
    public abstract void strokeText(String text, double x, double y, double maxWidth);

    /**
     * Get a pixel array that you can manipulate, e.g. apply effects /
     * transparency
     *
     * @param x width 
     * @param y height
     * @return a PixelMap a new ImageData Objesct with the specified Dimensions
     */
    public abstract ImageData createPixelMap(double x, double y);

    /**
     * Create a new ImageData object with the same dimensions as the object
     * specified by imageData (this does not copy the image data)
     *
     * @param pixelMap the ImageData to copy the Dimensions from
     * @return a new ImageData Objesct with the specified Dimensions
     */
    public abstract ImageData createPixelMap(ImageData pixelMap);

    /**
     * Get the pixels for a region of your GraphicsContext2D
     *
     * @param x start x coordinate
     * @param y start y coordinate
     * @param width width
     * @param height height
     * @return An ImageData Object containing a snapshot of the specified Region.
     */
    public abstract ImageData getSnapshot(double x, double y, double width, double height);

    /**
     * Render an ImageData Object at the specified position
     *
     * @param pixelMap the Pixel array
     * @param x start x coordinate
     * @param y start y coordinate
     */
    public abstract void drawPixelMap(ImageData pixelMap, double x, double y);

    /*
     * Render an ImageData Object at the specified position
     * @param pixelMap  the Pixel array to draw
     * @param x start x coordinate
     * @param y start y coordinate
     * @param dirtyx The horizontal (x) value, in pixels, where to place the image on the canvas
     * @param dirtyy The vertical (y) value, in pixels, where to place the image on the canvas
     * @param dirtywidth The width to use to draw the image on the canvas
     * @param dirtyheight The height to use to draw the image on the canvas
     */
//    public abstract void drawPixelMap(ImageData pixelMap, double x, double y, double dirtyx, double dirtyy, double dirtywidth, double dirtyheight);
    /**
     * Sets the global alpha of the current state.
     *
     * @param alpha value in the range {@code 0.0-1.0}. The value is clamped if
     * it is out of range.
     */
    public abstract void setGlobalAlpha(double alpha);

    /**
     * Gets the current global alpha.
     *
     * @return the current global alpha.
     */
    public abstract double getGlobalAlpha();

    /**
     * Sets the global blend mode.
     *
     * @param operation the BlendMode that will be set.
     */
    public abstract void setGlobalCompositeOperation(String operation);

    /**
     * Gets the global blend mode.
     *
     * @return the global BlendMode of the current state.
     */
    public abstract String getGlobalCompositeOperation();

    /**
     * Create a LinearGradient to use in Canvas.
     *
     * @param x0 x coordinate of start point
     * @param y0 y coordinate of start point
     * @param x1 x coordinate of end point
     * @param y1 y coordinate of end point
     * @param stops The Stop(s) definig this Gradient
     * @return the gradient
     */
    public abstract LinearGradient createLinearGradient(double x0, double y0, double x1, double y1, Map<Double, String> stops);

    /**
     * Create an Image Pattern from a source Image and a repeat style. Possible
     * Styles are repeat, repeat-x, repeat-y, or no-repeat. defaults to repeat
     *
     * @param image the Image
     * @param repeat the repeat style
     * @return the Pattern
     */
    public abstract Pattern createPattern(Image image, String repeat);

    /**
     * Create a RadialGradient
     *
     * @param x0 x Coordinate of starting circle
     * @param y0 y Coordinate of starting circle
     * @param r0 radius of starting circle
     * @param x1 x coordinate of ending circle
     * @param y1 y coordinate of ending circle
     * @param r1 radius of ending circle
     * @param stops the Stops defining this Gradient
     * @return the Gradient
     */
    public abstract RadialGradient createRadialGradient(double x0, double y0, double r0, double x1, double y1, double r1, Map<Double, String> stops);

    /**
     * Convert this String Representation of a Color to a Color Object.
     *
     * @param webColor the String describing the color, e.g. #ff0000 for red. 
     * @return The Color represented by the input
     */
    public abstract Color getWebColor(String webColor);

    /**
     * Get the height of this GraphicsContext2D (which should be the same as the
     * enclosing canvas height)
     *
     * @return the height of this GraphicsContext2D
     */
    public abstract int getHeight();

    /**
     * Get the width of this GraphicsContext2D (which should be the same as the
     * enclosing canvas height)
     *
     * @return the width of this GraphicsContext2D
     */
    public abstract int getWidth();

//    public void setHeight(int height) {
//        graphicsEnvironmentImpl.setHeight(height);
//    }
//
//    public void setWidth(int width) {
//        graphicsEnvironmentImpl.setWidth(width);
//    }
    /**
     * Fill a circle with a center position of centerX, centerY and the
     * specified radius.
     *
     * @param centerX center x coordinate
     * @param centerY center y coordinate
     * @param radius the radius
     */
    public abstract void fillCircle(float centerX, float centerY, float radius);

    /**
     * Fills a polygon with the given points using the currently set fill paint.
     *
     * @param x_coord array containing the x coordinates of the polygon's
     * points.
     * @param y_coord array containing the y coordinates of the polygon's
     * points.
     * @param vertexCount the number of points that make the polygon.
     */
    public abstract void fillPolygon(double[] x_coord, double[] y_coord, int vertexCount);

    /**
     * Get the Dimension of an Image
     *
     * @param img the Image to measure
     * @return the Dimension of this Image
     */
    public abstract Dimension getDimension(Image img);

    /**
     * Set the width of this GraphicsContext2D
     * @param width target width for this GraphicsContext2D
     */
    public abstract void setWidth(int width);

    /**
     * set the height of this GraphicsContext2D
     * @param height target height for this GraphicsContext2D
     */
    public abstract void setHeight(int height);

}
