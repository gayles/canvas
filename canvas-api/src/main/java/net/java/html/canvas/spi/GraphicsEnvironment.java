/**
 * Canvas API
 * Copyright (C) 2013 AntonEpple <toni.epple@eppleton.de>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 2 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. Look for COPYING file in the top folder.
 * If not, see http://opensource.org/licenses/GPL-2.0.
 */
package net.java.html.canvas.spi;

import net.java.html.canvas.Dimension;
import net.java.html.canvas.Image;
import net.java.html.canvas.ImageData;
import net.java.html.canvas.Style;

/**
 * Provider API for Canvas. Implement this to add support for your platform.
 *
 * @author antonepple
 * @param <Canvas>
 */
public interface GraphicsEnvironment <Canvas>{

    /**
     * Adds path elements to the current path to make an arc.
     *
     * @param canvas
     * @param centerX the center x position of the arc.
     * @param centerY the center y position of the arc.
     * @param startAngle the startAngle of the arc
     * @param radius the radius of the arc.
     * @param endAngle the endAngle of the arc
     * @param ccw the direction of the arc (counterclockwise)
     */
    public void arc(Canvas canvas, 
            double centerX,
            double centerY,
            double startAngle,
            double radius,
            double endAngle,
            boolean ccw);

    /**
     * Adds segments to the current path to make an arc.
     *
     * @param canvas
     * @param x1 the X coordinate of the first point of the arc.
     * @param y1 the Y coordinate of the first point of the arc.
     * @param x2 the X coordinate of the second point of the arc.
     * @param y2 the Y coordinate of the second point of the arc.
     * @param radius the radius of the arc in the range {0.0-positive infinity}.
     */
    public void arcTo(Canvas canvas, 
            double x1,
            double y1,
            double x2,
            double y2,
            double radius);

    /**
     * Returns true if the the given x,y point is inside the path.
     *
     * @param canvas
     * @param x the X coordinate to use for the check.
     * @param y the Y coordinate to use for the check.
     * @return true if the point given is inside the path, false otherwise.
     */
    public boolean isPointInPath(Canvas canvas, 
            double x, double y);

    /**
     * Fills the path with the current fill paint.
     * @param canvas
     */
    public void fill(Canvas canvas);

    /**
     * Strokes the path with the current stroke paint.
     * @param canvas
     */
    public void stroke(Canvas canvas);

    /**
     * Starts a Path
     * @param canvas
     */
    public void beginPath(Canvas canvas);

    /**
     * Closes the path.
     * @param canvas
     */
    public void closePath(Canvas canvas);

    /**
     * Clips using the current path
     * @param canvas
     */
    public void clip(Canvas canvas);

    /**
     * Issues a move command for the current path to the given x,y coordinate.
     *
     * @param canvas
     * @param x the X position for the move to command.
     * @param y the Y position for the move to command.
     */
    public void moveTo(Canvas canvas, double x, double y);

    /**
     * Adds segments to the current path to make a line at the given x,y
     * coordinate.
     *
     * @param canvas
     * @param x the X coordinate of the ending point of the line.
     * @param y the Y coordinate of the ending point of the line.
     */
    public void lineTo(Canvas canvas, double x, double y);

    /**
     * Adds segments to the current path to make a quadratic curve.
     *
     * @param canvas
     * @param cpx the X coordinate of the control point
     * @param cpy the Y coordinate of the control point
     * @param x the X coordinate of the end point
     * @param y the Y coordinate of the end point
     */
    public void quadraticCurveTo(Canvas canvas, double cpx, double cpy, double x, double y);

    /**
     * Adds segments to the current path to make a cubic bezier curve.
     *
     * @param canvas
     * @param cp1x the X coordinate of first bezier control point.
     * @param cp1y the Y coordinate of the first bezier control point.
     * @param cp2x the X coordinate of the second bezier control point.
     * @param cp2y the Y coordinate of the second bezier control point.
     * @param x the X coordinate of the end point.
     * @param y the Y coordinate of the end point.
     */
    public void bezierCurveTo(Canvas canvas, double cp1x, double cp1y, double cp2x, double cp2y, double x, double y);

    /**
     * Fills a rectangle using the current fill paint.
     *
     * @param canvas
     * @param x the X position of the upper left corner of the rectangle.
     * @param y the Y position of the upper left corner of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     */
    public void fillRect(Canvas canvas, double x, double y, double width, double height);

    /**
     * Strokes a rectangle using the current stroke paint.
     *
     * @param canvas
     * @param x the X position of the upper left corner of the rectangle.
     * @param y the Y position of the upper left corner of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     */
    public void strokeRect(Canvas canvas, double x, double y, double width, double height);

