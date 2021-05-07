package me.skiincraft.ousucanvas.elements;

import javax.annotation.Nonnull;

public enum ElementAlignment {

	LEFT, CENTER, RIGHT,
	TOP_LEFT, TOP, TOP_RIGHT,
	BOTTOM_LEFT, BOTTOM, BOTTOM_RIGHT;

	public int[] align(@Nonnull ElementContainer element){
		return this.align(element.getX(), element.getY(), element.getWidth(), element.getHeight());
	}

	public int[] align(int x, int y, int width, int height){
		switch (this){
			case CENTER:
				return new int[] {x - (width/2), y - (height/2)};
			case RIGHT:
				return new int[] {x - width, y - (height/2)};
			case LEFT:
				return new int[] {x, y - (height/2)};
			case TOP:
				return new int[] {x - (width/2), y - height};
			case BOTTOM:
				return new int[] {x - (width/2), y};
			case TOP_LEFT:
				return new int[] {x, y - height};
			case TOP_RIGHT:
				return new int[] {x - width, y - height};
			case BOTTOM_RIGHT:
				return new int[] {x - width, y};
			default:
				return new int[] {x, y};
		}
	}
}