    /**
     * Clears a portion of the canvas with a transparent color value.
     *
     * @param canvas
     * @param x X position of the upper left corner of the rectangle.
     * @param y Y position of the upper left corner of the rectangle.
     * @param width width of the rectangle.
     * @param height height of the rectangle.
     */
    public void clearRect(Canvas canvas, double x, double y, double width, double height);

    /**
     * Clears a portion of the canvas with a transparent color value.
     *
     * @param canvas
     * @param x X position of the upper left corner of the rectangle.
     * @param y Y position of the upper left corner of the rectangle.
     * @param width width of the rectangle.
     * @param height height of the rectangle.
     */
    public void rect(Canvas canvas, double x, double y, double width, double height);

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
     * @param canvas
     */
    public void save(Canvas canvas);

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
     * @param canvas
     */
    public void restore(Canvas canvas);

    /**
     * Rotates the current transform in degrees.
     *
     * @param canvas
     * @param angle value in degrees to rotate the current transform.
     */
    public void rotate(Canvas canvas, double angle);

    /**
     * Concatenates the input with the current transform.
     *
     * @param canvas
     * @param a - the X coordinate scaling element of the 3x4 matrix
     * @param b - the Y coordinate shearing element of the 3x4 matrix
     * @param c - the X coordinate shearing element of the 3x4 matrix
     * @param d - the Y coordinate scaling element of the 3x4 matrix
     * @param e - the X coordinate translation element of the 3x4 matrix
     * @param f - the Y coordinate translation element of the 3x4 matrix
     */
    public void transform(Canvas canvas, double a, double b, double c, double d, double e, double f);

    /**
     * Concatenates the input with the current transform.
     *
     * @param canvas
     * @param a - the X coordinate scaling element of the 3x4 matrix
     * @param b - the Y coordinate shearing element of the 3x4 matrix
     * @param c - the X coordinate shearing element of the 3x4 matrix
     * @param d - the Y coordinate scaling element of the 3x4 matrix
     * @param e - the X coordinate translation element of the 3x4 matrix
     * @param f - the Y coordinate translation element of the 3x4 matrix
     */
    public void setTransform(Canvas canvas, double a, double b, double c, double d, double e, double f);

    /**
     * Translates the current transform by x, y.
     *
     * @param canvas
     * @param x value to translate along the x axis.
     * @param y value to translate along the y axis.
     */
    public void translate(Canvas canvas, double x, double y);

    /**
     * Scales the current transform by x, y.
     *
     * @param canvas
     * @param x value to scale in the x axis.
     * @param y value to scale in the y axis.
     */
    public void scale(Canvas canvas, double x, double y);

    /**
     * Draws an image at the given x, y position using the width and height of
     * the given image.
     *
     * @param canvas
     * @param image the image to be drawn.
     * @param x the X coordinate on the destination for the upper left of the
     * image.
     * @param y the Y coordinate on the destination for the upper left of the
     * image.
     * @return the native Image for caching.
     */
    public Object drawImage(Canvas canvas, Image image, double x, double y, Object nativeImage);

    /**
     * Draws an image into the given destination rectangle of the canvas. The
     * Image is scaled to fit into the destination rectagnle.
     *
     * @param canvas
     * @param image the image to be drawn.
     * @param x the X coordinate on the destination for the upper left of the
     * image.
     * @param y the Y coordinate on the destination for the upper left of the
     * image.
     * @param width the width of the destination rectangle.
     * @param height the height of the destination rectangle.
     * @return the native Image for caching.
     *
     */
    public Object drawImage(Canvas canvas, Image image, double x, double y, double width, double height, Object nativeImage);

    /**
     * Draws the current source rectangle of the given image to the given
     * destination rectangle of the Canvas.
     *
     * @param canvas
     * @param image the image to be drawn.
     * @param sx the source rectangle's X coordinate position.
     * @param sy the source rectangle's Y coordinate position.
     * @param sWidth the source rectangle's width.
     * @param sHeight the source rectangle's height.
     * @param x the destination rectangle's X coordinate position.
     * @param y the destination rectangle's Y coordinate position.
     * @param width the destination rectangle's width.
     * @param height the destination rectangle's height.
     * @return the native Image for caching.
     */
    public Object drawImage(Canvas canvas, Image image, double sx, double sy, double sWidth, double sHeight, double x, double y, double width, double height, Object nativeImage);

    /**
     * Get the width of this Image
     *
     * @param canvas
     * @param image the image to measure
     * @param nativeImage the cached native Image or null
     * @return the width of the image
     */
    public int getWidth(Canvas canvas, Image image, Object nativeImage);

    /**
     * Get the height of this Image
     *
     * @param canvas
     * @param image the image to measure
     * @param nativeImage the cached native Image or null
     * @return the height of the image
     */
    public int getHeight(Canvas canvas, Image image, Object nativeImage);

    /**
     * When implementing you can return an Object of your choice to enable
     * caching. Returning null means no caching. When caching is enabled, and
     * the cache hasn't been invalidated, the Object you returned will be passed
     * as a parameter.
     *
     * @param canvas
     * @param style The style object you should use to create your native style
     * @param nativeStyle your native object if cached, null otherwise
     * @return return native Object for caching
     *
     */
    public Object setFillStyle(Canvas canvas, Style style, Object nativeStyle);

    /**
     * When implementing you can return an Object of your choice to enable
     * caching. Returning null means no caching. When caching is enabled, and
     * the cache hasn't been invalidated, the Object you returned will be passed
     * as a parameter.
     *
     * @param canvas
     * @param style The style object you should use to create your native style
     * @param nativeStyle your native object if cached, null otherwise
     * @return return native Object for caching
     *
     */
    public Object setStrokeStyle(Canvas canvas, Style style, Object nativeStyle);

//    public void setShadowColor(String color);
//
//    public void setShadowBlur(double blur);
//
//    public void setShadowOffsetX(double x);
//
//    public void setShadowOffsetY(double y);
//    
//    public String getShadowColor();
//
//    public double getShadowBlur();
//
//    public double getShadowOffsetX();
//
//    public double getShadowOffsetY();
    /**
     * Gets the current stroke line cap.
     *
     * @param canvas
     * @return {@code StrokeLineCap} with a value of Butt, Round, or Square.
     */
    public String getLineCap(Canvas canvas);

    /**
     * Sets the current stroke line cap.
     *
     * @param canvas
     * @param style a value of Butt, Round, or Square.
     */
    public void setLineCap(Canvas canvas, String style);

    /**
     * Gets the current stroke line join.
     *
     * @param canvas
     * @return a value of Miter, Bevel, or Round.
     */
    public String getLineJoin(Canvas canvas);

    /**
     * Sets the current stroke line join.
     *
     * @param canvas
     * @param style with a value of Miter, Bevel, or Round.
     */
    public void setLineJoin(Canvas canvas, String style);

    /**
     * Gets the current line width.
     *
     * @param canvas
     * @return value between 0 and infinity.
     */
    public double getLineWidth(Canvas canvas);

    /**
     * Sets the current line width.
     *
     * @param canvas
     * @param width value in the range {0-positive infinity}, with any other
     * value being ignored and leaving the value unchanged.
     */
    public void setLineWidth(Canvas canvas, double width);

    /**
     * Gets the current miter limit. v
     *
     * @param canvas
     * @return the miter limit value in the range {@code 0.0-positive infinity}
     */
    public double getMiterLimit(Canvas canvas);

    /**
     * Sets the current miter limit.
     *
     * @param canvas
     * @param limit miter limit value between 0 and positive infinity with any
     * other value being ignored and leaving the value unchanged.
     */
    public void setMiterLimit(Canvas canvas, double limit);

    /**
     * Gets the current Font.
     *
     * @param canvas
     * @return the Font
     */
    public String getFont(Canvas canvas);

    /**
     * Sets the current Font.
     *
     * @param canvas
     * @param font
     */
    public void setFont(Canvas canvas, String font);

    /**
     * Gets the current {@code TextAlignment}.
     *
     * @param canvas
     * @return TextAlignment with values of Left, Center, Right, or Justify.
     */
    public String getTextAlign(Canvas canvas);

    /**
     * Defines horizontal text alignment, relative to the text origin.
     *
     * @param canvas
     * @param textAlign with values of Left, Center, Right.
     */
    public void setTextAlign(Canvas canvas, String textAlign);

    /**
     * Sets the current Text Baseline.
     *
     * @param canvas
     * @param baseline with values of Top, Center, Baseline, or Bottom
     */
    public String getTextBaseline(Canvas canvas);

    /**
     * Sets the current Text Baseline.
     *
     * @param baseline with values of Top, Center, Baseline, or Bottom
     */
    public void setTextBaseline(Canvas canvas, String baseline);

    /**
     * Fills the given string of text at position x, y (0,0 at top left) with
     * the current fill paint attribute.
     *
     * @param canvas
     * @param text the string of text.
     * @param x position on the x axis.
     * @param y position on the y axis.
     */
    public void fillText(Canvas canvas, String text, double x, double y);

    /**
     * Fills text and includes a maximum width of the string.
     *
     * If the width of the text extends past max width, then it will be sized to
     * fit.
     *
     * @param canvas
     * @param text the string of text.
     * @param x position on the x axis.
     * @param y position on the y axis.
     * @param maxWidth maximum width the text string can have.
     */
    public void fillText(Canvas canvas, String text, double x, double y, double maxWidth);

    /**
     * The Dimension of this text using the current Font settings
     *
     * @param canvas
     * @param text
     * @return the Dimension of this text using the current Font settings
     */
    public Dimension measureText(Canvas canvas, String text);

    /**
     * draws the given string of text at position x, y (0,0 at top left) with
     * the current stroke paint attribute.
     *
     * @param canvas
     * @param text the string of text.
     * @param x position on the x axis.
     * @param y position on the y axis.
     */
    public void strokeText(Canvas canvas, String text, double x, double y);

    /**
     * Draws text with stroke paint and includes a maximum width of the string.
     *
     * If the width of the text extends past max width, then it will be sized to
     * fit.
     *
     * @param canvas
     * @param text the string of text.
     * @param x position on the x axis.
     * @param y position on the y axis.
     * @param maxWidth maximum width the text string can have.
     */
    public void strokeText(Canvas canvas, String text, double x, double y, double maxWidth);

    /**
     * Get a pixel array that you can manipulate, e.g. apply effects / transparency
     * @param canvas
     * @param x width
     * @param y height
     * @return a PixelMap
     */
    public ImageData createPixelMap(Canvas canvas, double x, double y);

    /**
     * Create a new ImageData object with the same dimensions as the 
     * object specified by imageData (this does not copy the image data)
     * @param canvas
     * @param imageData
     * @return 
     */
    public ImageData createPixelMap(Canvas canvas, ImageData imageData);

    /**
     * Get the pixels for a region of your GraphicsContext
     * @param canvas
     * @param x start x coordinate
     * @param y start y coordinate
     * @param width width
     * @param height height
     * @return 
     */
    public ImageData getPixelMap(Canvas canvas, double x, double y, double width, double height);

    /**
     * Render an ImageData Object at the specified position
     * @param canvas
     * @param imageData the Pixel array
      * @param x start x coordinate
     * @param y start y coordinate
     */
   public void putPixelMap(Canvas canvas, ImageData imageData, double x, double y);

    /**
     * Render an ImageData Object at the specified position
     * @param canvas
     * @param imageData the Pixel array to draw
     * @param x start x coordinate
     * @param y start y coordinate
     * @param dirtyx The horizontal (x) value, in pixels, where to place the image on the canvas
     * @param dirtyy The vertical (y) value, in pixels, where to place the image on the canvas
     * @param dirtywidth The width to use to draw the image on the canvas
     * @param dirtyheight The height to use to draw the image on the canvas
     */
    public void putPixelMap(Canvas canvas, ImageData imageData, double x, double y, double dirtyx, double dirtyy, double dirtywidth, double dirtyheight);
    /**
     * Sets the global alpha of the current state.
     *
     * @param canvas
     * @param alpha value in the range {@code 0.0-1.0}. The value is clamped if
     * it is out of range.
     */
    public void setGlobalAlpha(Canvas canvas, double alpha);

    /**
     * Get the global alpha of the current state.
     *
     * @param canvas
     * @return alpha value in the range {@code 0.0-1.0}.
     */
    public double getGlobalAlpha(Canvas canvas);

    /**
     * Sets the global blend mode.
     *
     * @param canvas
     * @param operation the BlendMode that will be set.
     */
    public void setGlobalCompositeOperation(Canvas canvas, String operation);

    /**
     * Gets the global blend mode.
     *
     * @param canvas
     * @return the global BlendMode of the current state.
     */
    public String getGlobalCompositeOperation(Canvas canvas);

    /**
     * Get the height of this GraphicsContext (which should be the same as the
     * enclosing canvas height)
     *
     * @param canvas
     * @return the height of this GraphicsContext
     */
    public int getHeight(Canvas canvas);

    /**
     * Get the width of this GraphicsContext (which should be the same as the
     * enclosing canvas height)
     *
     * @param canvas
     * @return the width of this GraphicsContext
     */
    public int getWidth(Canvas canvas);

//    public void setHeight(int height);
//
//    public void setWidth(int width);
     /**
     * Merges two images drawing one on top of the other and returning the
     * result.
     *
     * @param canvas
     * @param a the lower Image
     * @param b the upper Image
     * @param cachedA the native cached Image, if available, or null.
     * @param cachedB the native cached Image, if available, or null.
     * @return
     */  
    public Object mergeImages(Canvas canvas, Image a, Image b, Object cachedA, Object cachedB);
}
